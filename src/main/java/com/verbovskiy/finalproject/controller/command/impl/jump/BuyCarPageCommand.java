package com.verbovskiy.finalproject.controller.command.impl.jump;

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

public class BuyCarPageCommand implements ActionCommand {
    private final Logger logger = LogManager.getLogger(BuyCarPageCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        long carId = Long.parseLong(request.getParameter(RequestParameter.CAR_ID));
        CarService service = new CarServiceImpl();
        String page = PageType.ERROR.getPath();

        try {
            Car car = service.findById(carId);
            request.setAttribute(RequestParameter.CAR_ID, carId);
            request.setAttribute(RequestParameter.BRAND, car.getBrand().getBrand());
            request.setAttribute(RequestParameter.ADDED_DATE, car.getAddedDate());
            request.setAttribute(RequestParameter.ENGINE_TYPE, car.getEngineType().getEngine());
            request.setAttribute(RequestParameter.BOX_TYPE, car.getBoxType().getBox());
            request.setAttribute(RequestParameter.COLOR, car.getColor().getColor());
            request.setAttribute(RequestParameter.PRICE, car.getPrice());
            request.setAttribute(RequestParameter.IMAGE_NAME, car.getImageName());
            request.setAttribute(RequestParameter.DESCRIPTION, car.getDescription());
            request.setAttribute(RequestParameter.MODEL, car.getModel());
            request.setAttribute(RequestParameter.MANUFACTURE_YEAR, car.getManufactureYear());
            request.setAttribute(RequestParameter.IS_BOUGHT, false);
            page = PageType.BUY_CAR.getPath();
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
        return page;
    }
}
