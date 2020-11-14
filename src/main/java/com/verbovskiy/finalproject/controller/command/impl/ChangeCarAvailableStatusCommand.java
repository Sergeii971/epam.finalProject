package com.verbovskiy.finalproject.controller.command.impl;

import com.verbovskiy.finalproject.controller.AttributeKey;
import com.verbovskiy.finalproject.controller.command.ActionCommand;
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

/**
 * The type change car available status command.
 *
 * @author Verbovskiy Sergei
 * @version 1.0
 */
public class ChangeCarAvailableStatusCommand implements ActionCommand {
    private final Logger logger = LogManager.getLogger(ChangeCarAvailableStatusCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        long carId = Long.parseLong(request.getParameter(RequestParameter.CAR_ID));
        String page = PageType.ERROR.getPath();

        try {
            CarService carService = new CarServiceImpl();
            String statusStringFormat = request.getParameter(RequestParameter.IS_AVAILABLE);
            boolean isAvailable = Boolean.parseBoolean(statusStringFormat);
            carService.updateIsAvailableStatus(carId, isAvailable);
            List<Car> allCars = carService.findAllCars();
            int toIndex = (int) session.getAttribute(AttributeKey.TO_INDEX);
            int fromIndex = (int) session.getAttribute(AttributeKey.FROM_INDEX);
            List<Car> carsPerPage = allCars.subList(fromIndex, toIndex);
            session.setAttribute(AttributeKey.CAR_LIST, allCars);
            session.setAttribute(AttributeKey.CAR_PER_PAGE, carsPerPage);
            page = PageType.SHOW_CAR.getPath();
        } catch (ServiceException e) {
            logger.log(Level.ERROR,  e);
        }
        return page;
    }
}
