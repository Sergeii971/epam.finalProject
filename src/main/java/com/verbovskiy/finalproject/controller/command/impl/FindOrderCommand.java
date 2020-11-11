package com.verbovskiy.finalproject.controller.command.impl;

import com.verbovskiy.finalproject.controller.AttributeKey;
import com.verbovskiy.finalproject.controller.command.ActionCommand;
import com.verbovskiy.finalproject.controller.command.Constant;
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
import java.util.Optional;

public class FindOrderCommand implements ActionCommand {
    private final Logger logger = LogManager.getLogger(FindCarCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String searchParameter = request.getParameter(RequestParameter.SEARCH_PARAMETER);
        String brand = request.getParameter(RequestParameter.BRAND);
        String color = request.getParameter(RequestParameter.COLOR);
        String boxType = request.getParameter(RequestParameter.BOX_TYPE);
        String engineType = request.getParameter(RequestParameter.ENGINE_TYPE);
        OrderService service = new OrderServiceImpl();
        String page = PageType.ERROR.getPath();
        session.setAttribute(AttributeKey.IS_FIRST_PAGE, true);
        try {
            Optional<List<Order>> orders = service.findOrdersByParameters(searchParameter, brand, color, boxType,
                    engineType);
            if (!orders.isPresent()) {
                request.setAttribute(AttributeKey.INCORRECT_PARAMETER, true);
                request.setAttribute(RequestParameter.SEARCH_PARAMETER, searchParameter);
            } else {
                int toIndex = Constant.NUMBER_OF_ORDER_PER_PAGE;
                if (orders.get().size() <= Constant.NUMBER_OF_ORDER_PER_PAGE) {
                    toIndex = orders.get().size();
                    session.setAttribute(RequestParameter.HAS_NEXT_PAGE, false);
                }
                List<Order> ordersPerPage = (orders.get().size() == 1) ? orders.get() : orders.get().subList(0, toIndex);
                session.setAttribute(AttributeKey.ORDER_PER_PAGE, ordersPerPage);
                session.setAttribute(AttributeKey.ORDER_LIST, orders.get());
                session.setAttribute(AttributeKey.TO_INDEX, toIndex);
                session.setAttribute(AttributeKey.FROM_INDEX, 0);
            }
            page = PageType.ADMIN_SHOW_ORDER.getPath();
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
        return page;
    }
}
