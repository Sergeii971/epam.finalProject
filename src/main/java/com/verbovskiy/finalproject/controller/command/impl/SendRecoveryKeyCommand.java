package com.verbovskiy.finalproject.controller.command.impl;

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
import java.util.Optional;

public class SendRecoveryKeyCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger(SendRecoveryKeyCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        String email = request.getParameter(RequestParameter.EMAIL);
        UserService service = new UserService();
        String page = PageType.ERROR.getPath();

        try {
            Optional<Account> account = service.findByLogin(email);
            if (account.isPresent()) {
                Cryptographer cryptographer = new Cryptographer();
                String recoveryKey = cryptographer.encrypt(account.get().toString());
                MailSender sender = new MailSender(email, RequestParameter.MAIL_MASSAGE_SUBJECT, recoveryKey);
                sender.send();
            }
            HttpSession session = request.getSession();
            session.setAttribute(RequestParameter.EMAIL, email);
            addComeBackPagePath(session, PageType.FORGOT_PASSWORD.getPath());
            page = PageType.FORGOT_PASSWORD.getPath();
        } catch (ServiceException | EncryptionException | SendMailException e) {
            logger.log(Level.ERROR, e);
        }
        return page;
    }
}
