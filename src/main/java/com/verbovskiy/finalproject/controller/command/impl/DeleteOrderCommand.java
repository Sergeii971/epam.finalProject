package com.verbovskiy.finalproject.controller.command.impl;

import com.verbovskiy.finalproject.controller.AttributeKey;
import com.verbovskiy.finalproject.controller.command.ActionCommand;
import com.verbovskiy.finalproject.controller.command.PageType;
import com.verbovskiy.finalproject.controller.command.RequestParameter;
import com.verbovskiy.finalproject.exception.ServiceException;
import com.verbovskiy.finalproject.model.entity.Order;
import com.verbovskiy.finalproject.model.service.OrderService;
import com.verbovskiy.finalproject.model.service.impl.OrderServiceImpl;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class DeleteOrderCommand implements ActionCommand {
    private final Logger logger = LogManager.getLogger(DeleteOrderCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        long orderId = Long.parseLong(request.getParameter(RequestParameter.ORDER_ID));
        String page = PageType.ERROR.getPath();

        try {
            OrderService service = new OrderServiceImpl();
            int toIndex = (int) session.getAttribute(AttributeKey.TO_INDEX);
            int fromIndex = (int) session.getAttribute(AttributeKey.FROM_INDEX);
            service.remove(orderId);
            List<Order> allOrders = service.findAllOrders();
            if (allOrders.size() == 0) {
                request.setAttribute(RequestParameter.IS_EMPTY, true);
                session.setAttribute(AttributeKey.ORDER_PER_PAGE, new ArrayList<>());
            } else {
                if (allOrders.size() <= toIndex) {
                    toIndex = allOrders.size();
                    session.setAttribute(RequestParameter.HAS_NEXT_PAGE, false);
                }
                List<Order> ordersPerPage = allOrders.subList(fromIndex, toIndex);
                session.setAttribute(AttributeKey.ORDER_LIST, allOrders);
                session.setAttribute(AttributeKey.ORDER_PER_PAGE, ordersPerPage);
                session.setAttribute(AttributeKey.TO_INDEX, toIndex);
                session.setAttribute(AttributeKey.FROM_INDEX, fromIndex);
            }
            boolean isAdmin = (boolean) session.getAttribute(RequestParameter.IS_ADMIN);
            page = isAdmin ? PageType.ADMIN_SHOW_ORDER.getPath() : PageType.USER_SHOW_ORDER.getPath();
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
        return page;
    }
}
