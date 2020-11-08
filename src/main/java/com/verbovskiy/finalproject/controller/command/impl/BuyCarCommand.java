package com.verbovskiy.finalproject.controller.command.impl;

import com.verbovskiy.finalproject.controller.command.ActionCommand;
import com.verbovskiy.finalproject.controller.command.PageType;
import com.verbovskiy.finalproject.controller.command.RequestParameter;
import com.verbovskiy.finalproject.exception.ServiceException;
import com.verbovskiy.finalproject.model.service.CarService;
import com.verbovskiy.finalproject.model.service.OrderService;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;

public class BuyCarCommand implements ActionCommand {
    private final Logger logger = LogManager.getLogger(BuyCarCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();

        long carId = Long.parseLong(request.getParameter(RequestParameter.CAR_ID));
        String userEmail = (String) session.getAttribute(RequestParameter.EMAIL);
        LocalDate date = LocalDate.now();
        boolean inProcessing = true;
        String page = PageType.ERROR.getPath();

        try {
            OrderService orderService = new OrderService();
            orderService.add(userEmail, carId, date, inProcessing);
            request.setAttribute(RequestParameter.IS_BOUGHT, true);
            page = PageType.BUY_CAR.getPath();
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
        return page;
    }
}
