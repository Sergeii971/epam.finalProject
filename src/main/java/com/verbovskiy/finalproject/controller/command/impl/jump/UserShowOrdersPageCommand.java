package com.verbovskiy.finalproject.controller.command.impl.jump;

import com.verbovskiy.finalproject.controller.AttributeKey;
import com.verbovskiy.finalproject.controller.command.ActionCommand;
import com.verbovskiy.finalproject.controller.command.PageType;
import com.verbovskiy.finalproject.controller.command.RequestParameter;
import com.verbovskiy.finalproject.exception.ServiceException;
import com.verbovskiy.finalproject.model.entity.Order;
import com.verbovskiy.finalproject.model.service.impl.OrderServiceImpl;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

public class UserShowOrdersPageCommand implements ActionCommand {
    private final Logger logger = LogManager.getLogger(UserShowOrdersPageCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        OrderServiceImpl service = new OrderServiceImpl();
        String page = PageType.ERROR.getPath();
        String email = (String) session.getAttribute(RequestParameter.EMAIL);

        try {
            Optional<List<Order>> orders = service.findOrdersByUserEmail(email);
            if (!orders.isPresent()) {
                request.setAttribute(RequestParameter.IS_EMPTY, true);
            } else {
                session.setAttribute(AttributeKey.ORDER_LIST, orders.get());
            }
            page = PageType.USER_SHOW_ORDER.getPath();
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
        return page;
    }
}
