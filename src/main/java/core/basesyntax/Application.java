package core.basesyntax;

import core.basesyntax.lib.Injector;
import core.basesyntax.service.CarService;
import core.basesyntax.service.DriverService;
import core.basesyntax.service.ManufacturerService;

public class Application {
    private static final String PACKAGE_NAME = "core.basesyntax";
    private static Injector injector = Injector.getInstance(PACKAGE_NAME);

    public static void main(String[] args) {
        ManufacturerService manufacturerService = (ManufacturerService) injector
                .getInstance(ManufacturerService.class);
        // Drivers testing*/
        DriverService driverService = (DriverService) injector
                .getInstance(DriverService.class);
        /*Cars testing*/
        CarService carService = (CarService) injector
                .getInstance(CarService.class);
    }
}
