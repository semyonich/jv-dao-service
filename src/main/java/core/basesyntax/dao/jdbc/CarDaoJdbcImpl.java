package core.basesyntax.dao.jdbc;

import core.basesyntax.dao.CarDao;
import core.basesyntax.exceptions.DataProcessingException;
import core.basesyntax.lib.Dao;
import core.basesyntax.model.Car;
import core.basesyntax.model.Driver;
import core.basesyntax.model.Manufacturer;
import core.basesyntax.util.ConnectionUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Dao
public class CarDaoJdbcImpl implements CarDao {
    @Override
    public Car create(Car car) {
        String createString = "INSERT INTO cars (car_model, manufacturer_id"
                + ") VALUES (?,?);";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(createString,
                        Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, car.getModel());
            statement.setLong(2, car.getManufacturer().getId());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                car.setId(resultSet.getObject(1, Long.class));
            }
            return car;
        } catch (SQLException e) {
            throw new DataProcessingException("Unable to CREATE car in DB " + car, e);
        }
    }

    @Override
    public Optional<Car> get(Long id) {
        String getCarString = "SELECT c.car_id, car_model AS model, c.manufacturer_id, "
                + "manufacturer_name AS made_by, manufacturer_country AS country "
                + "FROM cars c "
                + "INNER JOIN manufacturers m "
                + "ON c.manufacturer_id=m.manufacturer_id "
                + "WHERE c.deleted=false AND c.car_id=?;";
        String getCarDrivers = "SELECT cd.driver_id, d.driver_name AS driver_name, "
                + "d.driver_license AS license "
                + "FROM cars_drivers cd "
                + "INNER JOIN drivers d "
                + "ON cd.driver_id=d.driver_id "
                + "WHERE car_id=? AND d.deleted=false;";
        Car car = null;
        List<Driver> drivers = new ArrayList<>();
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement carStatement = connection.prepareStatement(getCarString);
                PreparedStatement driversStatement = connection.prepareStatement(getCarDrivers)) {
            carStatement.setLong(1, id);
            driversStatement.setLong(1, id);
            ResultSet carResultSet = carStatement.executeQuery();
            while (carResultSet.next()) {
                car = makeCar(carResultSet);
            }
            ResultSet driverResultSet = driversStatement.executeQuery();
            while (driverResultSet.next()) {
                drivers.add(makeDriver(driverResultSet));
            }
            car.setDrivers(drivers);

        } catch (SQLException e) {
            throw new DataProcessingException("Unable to GET car from DB, id=" + id, e);
        }
        return Optional.ofNullable(car);
    }

    @Override
    public List<Car> getAll() {
        String getAllCarsString = "SELECT c.car_id, car_model AS model, c.manufacturer_id, "
                + "manufacturer_name AS made_by, manufacturer_country AS country "
                + "FROM cars c "
                + "INNER JOIN manufacturers m "
                + "ON c.manufacturer_id=m.manufacturer_id "
                + "WHERE c.deleted=false;";
        String getCarDrivers = "SELECT cd.driver_id, d.driver_name AS driver_name, "
                + "d.driver_license AS license "
                + "FROM cars_drivers cd "
                + "INNER JOIN drivers d "
                + "ON cd.driver_id=d.driver_id "
                + "WHERE car_id=? AND d.deleted=false;";
        List<Car> cars = new ArrayList<>();
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement carsStatement = connection.prepareStatement(getAllCarsString);
                PreparedStatement driversStatement = connection.prepareStatement(getCarDrivers)) {
            ResultSet carsResultSet = carsStatement.executeQuery();
            while (carsResultSet.next()) {
                Car car = makeCar(carsResultSet);
                List<Driver> drivers = new ArrayList<>();
                driversStatement.setLong(1, car.getId());
                ResultSet driverResultSet = driversStatement.executeQuery();
                while (driverResultSet.next()) {
                    drivers.add(makeDriver(driverResultSet));
                }
                car.setDrivers(drivers);
                cars.add(car);
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Unable to GET ALL cars from DB", e);
        }
        return cars;
    }

    @Override
    public Car update(Car car) {
        String updateCarString = "UPDATE cars SET car_id=?, manufacturer_id=?, car_model=?"
                + " WHERE car_id=? AND deleted=false;";
        String deleteLinksString = "DELETE FROM cars_drivers WHERE car_id=?;";
        String newLinksString = "INSERT INTO cars_drivers (car_id, driver_id"
                + ") VALUES (?,?);";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement carUpdateStatement = connection.prepareStatement(updateCarString);
                PreparedStatement deleteLinksStatement = connection
                        .prepareStatement(deleteLinksString);
                PreparedStatement newLinksStatement = connection.prepareStatement(newLinksString)) {
            carUpdateStatement.setLong(1, car.getId());
            carUpdateStatement.setLong(2, car.getManufacturer().getId());
            carUpdateStatement.setString(3, car.getModel());
            carUpdateStatement.setLong(4, car.getId());
            carUpdateStatement.executeUpdate();
            deleteLinksStatement.setLong(1,car.getId());
            deleteLinksStatement.executeUpdate();
            newLinksStatement.setLong(1, car.getId());
            for (Driver driver : car.getDrivers()) {
                newLinksStatement.setLong(2, driver.getId());
                newLinksStatement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Unable to UPDATE car in DB: "
                    + car, e);
        }
        return car;
    }

    @Override
    public boolean delete(Long id) {
        String deleteString = "UPDATE cars SET deleted=true WHERE car_id=?;";
        int result;
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(deleteString)) {
            statement.setLong(1, id);
            result = statement.executeUpdate();
        } catch (SQLException e) {
            throw new DataProcessingException("Unable to DELETE car from DB, id=" + id, e);
        }
        return result > 0;
    }

    @Override
    public List<Car> getAllByDriver(Long driverId) {
        String getCarsByDriverString = "SELECT car_id FROM cars_drivers WHERE driver_id=?;";
        List<Car> cars = new ArrayList<>();
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement getCarsStatement = connection
                        .prepareStatement(getCarsByDriverString)) {
            getCarsStatement.setLong(1, driverId);
            ResultSet carsResultSet = getCarsStatement.executeQuery();
            while (carsResultSet.next()) {
                cars.add(get(carsResultSet.getLong("car_id")).get());
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Unable to GET cars by driver_id from DB, id="
                    + driverId, e);
        }
        return cars;
    }

    private Car makeCar(ResultSet resultSet) {
        try {
            String manufacturerName = resultSet.getString("made_by");
            String manufacturerCountry = resultSet.getString("country");
            Long manufacturerId = resultSet.getObject("manufacturer_id", Long.class);
            String carModel = resultSet.getString("model");
            Long carId = resultSet.getObject("car_id", Long.class);
            Manufacturer manufacturer = new Manufacturer(manufacturerName, manufacturerCountry);
            manufacturer.setId(manufacturerId);
            Car car = new Car(carModel, manufacturer);
            car.setId(carId);
            return car;
        } catch (SQLException e) {
            throw new DataProcessingException("Unable to make manufacturer from DB", e);
        }
    }

    private Driver makeDriver(ResultSet resultSet) {
        try {
            String name = resultSet.getString("driver_name");
            String driverLicense = resultSet.getString("license");
            Long driverId = resultSet.getObject("driver_id", Long.class);
            Driver driver = new Driver(name, driverLicense);
            driver.setId(driverId);
            return driver;
        } catch (SQLException e) {
            throw new DataProcessingException("Unable to make driver from DB", e);
        }
    }
}
