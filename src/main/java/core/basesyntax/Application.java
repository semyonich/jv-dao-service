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
        Manufacturer daewoo = new Manufacturer("Daewoo", "Ukraine");
        Manufacturer tesla = new Manufacturer("Tesla", "USA");
        manufacturerService.create(daewoo);
        manufacturerService.create(tesla);
        System.out.println("Manufacturers list: " + manufacturerService.getAll());
        System.out.println("Manufacturer with id=1: "
                + manufacturerService.get(1L));
        Manufacturer newManufacturer = new Manufacturer("Toyota", "Japan");
        newManufacturer.setId(1L);
        System.out.println("Updated manufacturer with id=1: "
                + manufacturerService.update(newManufacturer));
        System.out.println("New manufacturer list: " + manufacturerService.getAll());
        System.out.println("Deleting manufacturer with id=2: "
                + manufacturerService.delete(2L));
        System.out.println("Manufacturer list after deleting id=2: "
                + manufacturerService.getAll());
        /* Drivers testing*/
        DriverService driverService = (DriverService) injector
                .getInstance(DriverService.class);
        Driver alice = new Driver("Alice", "123456");
        Driver bruce = new Driver("Bruce", "654321");
        driverService.create(alice);
        driverService.create(bruce);
        System.out.println("Driver with id=1: " + driverService.get(1L));
        System.out.println("All drivers are: " + driverService.getAll());
        Driver john = new Driver("John", "987654");
        john.setId(1L);
        driverService.update(john);
        System.out.println("All drivers after update are: " + driverService.getAll());
        driverService.create(alice);
        System.out.println("Deleting driver with iq=2: " + driverService.delete(2L));
        System.out.println("Driver list after deleting id=2: " + driverService.getAll());
        /* Cars testing*/
        CarService carService = (CarService) injector
                .getInstance(CarService.class);
        Car daewooLanos = new Car("Lanos", daewoo);
        Car teslaS = new Car("S", tesla);
        Manufacturer bmw = new Manufacturer("BMW", "Germany");
        Car bmwX6 = new Car("X6", bmw);
        carService.create(daewooLanos);
        carService.create(teslaS);
        carService.create(bmwX6);
        System.out.println("Car with id=1 is: " + carService.get(1L));
        System.out.println("Cars list: " + carService.getAll());
        Car daewooNexia = new Car("Nexia", daewoo);
        daewooNexia.setId(2L);
        System.out.println(carService.update(daewooNexia));
        System.out.println("After updating car with id=2: " + carService.getAll());
        System.out.println("Deleting car with iq=1: " + carService.delete(1L));
        System.out.println("Car list after deleting id=1: " + carService.getAll());
        carService.addDriverToCar(driverService.get(1L), daewooNexia);
        carService.addDriverToCar(driverService.get(1L), bmwX6);
        carService.addDriverToCar(driverService.get(3L), daewooNexia);
        System.out.println("Cars with drivers are: " + carService.getAll());
        System.out.println("Cars with driver with id=1 are: " + carService.getAllByDriver(1L));
        System.out.println("Cars with driver with id=3 are: " + carService.getAllByDriver(3L));
        carService.removeDriverFromCar(driverService.get(1L), carService.get(2L));
        System.out.println("After remove driver(id=1) from car(id=2) cars are: "
                + carService.getAll());
    }
}
