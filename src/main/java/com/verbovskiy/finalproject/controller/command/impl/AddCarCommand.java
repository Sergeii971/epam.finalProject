package com.verbovskiy.finalproject.controller.command.impl;

import com.verbovskiy.finalproject.controller.AttributeKey;
import com.verbovskiy.finalproject.controller.command.ActionCommand;
import com.verbovskiy.finalproject.controller.command.PageType;
import com.verbovskiy.finalproject.controller.command.RequestParameter;
import com.verbovskiy.finalproject.exception.ServiceException;
import com.verbovskiy.finalproject.model.service.CarService;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.Map;

public class AddCarCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger(AddUserCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        CarService service = new CarService();
        HttpSession session = request.getSession();
        String brand = request.getParameter(RequestParameter.BRAND);
        String price = request.getParameter(RequestParameter.PRICE);
        String description = request.getParameter(RequestParameter.DESCRIPTION);
        String imageName = (String) session.getAttribute(RequestParameter.IMAGE_NAME);
        String model = request.getParameter(RequestParameter.MODEL);
        String manufactureYear = request.getParameter(RequestParameter.MANUFACTURE_YEAR);
        String color = request.getParameter(RequestParameter.COLOR);
        String engineType = request.getParameter(RequestParameter.ENGINE_TYPE);
        String boxType = request.getParameter(RequestParameter.BOX_TYPE);
        boolean isAvailable = true;
        LocalDate addedDate = LocalDate.now();
        String page = PageType.ERROR.getPath();

        try {
            Map<String, Boolean> incorrectParameter = service.add(brand, price, description, imageName, isAvailable,
                    addedDate, model, manufactureYear, color, boxType, engineType);
            if (incorrectParameter.size() != 0) {
                request.setAttribute(RequestParameter.PRICE, price);
                request.setAttribute(RequestParameter.IMAGE_NAME, imageName);
                request.setAttribute(RequestParameter.DESCRIPTION, description);
                request.setAttribute(RequestParameter.MODEL, model);
                request.setAttribute(RequestParameter.MANUFACTURE_YEAR, manufactureYear);
                request.setAttribute(AttributeKey.INCORRECT_PARAMETER, incorrectParameter);
            } else {
                session.removeAttribute(RequestParameter.IMAGE_NAME);
            }
            page = PageType.ADD_CAR.getPath();
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
        return page;
    }
}
