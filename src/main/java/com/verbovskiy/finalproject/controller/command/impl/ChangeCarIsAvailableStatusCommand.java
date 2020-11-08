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

public class ChangeCarIsAvailableStatusCommand implements ActionCommand {
    private final Logger logger = LogManager.getLogger(ChangeCarIsAvailableStatusCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        CarService service = new CarService();
        String statusStringFormat = request.getParameter(RequestParameter.IS_AVAILABLE);
        boolean isAvailable = Boolean.parseBoolean(statusStringFormat);
        long carId = Long.parseLong(request.getParameter(RequestParameter.CAR_ID));
        String page = PageType.ERROR.getPath();

        try {
            service.updateIsAvailableStatus(carId, isAvailable);
            List<Car> allCars = service.findAllCars();
            int toIndex = (int) session.getAttribute(AttributeKey.TO_INDEX);
            int fromIndex = (int) session.getAttribute(AttributeKey.FROM_INDEX);
            List<Car> carsPerPage = allCars.subList(fromIndex, toIndex);
            session.setAttribute(AttributeKey.CAR_LIST, allCars);
            session.setAttribute(AttributeKey.CAR_PER_PAGE, carsPerPage);
            page = PageType.ADMIN_SHOW_CAR.getPath();
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Error while updating user status", e);
        }
        return page;
    }
}
