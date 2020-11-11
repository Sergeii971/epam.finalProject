package com.verbovskiy.finalproject.controller.command.impl;

import com.verbovskiy.finalproject.controller.AttributeKey;
import com.verbovskiy.finalproject.controller.command.ActionCommand;
import com.verbovskiy.finalproject.controller.command.Constant;
import com.verbovskiy.finalproject.controller.command.PageType;
import com.verbovskiy.finalproject.controller.command.RequestParameter;
import com.verbovskiy.finalproject.model.entity.Car;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class CarPreviousPageCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        List<Car> allCars = (List<Car>) session.getAttribute(AttributeKey.CAR_LIST);
        int fromIndex = ((Integer) session.getAttribute(AttributeKey.FROM_INDEX)) - Constant.NUMBER_OF_CAR_PER_PAGE;
        int toIndex = ((Integer)session.getAttribute(AttributeKey.TO_INDEX));

        if (toIndex == allCars.size()) {
            toIndex -= allCars.size() % Constant.NUMBER_OF_CAR_PER_PAGE;
        } else {
            toIndex -= Constant.NUMBER_OF_CAR_PER_PAGE;
        }
        if (fromIndex < Constant.NUMBER_OF_CAR_PER_PAGE) {
            session.setAttribute(AttributeKey.IS_FIRST_PAGE, true);
            toIndex = Constant.NUMBER_OF_CAR_PER_PAGE;
            fromIndex = 0;
        }
        if (allCars.size() > toIndex) {
            session.setAttribute(RequestParameter.HAS_NEXT_PAGE, true);
        }
        List<Car> carsPerPage = allCars.subList(fromIndex, toIndex);
        session.setAttribute(AttributeKey.CAR_PER_PAGE, carsPerPage);
        session.setAttribute(AttributeKey.CAR_LIST, allCars);
        session.setAttribute(AttributeKey.TO_INDEX, toIndex);
        session.setAttribute(AttributeKey.FROM_INDEX, fromIndex);

        return PageType.SHOW_CAR.getPath();
    }
}
