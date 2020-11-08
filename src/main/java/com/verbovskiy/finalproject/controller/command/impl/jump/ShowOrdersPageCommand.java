package com.verbovskiy.finalproject.controller.command.impl.jump;

import com.verbovskiy.finalproject.controller.AttributeKey;
import com.verbovskiy.finalproject.controller.command.ActionCommand;
import com.verbovskiy.finalproject.controller.command.Constant;
import com.verbovskiy.finalproject.controller.command.PageType;
import com.verbovskiy.finalproject.controller.command.RequestParameter;
import com.verbovskiy.finalproject.exception.ServiceException;
import com.verbovskiy.finalproject.model.entity.Car;
import com.verbovskiy.finalproject.model.entity.Order;
import com.verbovskiy.finalproject.model.service.CarService;
import com.verbovskiy.finalproject.model.service.OrderService;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class ShowOrdersPageCommand implements ActionCommand {
    private final Logger logger = LogManager.getLogger(ShowOrdersPageCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        OrderService service = new OrderService();
        String page = PageType.ERROR.getPath();
        session.setAttribute(RequestParameter.HAS_NEXT_PAGE, true);

        try {
            List<Order> allOrders = service.findAllOrders();
            if (allOrders == null || allOrders.isEmpty()) {
                request.setAttribute(RequestParameter.IS_EMPTY, true);
            } else {
                int toIndex = Constant.NUMBER_OF_ORDER_PER_PAGE;
                if (allOrders.size() <= Constant.NUMBER_OF_ORDER_PER_PAGE) {
                    toIndex = allOrders.size();
                    session.setAttribute(RequestParameter.HAS_NEXT_PAGE, false);
                }
                List<Order> ordersPerPage;
                ordersPerPage = (allOrders.size() == 1) ? allOrders : allOrders.subList(0, toIndex);
                session.setAttribute(AttributeKey.ORDER_PER_PAGE, ordersPerPage);
                session.setAttribute(AttributeKey.ORDER_LIST, allOrders);
                session.setAttribute(AttributeKey.TO_INDEX, toIndex);
                session.setAttribute(AttributeKey.FROM_INDEX, 0);
            }
            page = PageType.SHOW_ORDER.getPath();
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
        return page;
    }
}
