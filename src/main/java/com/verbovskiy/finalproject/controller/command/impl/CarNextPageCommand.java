package com.verbovskiy.finalproject.controller.command.impl;

import com.verbovskiy.finalproject.controller.AttributeKey;
import com.verbovskiy.finalproject.controller.command.ActionCommand;
import com.verbovskiy.finalproject.controller.command.Constant;
import com.verbovskiy.finalproject.controller.command.PageType;
import com.verbovskiy.finalproject.controller.command.RequestParameter;
import com.verbovskiy.finalproject.exception.ServiceException;
import com.verbovskiy.finalproject.model.entity.Car;
import com.verbovskiy.finalproject.model.service.CarService;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class CarNextPageCommand implements ActionCommand {
    private final Logger logger = LogManager.getLogger(CarNextPageCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        List<Car> allCars = (List<Car>) session.getAttribute(AttributeKey.CAR_LIST);
        int fromIndex = ((Integer) session.getAttribute(AttributeKey.FROM_INDEX)) + Constant.NUMBER_OF_CAR_PER_PAGE;
        int toIndex = ((Integer)session.getAttribute(AttributeKey.TO_INDEX)) + Constant.NUMBER_OF_CAR_PER_PAGE;

        if (allCars.size() <= toIndex) {
            toIndex = allCars.size();
            session.setAttribute(RequestParameter.HAS_NEXT_PAGE, false);
        }
        List<Car> carsPerPage = allCars.subList(fromIndex, toIndex);
        session.setAttribute(AttributeKey.CAR_PER_PAGE, carsPerPage);
        session.setAttribute(AttributeKey.CAR_LIST, allCars);
        session.setAttribute(AttributeKey.TO_INDEX, toIndex);
        session.setAttribute(AttributeKey.FROM_INDEX, fromIndex);
        session.setAttribute(AttributeKey.IS_FIRST_PAGE, false);
        return PageType.ADMIN_SHOW_CAR.getPath();
    }
}
