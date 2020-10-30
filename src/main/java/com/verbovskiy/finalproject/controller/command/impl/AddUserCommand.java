package com.verbovskiy.finalproject.controller.command.impl;

import com.verbovskiy.finalproject.controller.AttributeKey;
import com.verbovskiy.finalproject.controller.command.ActionCommand;
import com.verbovskiy.finalproject.controller.command.RequestParameter;
import com.verbovskiy.finalproject.controller.command.PageType;
import com.verbovskiy.finalproject.exception.EncryptionException;
import com.verbovskiy.finalproject.exception.SendMailException;
import com.verbovskiy.finalproject.exception.ServiceException;
import com.verbovskiy.finalproject.model.entity.Account;
import com.verbovskiy.finalproject.model.service.UserService;
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
        UserService service = new UserService();
        String email = request.getParameter(RequestParameter.EMAIL);
        String password = request.getParameter(RequestParameter.PASSWORD);
        boolean isAdmin = false;
        boolean isBlocked = true;
        boolean isActive = false;
        String name = request.getParameter(RequestParameter.NAME);
        String surname = request.getParameter(RequestParameter.SURNAME);
        String page = PageType.ERROR.getPath();
        HttpSession session = request.getSession();

        try {
            if(service.add(email, password, isAdmin, isBlocked, email, name, surname)) {
                Account account = new Account(email, isBlocked, isAdmin, isActive);
                Cryptographer cryptographer = new Cryptographer();
                String confirmationKey = cryptographer.encrypt(account.toString());
                MailSender sender = new MailSender(email, RequestParameter.MAIL_MASSAGE_SUBJECT, confirmationKey);
                sender.send();
                page = PageType.CONFIRMATION.getPath();
            } else {
                session.setAttribute(RequestParameter.EMAIL, email);
                session.setAttribute(RequestParameter.PASSWORD, password);
                session.setAttribute(RequestParameter.NAME, name);
                session.setAttribute(RequestParameter.SURNAME, surname);
                addComeBackPagePath(session, PageType.CONFIRMATION.getPath());
                page = PageType.REGISTRATION.getPath();
            }
        } catch (ServiceException | EncryptionException | SendMailException e) {
            logger.log(Level.ERROR, e);
        }
        return page;
    }
}
