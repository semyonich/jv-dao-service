package core.basesyntax.dao.jdbc;

import core.basesyntax.dao.ManufacturerDao;
import core.basesyntax.exceptions.DataProcessingException;
import core.basesyntax.lib.Dao;
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
public class ManufacturerDaoJdbcImpl implements ManufacturerDao {
    @Override
    public Manufacturer create(Manufacturer manufacturer) {
        String createString = "INSERT INTO manufacturers (manufacturer_name, "
                + "manufacturer_country) VALUES (?,?);";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(createString,
                        Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, manufacturer.getName());
            statement.setString(2, manufacturer.getCountry());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                manufacturer.setId(resultSet.getObject(1, Long.class));
            }
            return manufacturer;
        } catch (SQLException e) {
            throw new DataProcessingException("Unable to CREATE manufacturer in DB "
                    + manufacturer, e);
        }
    }

    @Override
    public Optional<Manufacturer> get(Long id) {
        String getString = "SELECT * FROM manufacturers WHERE manufacturer_id=? AND deleted=false;";
        Manufacturer manufacturer = null;
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(getString)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                manufacturer = makeManufacturer(resultSet);
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Unable to GET manufacturer from DB, id=" + id, e);
        }
        return Optional.ofNullable(manufacturer);
    }

    @Override
    public List<Manufacturer> getAll() {
        String getAllString = "SELECT * FROM manufacturers WHERE deleted=false";
        List<Manufacturer> outputList = new ArrayList<>();
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(getAllString)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                outputList.add(makeManufacturer(resultSet));
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Unable to GET ALL manufacturers from DB", e);
        }
        return outputList;
    }

    @Override
    public Manufacturer update(Manufacturer manufacturer) {
        String updateString = "UPDATE manufacturers SET manufacturer_name=?, manufacturer_country=?"
                + " WHERE manufacturer_id=? AND deleted=false;";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(updateString)) {
            statement.setString(1, manufacturer.getName());
            statement.setString(2, manufacturer.getCountry());
            statement.setLong(3, manufacturer.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DataProcessingException("Unable to UPDATE manufacturer in DB: "
                    + manufacturer, e);
        }
        return manufacturer;
    }

    @Override
    public boolean delete(Long id) {
        String deleteString = "UPDATE manufacturers SET deleted=true WHERE manufacturer_id=?;";
        int result;
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(deleteString)) {
            statement.setLong(1, id);
            result = statement.executeUpdate();
        } catch (SQLException e) {
            throw new DataProcessingException("Unable to DELETE manufacturer from DB, id=" + id, e);
        }
        return result > 0;
    }

    private Manufacturer makeManufacturer(ResultSet resultSet) {
        try {
            String name = resultSet.getString("manufacturer_name");
            String country = resultSet.getString("manufacturer_country");
            Long manufacturerId = resultSet.getObject("manufacturer_id", Long.class);
            Manufacturer manufacturer = new Manufacturer(name, country);
            manufacturer.setId(manufacturerId);
            return manufacturer;
        } catch (SQLException e) {
            throw new DataProcessingException("Unable to make manufacturer from DB", e);
        }
    }
}
