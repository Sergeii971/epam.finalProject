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
import java.util.List;

public class ChangeOrderStatusCommand implements ActionCommand {
    private final Logger logger = LogManager.getLogger(ChangeOrderStatusCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        long orderId = Long.parseLong(request.getParameter(RequestParameter.ORDER_ID));
        boolean inProcessing = false;
        String page = PageType.ERROR.getPath();

        try {
            OrderService service = new OrderServiceImpl();

            service.updateInProcessingStatus(orderId, inProcessing);
            List<Order> allOrders = service.findAllOrders();
            int toIndex = (int) session.getAttribute(AttributeKey.TO_INDEX);
            int fromIndex = (int) session.getAttribute(AttributeKey.FROM_INDEX);
            List<Order> ordersPerPage = allOrders.subList(fromIndex, toIndex);

            session.setAttribute(AttributeKey.ORDER_LIST, allOrders);
            session.setAttribute(AttributeKey.ORDER_PER_PAGE, ordersPerPage);
            page = PageType.ADMIN_SHOW_ORDER.getPath();
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
        return page;
    }
}
