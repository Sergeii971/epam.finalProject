package com.verbovskiy.finalproject.controller.command.impl;

import com.verbovskiy.finalproject.controller.command.ActionCommand;
import com.verbovskiy.finalproject.controller.command.CommandParameter;
import com.verbovskiy.finalproject.controller.command.PageName;
import com.verbovskiy.finalproject.exception.EncryptionException;
import com.verbovskiy.finalproject.exception.SendMailException;
import com.verbovskiy.finalproject.exception.ServiceException;
import com.verbovskiy.finalproject.model.service.UserService;
import com.verbovskiy.finalproject.util.encryption.Cryptographer;
import com.verbovskiy.finalproject.util.mail.MailSender;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class AddUserCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger(AddUserCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        UserService service = new UserService();
        String email = request.getParameter(CommandParameter.EMAIL_PARAMETER);
        String password = request.getParameter(CommandParameter.PASSWORD_PARAMETER);
        boolean isAdmin = false;
        boolean isBlocked = true;
        String name = request.getParameter(CommandParameter.NAME_PARAMETER);
        String surname = request.getParameter(CommandParameter.SURNAME_PARAMETER);
        String page = PageName.ERROR.getPath();
        request.setAttribute(CommandParameter.EMAIL_PARAMETER, email);
        request.setAttribute(CommandParameter.PASSWORD_PARAMETER, password);
        request.setAttribute(CommandParameter.NAME_PARAMETER, name);
        request.setAttribute(CommandParameter.SURNAME_PARAMETER, surname);

        try {
            Cryptographer cryptographer = new Cryptographer();
            String encryptedEmail = cryptographer.encrypt(email);
            if(service.add(email, password, isAdmin, isBlocked, email, name, surname)) {
                MailSender sender = new MailSender(email, CommandParameter.MAIL_MASSAGE_SUBJECT,
                        encryptedEmail);
                sender.send();
                page = PageName.CONFIRMATION.getPath();
            } else {
                page = PageName.REGISTRATION.getPath();
            }
            } catch (ServiceException | EncryptionException | IOException | SendMailException e) {
            logger.log(Level.ERROR, e);
        }
        return page;
    }
}
