package core.basesyntax.controller.car;

import core.basesyntax.lib.Injector;
import core.basesyntax.model.Car;
import core.basesyntax.model.Manufacturer;
import core.basesyntax.service.CarService;
import core.basesyntax.service.ManufacturerService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddCarController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("core.basesyntax");
    private final ManufacturerService manufacturerService = (ManufacturerService) injector
            .getInstance(ManufacturerService.class);
    private final CarService carService = (CarService) injector
            .getInstance(CarService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/cars/add.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String carModel = req.getParameter("car_model");
        Long manufacturerId = Long.valueOf(req.getParameter("manufacturer_id"));
        Manufacturer manufacturer = manufacturerService.get(manufacturerId);
        carService.create(new Car(carModel, manufacturer));
        resp.sendRedirect(req.getContextPath() + "/");
    }
}

