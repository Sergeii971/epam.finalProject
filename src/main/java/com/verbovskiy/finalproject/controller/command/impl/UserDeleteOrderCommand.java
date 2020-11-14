package com.verbovskiy.finalproject.controller.command.impl;

import com.verbovskiy.finalproject.controller.AttributeKey;
import com.verbovskiy.finalproject.controller.command.ActionCommand;
import com.verbovskiy.finalproject.controller.command.PageType;
import com.verbovskiy.finalproject.controller.command.RequestParameter;
import com.verbovskiy.finalproject.exception.ServiceException;
import com.verbovskiy.finalproject.model.entity.UserOrder;
import com.verbovskiy.finalproject.model.service.OrderService;
import com.verbovskiy.finalproject.model.service.impl.OrderServiceImpl;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

/**
 * The type user delete order command.
 *
 * @author Verbovskiy Sergei
 * @version 1.0
 */
public class UserDeleteOrderCommand implements ActionCommand {
    private final Logger logger = LogManager.getLogger(UserDeleteOrderCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        long orderId = Long.parseLong(request.getParameter(RequestParameter.ORDER_ID));
        String email = (String) session.getAttribute(RequestParameter.EMAIL);
        String page = PageType.ERROR.getPath();

        try {
            OrderService service = new OrderServiceImpl();
            service.remove(orderId);
            Optional<List<UserOrder>> allOrders = service.findOrdersByUserEmail(email);
            session.setAttribute(AttributeKey.ORDER_LIST, allOrders.get());
            page = PageType.USER_SHOW_ORDER.getPath();
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
        return page;
    }
}
