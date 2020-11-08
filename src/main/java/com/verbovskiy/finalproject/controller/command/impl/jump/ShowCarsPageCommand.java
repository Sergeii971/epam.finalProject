package com.verbovskiy.finalproject.controller.command.impl.jump;

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

public class ShowCarsPageCommand implements ActionCommand {
    private final Logger logger = LogManager.getLogger(ShowCarsPageCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        CarService service = new CarService();
        String page = PageType.ERROR.getPath();
        session.setAttribute(RequestParameter.HAS_NEXT_PAGE, true);
        boolean isAdmin = (boolean) session.getAttribute(RequestParameter.IS_ADMIN);

        try {
            List<Car> allCars = isAdmin ? service.findAllCars() : service.findAvailableCar();
            if (allCars == null || allCars.isEmpty()) {
                request.setAttribute(RequestParameter.IS_EMPTY, true);
            } else {
                int toIndex = Constant.NUMBER_OF_CAR_PER_PAGE;
                if (allCars.size() <= Constant.NUMBER_OF_CAR_PER_PAGE) {
                    toIndex = allCars.size();
                    session.setAttribute(RequestParameter.HAS_NEXT_PAGE, false);
                }
                List<Car> carsPerPage;
                carsPerPage = (allCars.size() == 1) ? allCars : allCars.subList(0, toIndex);
                session.setAttribute(AttributeKey.CAR_PER_PAGE, carsPerPage);
                session.setAttribute(AttributeKey.CAR_LIST, allCars);
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
