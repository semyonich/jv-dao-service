package core.basesyntax.db;

import core.basesyntax.model.Car;
import core.basesyntax.model.Driver;
import core.basesyntax.model.Manufacturer;

import java.util.ArrayList;
import java.util.List;

public class Storage {
    private static final List<Driver> driversList = new ArrayList<>();
    private static final List<Manufacturer> manufacturerList = new ArrayList<>();
    private static final List<Car> carList = new ArrayList<>();
}
