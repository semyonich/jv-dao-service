package core.basesyntax.db;

import core.basesyntax.model.Car;
import core.basesyntax.model.Driver;
import core.basesyntax.model.Manufacturer;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    public static final List<Manufacturer> manufacturers = new ArrayList<>();
    public static final List<Car> cars = new ArrayList<>();
    public static final List<Driver> drivers = new ArrayList<>();
    private static Long manufacturerId = 0L;
    private static Long carId = 0L;
    private static Long driverId = 0L;

    public static Manufacturer addManufacturer(Manufacturer manufacturer) {
        manufacturerId++;
        manufacturer.setId(manufacturerId);
        manufacturers.add(manufacturer);
        return manufacturer;
    }

    public static Car addCar(Car car) {
        carId++;
        car.setId(carId);
        cars.add(car);
        return car;
    }

    public static Driver addDriver(Driver driver) {
        driverId++;
        driver.setId(driverId);
        drivers.add(driver);
        return driver;
    }
}
