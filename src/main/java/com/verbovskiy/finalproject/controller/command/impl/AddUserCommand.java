package com.verbovskiy.finalproject.controller.command.impl;

import com.verbovskiy.finalproject.controller.AttributeKey;
import com.verbovskiy.finalproject.controller.command.ActionCommand;
import com.verbovskiy.finalproject.controller.command.PageType;
import com.verbovskiy.finalproject.controller.command.RequestParameter;
import com.verbovskiy.finalproject.exception.EncryptionException;
import com.verbovskiy.finalproject.exception.SendMailException;
import com.verbovskiy.finalproject.exception.ServiceException;
import com.verbovskiy.finalproject.model.entity.Account;
import com.verbovskiy.finalproject.model.service.UserService;
import com.verbovskiy.finalproject.model.service.impl.UserServiceImpl;
import com.verbovskiy.finalproject.util.encryption.Cryptographer;
import com.verbovskiy.finalproject.util.mail.MailSender;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

public class AddUserCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger(AddUserCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        UserService service = new UserServiceImpl();
        String email = request.getParameter(RequestParameter.EMAIL);
        String password = request.getParameter(RequestParameter.PASSWORD);
        boolean isAdmin = false;
        boolean isBlocked = false;
        boolean isConfirmed = false;
        String name = request.getParameter(RequestParameter.NAME);
        String surname = request.getParameter(RequestParameter.SURNAME);
        String page = PageType.ERROR.getPath();
        HttpSession session = request.getSession();

        try {
            Map<String, Boolean> incorrectParameter = service.add(email, password, isAdmin, isBlocked, isConfirmed,
                    email, name, surname);
            if(incorrectParameter.size() == 0) {
                Account account = new Account(email, isBlocked, isAdmin, isConfirmed);
                Cryptographer cryptographer = new Cryptographer();
                String confirmationKey = cryptographer.encrypt(account.toString());
                MailSender sender = new MailSender(email,RequestParameter.EMPTY_VALUE, confirmationKey);
                sender.send();
                addComeBackPagePath(session, PageType.CONFIRMATION.getPath());
                page = PageType.CONFIRMATION.getPath();
            } else {
                request.setAttribute(RequestParameter.EMAIL, email);
                request.setAttribute(RequestParameter.NAME, name);
                request.setAttribute(RequestParameter.SURNAME, surname);
                request.setAttribute(AttributeKey.INCORRECT_PARAMETER, incorrectParameter);
                page = PageType.REGISTRATION.getPath();
            }
        } catch (ServiceException | EncryptionException | SendMailException e) {
            logger.log(Level.ERROR, e);
        }
        return page;
    }
}
