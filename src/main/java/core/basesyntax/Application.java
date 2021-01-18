package core.basesyntax;

import core.basesyntax.lib.Injector;
import core.basesyntax.model.Car;
import core.basesyntax.model.Driver;
import core.basesyntax.model.Manufacturer;
import core.basesyntax.service.CarService;
import core.basesyntax.service.DriverService;
import core.basesyntax.service.ManufacturerService;

public class Application {
    private static final String PACKAGE_NAME = "core.basesyntax";
    private static Injector injector = Injector.getInstance(PACKAGE_NAME);

    public static void main(String[] args) {
        ManufacturerService manufacturerService = (ManufacturerService) injector
                .getInstance(ManufacturerService.class);
        manufacturerService.create(new Manufacturer("Daewoo", "Ukraine"));
        manufacturerService.create(new Manufacturer("Tesla", "USA"));
        manufacturerService.create(new Manufacturer("BMW", "Germany"));
        System.out.println("Manufacturers list: " + manufacturerService.getAll());
        System.out.println("Manufacturer with id=2: "
                + manufacturerService.get(2L));
        Manufacturer newManufacturer = new Manufacturer("Toyota", "Japan");
        newManufacturer.setId(2L);
        System.out.println("Updated manufacturer with id=2: "
                + manufacturerService.update(newManufacturer));
        System.out.println("New manufacturer list: " + manufacturerService.getAll());
        System.out.println("Deleting manufacturer with id=6: "
                + manufacturerService.delete(6L));
        System.out.println("Manufacturer list after deleting id=2: "
                + manufacturerService.getAll());
        Manufacturer tesla = new Manufacturer("Tesla", "USA");
        tesla.setId(2L);
        manufacturerService.update(tesla);
        System.out.println("Restoring initial state. Manufacturers are: "
                + manufacturerService.getAll());
        // Drivers testing*/
        DriverService driverService = (DriverService) injector
                .getInstance(DriverService.class);
        driverService.create(new Driver("Alice", "123456"));
        driverService.create(new Driver("Bruce", "654321"));
        driverService.create(new Driver("John", "987654"));
        driverService.create(new Driver("Donny", "11111"));
        System.out.println("Driver with id=1: " + driverService.get(1L));
        System.out.println("All drivers are: " + driverService.getAll());
        Driver john = new Driver("John", "987654");
        john.setId(2L);
        driverService.update(john);
        System.out.println("All drivers after update are: " + driverService.getAll());
        System.out.println("Deleting driver with iq=2: " + driverService.delete(2L));
        System.out.println("Driver list after deleting id=2: " + driverService.getAll());
        driverService.create(new Driver("Bruce", "654321")).setId(2L);
        System.out.println("Restoring initial state. Drivers are: " + driverService.getAll());
        /*Cars testing*/
        CarService carService = (CarService) injector
                .getInstance(CarService.class);
        System.out.println(carService.create(new Car("Lanos", manufacturerService.get(1L))));
        System.out.println(carService.create(new Car("Model S", manufacturerService.get(2L))));
        System.out.println(carService.create(new Car("X6", manufacturerService.get(3L))));
        System.out.println("Car with id=2 is: " + carService.get(2L));
        System.out.println("Cars list: " + carService.getAll());
        Car daewooNexia = new Car("Lanos", manufacturerService.get(1L));
        daewooNexia.setId(2L);
        daewooNexia.getDrivers().add(driverService.get(3L));
        daewooNexia.getDrivers().add(driverService.get(4L));
        carService.update(daewooNexia);
        System.out.println("After updating car with id=2: " + carService.getAll());
        System.out.println("Deleting car with iq=2: " + carService.delete(2L));
        System.out.println("Car list after deleting id=1: " + carService.getAll());
        carService.addDriverToCar(driverService.get(1L), carService.get(3L));
        carService.addDriverToCar(driverService.get(1L), carService.get(2L));
        carService.addDriverToCar(driverService.get(2L), carService.get(3L));
        System.out.println("Cars with drivers are: " + carService.getAll());
        System.out.println("Cars with driver with id=1 are: " + carService.getAllByDriver(1L));
        System.out.println("Cars with driver with id=4 are: " + carService.getAllByDriver(4L));
        carService.removeDriverFromCar(driverService.get(1L), carService.get(3L));
        System.out.println("After remove driver(id=1) from car(id=3) cars are: "
                + carService.getAll());
    }
}
