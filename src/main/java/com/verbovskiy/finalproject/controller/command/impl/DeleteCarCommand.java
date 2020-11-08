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
import java.util.ArrayList;
import java.util.List;

public class DeleteCarCommand implements ActionCommand {
    private final Logger logger = LogManager.getLogger(DeleteCarCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        CarService service = new CarService();
        long carId = Long.parseLong(request.getParameter(RequestParameter.CAR_ID));
        int toIndex = (int) session.getAttribute(AttributeKey.TO_INDEX);
        int fromIndex = (int) session.getAttribute(AttributeKey.FROM_INDEX);
        String page = PageType.ERROR.getPath();

        try {
            service.remove(carId);
            List<Car> allCars = service.findAllCars();
            if (allCars.size() == 0) {
                request.setAttribute(RequestParameter.IS_EMPTY, true);
                session.setAttribute(AttributeKey.CAR_PER_PAGE, new ArrayList<>());
            } else {
                if (allCars.size() <= toIndex - 1) {
                    toIndex = allCars.size();
                    session.setAttribute(RequestParameter.HAS_NEXT_PAGE, false);
                }
                List<Car> carsPerPage = allCars.subList(fromIndex, toIndex);
                session.setAttribute(AttributeKey.CAR_LIST, allCars);
                session.setAttribute(AttributeKey.CAR_PER_PAGE, carsPerPage);
                session.setAttribute(AttributeKey.TO_INDEX, toIndex);
                session.setAttribute(AttributeKey.FROM_INDEX, fromIndex);
            }
            page = PageType.ADMIN_SHOW_CAR.getPath();
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
        return page;
    }
}
