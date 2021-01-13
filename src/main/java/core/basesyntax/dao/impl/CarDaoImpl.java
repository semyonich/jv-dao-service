package core.basesyntax.dao.impl;

import core.basesyntax.dao.CarDao;
import core.basesyntax.db.Storage;
import core.basesyntax.lib.Dao;
import core.basesyntax.model.Car;
import core.basesyntax.model.Driver;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Dao
public class CarDaoImpl implements CarDao {
    @Override
    public Car create(Car car) {
        return Storage.addCar(car);
    }

    @Override
    public Optional<Car> get(Long id) {
        return Storage.cars.stream()
                .filter(m -> m.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<Car> getAll() {
        return Storage.cars;
    }

    @Override
    public Car update(Car car) {
        for (int i = 0; i < Storage.cars.size(); i++) {
            if (Storage.cars.get(i).getId().equals(car.getId())) {
                Storage.cars.set(i, car);
                break;
            }
        }
        return car;
    }

    @Override
    public boolean delete(Long id) {
        return Storage.cars.removeIf(i -> i.getId().equals(id));
    }

    @Override
    public List<Car> getAllByDriver(Long id) {
        List<Car> driverCars = new ArrayList<>();
        for (Car car : Storage.cars) {
            for (Driver driver : car.getDrivers()) {
                if (driver.getId().equals(id)) {
                    driverCars.add(car);
                }
            }
        }
        return driverCars;
    }
}
