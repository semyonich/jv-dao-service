package core.basesyntax.controller;

import core.basesyntax.lib.Injector;
import core.basesyntax.model.Manufacturer;
import core.basesyntax.service.ManufacturerService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddManufacturerController extends HttpServlet {
    private static final String PACKAGE_NAME = "core.basesyntax";
    private static final Injector injector = Injector.getInstance(PACKAGE_NAME);
    private final ManufacturerService manufacturerService = (ManufacturerService) injector
            .getInstance(ManufacturerService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/manufacturers/addManufacturer.jsp")
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String manufacturerName = req.getParameter("manufacturerName");
        String country = req.getParameter("country");
        manufacturerService.create(new Manufacturer(manufacturerName, country));
        resp.sendRedirect(req.getContextPath() + "/");
    }
}
