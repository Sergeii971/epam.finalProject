package com.verbovskiy.finalproject.controller.command.impl;

import com.verbovskiy.finalproject.controller.AttributeKey;
import com.verbovskiy.finalproject.controller.command.ActionCommand;
import com.verbovskiy.finalproject.controller.command.Constant;
import com.verbovskiy.finalproject.controller.command.PageType;
import com.verbovskiy.finalproject.controller.command.RequestParameter;
import com.verbovskiy.finalproject.exception.ServiceException;
import com.verbovskiy.finalproject.model.entity.Car;
import com.verbovskiy.finalproject.model.service.CarService;
import com.verbovskiy.finalproject.model.service.impl.CarServiceImpl;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

public class FindCarCommand implements ActionCommand {
    private final Logger logger = LogManager.getLogger(FindCarCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String searchParameter = request.getParameter(RequestParameter.SEARCH_PARAMETER);
        String fromPrice = request.getParameter(RequestParameter.FROM_PRICE);
        String toPrice = request.getParameter(RequestParameter.TO_PRICE);
        String brand = request.getParameter(RequestParameter.BRAND);
        String color = request.getParameter(RequestParameter.COLOR);
        String boxType = request.getParameter(RequestParameter.BOX_TYPE);
        String engineType = request.getParameter(RequestParameter.ENGINE_TYPE);
        boolean isAdmin = (boolean) session.getAttribute(RequestParameter.IS_ADMIN);
        CarService service = new CarServiceImpl();
        String page = PageType.ERROR.getPath();
        session.setAttribute(AttributeKey.IS_FIRST_PAGE, true);
        try {
            Optional<List<Car>> cars = service.findCarsByParameters(searchParameter, fromPrice, toPrice,
                    brand, color, boxType, engineType, isAdmin);
            if (!cars.isPresent()) {
                session.setAttribute(AttributeKey.INCORRECT_PARAMETER, true);
                request.setAttribute(RequestParameter.SEARCH_PARAMETER, searchParameter);
                request.setAttribute(RequestParameter.TO_PRICE, toPrice);
                request.setAttribute(RequestParameter.FROM_PRICE, fromPrice);
            } else {
                int toIndex = Constant.NUMBER_OF_CAR_PER_PAGE;
                if (cars.get().size() <= Constant.NUMBER_OF_CAR_PER_PAGE) {
                    toIndex = cars.get().size();
                    session.setAttribute(RequestParameter.HAS_NEXT_PAGE, false);
                }
                List<Car> carsPerPage;
                carsPerPage = (cars.get().size() == 1) ? cars.get() : cars.get().subList(0, toIndex);
                session.setAttribute(AttributeKey.CAR_PER_PAGE, carsPerPage);
                session.setAttribute(AttributeKey.CAR_LIST, cars.get());
                session.setAttribute(AttributeKey.TO_INDEX, toIndex);
                session.setAttribute(AttributeKey.FROM_INDEX, 0);
            }
            page = PageType.SHOW_CAR.getPath();
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
        return page;
    }
}
