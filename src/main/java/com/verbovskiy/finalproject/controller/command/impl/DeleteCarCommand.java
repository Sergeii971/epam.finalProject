package com.verbovskiy.finalproject.controller.command.impl;

import com.verbovskiy.finalproject.controller.AttributeKey;
import com.verbovskiy.finalproject.controller.command.ActionCommand;
import com.verbovskiy.finalproject.controller.command.PageType;
import com.verbovskiy.finalproject.controller.command.RequestParameter;
import com.verbovskiy.finalproject.exception.ServiceException;
import com.verbovskiy.finalproject.model.entity.Car;
import com.verbovskiy.finalproject.model.entity.UserOrder;
import com.verbovskiy.finalproject.model.service.CarService;
import com.verbovskiy.finalproject.model.service.OrderService;
import com.verbovskiy.finalproject.model.service.impl.CarServiceImpl;
import com.verbovskiy.finalproject.model.service.impl.OrderServiceImpl;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * The type delete car command.
 *
 * @author Verbovskiy Sergei
 * @version 1.0
 */
public class DeleteCarCommand implements ActionCommand {
    private final Logger logger = LogManager.getLogger(DeleteCarCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        long carId = Long.parseLong(request.getParameter(RequestParameter.CAR_ID));
        String page = PageType.ERROR.getPath();

        try {
            OrderService orderService = new OrderServiceImpl();
            Optional<UserOrder> order =orderService.findByCarId(carId);
            if (order.isPresent()) {
                request.setAttribute(RequestParameter.IN_ORDER_LIST, true);
            } else {
                int toIndex = (int) session.getAttribute(AttributeKey.TO_INDEX);
                int fromIndex = (int) session.getAttribute(AttributeKey.FROM_INDEX);
                CarService service = new CarServiceImpl();
                service.remove(carId);
                List<Car> allCars = service.findAllCars();
                if (allCars.size() == 0) {
                    request.setAttribute(RequestParameter.IS_EMPTY, true);
                    session.setAttribute(AttributeKey.CAR_PER_PAGE, new ArrayList<>());
                } else {
                    if (allCars.size() <= toIndex) {
                        toIndex = allCars.size();
                        session.setAttribute(RequestParameter.HAS_NEXT_PAGE, false);
                    }
                    List<Car> carsPerPage = allCars.subList(fromIndex, toIndex);
                    session.setAttribute(AttributeKey.CAR_LIST, allCars);
                    session.setAttribute(AttributeKey.CAR_PER_PAGE, carsPerPage);
                    session.setAttribute(AttributeKey.TO_INDEX, toIndex);
                    session.setAttribute(AttributeKey.FROM_INDEX, fromIndex);
                }
            }
            page = PageType.SHOW_CAR.getPath();
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
        return page;
    }
}
