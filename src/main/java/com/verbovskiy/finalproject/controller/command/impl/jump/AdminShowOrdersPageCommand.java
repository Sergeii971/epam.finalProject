package com.verbovskiy.finalproject.controller.command.impl.jump;

import com.verbovskiy.finalproject.controller.AttributeKey;
import com.verbovskiy.finalproject.controller.command.ActionCommand;
import com.verbovskiy.finalproject.controller.command.Constant;
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

public class AdminShowOrdersPageCommand implements ActionCommand {
    private final Logger logger = LogManager.getLogger(AdminShowOrdersPageCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute(RequestParameter.HAS_PREVIOUS_PAGE, false);
        session.setAttribute(AttributeKey.IS_FIRST_PAGE, true);
        OrderService service = new OrderServiceImpl();
        String page = PageType.ERROR.getPath();
        session.setAttribute(RequestParameter.HAS_NEXT_PAGE, true);

        try {
            List<UserOrder> allOrders = service.findAllOrders();
            if (allOrders == null || allOrders.isEmpty()) {
                request.setAttribute(RequestParameter.IS_EMPTY, true);
                session.setAttribute(RequestParameter.HAS_NEXT_PAGE, false);
            } else {
                int toIndex = Constant.NUMBER_OF_ORDER_PER_PAGE;
                if (allOrders.size() <= Constant.NUMBER_OF_ORDER_PER_PAGE) {
                    toIndex = allOrders.size();
                    session.setAttribute(RequestParameter.HAS_NEXT_PAGE, false);
                }
                List<UserOrder> ordersPerPage;
                ordersPerPage = (allOrders.size() == 1) ? allOrders : allOrders.subList(0, toIndex);
                session.setAttribute(AttributeKey.ORDER_PER_PAGE, ordersPerPage);
                session.setAttribute(AttributeKey.ORDER_LIST, allOrders);
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
