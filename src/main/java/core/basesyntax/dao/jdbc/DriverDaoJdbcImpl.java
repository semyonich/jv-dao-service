package core.basesyntax.dao.jdbc;

import core.basesyntax.dao.DriverDao;
import core.basesyntax.exceptions.DataProcessingException;
import core.basesyntax.lib.Dao;
import core.basesyntax.model.Driver;
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
public class DriverDaoJdbcImpl implements DriverDao {
    @Override
    public Driver create(Driver driver) {
        String createString = "INSERT INTO drivers (driver_name, "
                + "driver_license) VALUES (?,?);";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(createString,
                        Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, driver.getName());
            statement.setString(2, driver.getLicenseNumber());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                driver.setId(resultSet.getObject(1, Long.class));
            }
            return driver;
        } catch (SQLException e) {
            throw new DataProcessingException("Unable to CREATE driver in DB " + driver, e);
        }
    }

    @Override
    public Optional<Driver> get(Long id) {
        String getString = "SELECT * FROM drivers WHERE driver_id=? AND deleted=false;";
        Driver driver = null;
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(getString)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                driver = makeDriver(resultSet);
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Unable to GET manufacturer from DB, id=" + id, e);
        }
        return Optional.ofNullable(driver);
    }

    @Override
    public List<Driver> getAll() {
        String getAllString = "SELECT * FROM drivers WHERE deleted=false";
        List<Driver> outputList = new ArrayList<>();
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(getAllString)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                outputList.add(makeDriver(resultSet));
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Unable to GET ALL drivers from DB", e);
        }
        return outputList;
    }

    @Override
    public Driver update(Driver driver) {
        String updateString = "UPDATE drivers SET driver_name=?, driver_license=?"
                + " WHERE driver_id=? AND deleted=false;";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(updateString)) {
            statement.setString(1, driver.getName());
            statement.setString(2, driver.getLicenseNumber());
            statement.setLong(3, driver.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DataProcessingException("Unable to UPDATE driver in DB: "
                    + driver, e);
        }
        return driver;
    }

    @Override
    public boolean delete(Long id) {
        String deleteString = "UPDATE drivers SET deleted=true WHERE driver_id=?;";
        int result;
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(deleteString)) {
            statement.setLong(1, id);
            result = statement.executeUpdate();
        } catch (SQLException e) {
            throw new DataProcessingException("Unable to DELETE driver from DB, id=" + id, e);
        }
        return result > 0;
    }

    private Driver makeDriver(ResultSet resultSet) {
        try {
            String name = resultSet.getString("driver_name");
            String driverLicense = resultSet.getString("driver_license");
            Long driverId = resultSet.getObject("driver_id", Long.class);
            Driver driver = new Driver(name, driverLicense);
            driver.setId(driverId);
            return driver;
        } catch (SQLException e) {
            throw new DataProcessingException("Unable to make driver from DB", e);
        }
    }
}
