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
import com.verbovskiy.finalproject.model.service.impl.UserServiceImpl;
import com.verbovskiy.finalproject.util.encryption.Cryptographer;
import com.verbovskiy.finalproject.util.mail.MailSender;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

/**
 * The type send recovery key command.
 *
 * @author Verbovskiy Sergei
 * @version 1.0
 */
public class SendRecoveryKeyCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger(SendRecoveryKeyCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        String email = request.getParameter(RequestParameter.EMAIL);
        UserService service = new UserServiceImpl();
        String page = PageType.ERROR.getPath();
        HttpSession session = request.getSession();

        try {
            Optional<Account> account = service.findByLogin(email);
            if (account.isPresent()) {
                if (!service.isBlocked(email)) {
                    Cryptographer cryptographer = new Cryptographer();
                    String recoveryKey = cryptographer.encrypt(account.get().toString());
                    MailSender sender = new MailSender(email, RequestParameter.EMPTY_VALUE, recoveryKey);
                    sender.send();
                    request.setAttribute(AttributeKey.IS_CONFIRMATION_CODE_SEND, true);
                } else {
                    request.setAttribute(RequestParameter.IS_BLOCKED, true);
                }
            }
            session.setAttribute(RequestParameter.EMAIL, email);
            page = PageType.FORGOT_PASSWORD.getPath();
        } catch (ServiceException | EncryptionException | SendMailException e) {
            logger.log(Level.ERROR, e);
        }
        return page;
    }
}
