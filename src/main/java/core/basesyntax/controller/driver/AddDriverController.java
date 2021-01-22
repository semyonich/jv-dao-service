package core.basesyntax.controller.driver;

import core.basesyntax.lib.Injector;
import core.basesyntax.model.Driver;
import core.basesyntax.service.DriverService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddDriverController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("core.basesyntax");
    private final DriverService driverService = (DriverService) injector
            .getInstance(DriverService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/drivers/add.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String driverName = req.getParameter("driver_name");
        String licenseNumber = req.getParameter("license_number");
        String login = req.getParameter("driver_login");
        String password = req.getParameter("driver_password");
        String repeatPassword = req.getParameter("repeat_password");
        if (password.equals(repeatPassword)) {
            driverService.create(new Driver(driverName, licenseNumber, login, password));
            resp.sendRedirect(req.getContextPath() + "/");
            return;
        }
        req.setAttribute("errorMessage", "Passwords are not equal");
        req.getRequestDispatcher("/WEB-INF/views/drivers/add.jsp").forward(req, resp);
    }
}
