package com.verbovskiy.finalproject.controller.command.impl;

import com.verbovskiy.finalproject.controller.AttributeKey;
import com.verbovskiy.finalproject.controller.command.ActionCommand;
import com.verbovskiy.finalproject.controller.command.Constant;
import com.verbovskiy.finalproject.controller.command.PageType;
import com.verbovskiy.finalproject.controller.command.RequestParameter;
import com.verbovskiy.finalproject.model.entity.Car;
import com.verbovskiy.finalproject.model.entity.Order;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class OrderNextPageCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        List<Order> allOrders = (List<Order>) session.getAttribute(AttributeKey.ORDER_LIST);
        int fromIndex = ((Integer) session.getAttribute(AttributeKey.FROM_INDEX)) + Constant.NUMBER_OF_ORDER_PER_PAGE;
        int toIndex = ((Integer)session.getAttribute(AttributeKey.TO_INDEX)) + Constant.NUMBER_OF_ORDER_PER_PAGE;

        if (allOrders.size() <= toIndex) {
            toIndex = allOrders.size();
            session.setAttribute(RequestParameter.HAS_NEXT_PAGE, false);
        }
        List<Order> ordersPerPage = allOrders.subList(fromIndex, toIndex);
        session.setAttribute(AttributeKey.ORDER_PER_PAGE, ordersPerPage);
        session.setAttribute(AttributeKey.ORDER_LIST, allOrders);
        session.setAttribute(AttributeKey.TO_INDEX, toIndex);
        session.setAttribute(AttributeKey.FROM_INDEX, fromIndex);
        session.setAttribute(AttributeKey.IS_FIRST_PAGE, false);
        return PageType.SHOW_ORDER.getPath();
    }
}
