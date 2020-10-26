package com.verbovskiy.finalproject.controller.command.impl;

import com.verbovskiy.finalproject.controller.command.ActionCommand;
import com.verbovskiy.finalproject.controller.command.CommandParameter;
import com.verbovskiy.finalproject.controller.command.PageName;
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

public class AddUserCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger(AddUserCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        UserService service = new UserService();
        String email = request.getParameter(CommandParameter.EMAIL_PARAMETER);
        String password = request.getParameter(CommandParameter.PASSWORD_PARAMETER);
        boolean isAdmin = false;
        boolean isBlocked = true;
        boolean isActive = false;
        String name = request.getParameter(CommandParameter.NAME_PARAMETER);
        String surname = request.getParameter(CommandParameter.SURNAME_PARAMETER);
        String page = PageName.ERROR.getPath();
        HttpSession session = request.getSession();

        try {
            if(service.add(email, password, isAdmin, isBlocked, email, name, surname)) {
                Account account = new Account(email, isBlocked, isAdmin, isActive);
                Cryptographer cryptographer = new Cryptographer();
                String confirmationKey = cryptographer.encrypt(account.toString());
                MailSender sender = new MailSender(email, CommandParameter.MAIL_MASSAGE_SUBJECT, confirmationKey);
                sender.send();
                page = PageName.CONFIRMATION.getPath();
            } else {
                session.setAttribute(CommandParameter.EMAIL_PARAMETER, email);
                session.setAttribute(CommandParameter.PASSWORD_PARAMETER, password);
                session.setAttribute(CommandParameter.NAME_PARAMETER, name);
                session.setAttribute(CommandParameter.SURNAME_PARAMETER, surname);
                page = PageName.REGISTRATION.getPath();
            }
        } catch (ServiceException | EncryptionException | SendMailException e) {
            logger.log(Level.ERROR, e);
        }
        return page;
    }
}
