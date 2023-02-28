/*package repository;

import db.DbConnectionManager;
import domainModell.location.Location;
import domainModell.person.Person;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class LocationDao implements Dao<Location> {

    DbConnectionManager dbConManagerSingleton = null;

    public LocationDao() {
        dbConManagerSingleton = DbConnectionManager.getInstance();
    }

    @Override
    public Location get(int id) throws NoSuchElementException {
        Location location = null;
        try {
            ResultSet resultSet = dbConManagerSingleton.excecuteQuery("SELECT id, location_name, location_address FROM locations WHERE id=" + id);
            if (!resultSet.next())
                throw new NoSuchElementException("The location with id " + id + " doesen't exist in database");
            else
                location = new Location(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4));
            dbConManagerSingleton.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return location;
    }

    @Override
    public List<Location> getAll() {
        ArrayList<Location> list = new ArrayList<>();
        try {
            ResultSet resultSet = dbConManagerSingleton.excecuteQuery("SELECT id, location_name, location_address FROM locations");
            while (resultSet.next()) {
                list.add(new Location(resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4))
                );
            }
            dbConManagerSingleton.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Location save(Location t) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet;
        try {
            preparedStatement = dbConManagerSingleton.prepareStatement(
                    "INSERT INTO locations (location_name, location_address) " +
                            "VALUES (?, ?)");
            preparedStatement.setString(1, t.getAddress());
            preparedStatement.setString(2, t.getCity());
            preparedStatement.setString(3, t.getCountry());
            int rowsAffected = preparedStatement.executeUpdate();
            if(rowsAffected == 1){
                resultSet = preparedStatement.getGeneratedKeys();
                resultSet.next();
                return new Location(resultSet.getInt(1), t.getAddress(),t.getCity(),t.getCountry());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return t;
    }

    public void update(Location t) throws NoSuchElementException {
        PreparedStatement preparedStatement = null;
        int rowCount = 0;
        try {
            preparedStatement = dbConManagerSingleton.prepareStatement(
                    "UPDATE locations SET location_name=?, location_address=? WHERE id=" + t.getId());
            preparedStatement.setString(1, t.getAddress());
            preparedStatement.setString(2, t.getCity());
            preparedStatement.setString(3, t.getCountry());
            rowCount = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (rowCount == 0) {
            throw new NoSuchElementException("Location with ID " + t.getId() + " does not exist in the database.");
        }
    }

    @Override
    public void delete(int id) {
        PreparedStatement preparedStatement = null;
        int rowCount = 0;
        try {
            preparedStatement = dbConManagerSingleton.prepareStatement(
                    "DELETE FROM locations WHERE id = ?");
            preparedStatement.setInt(1, id);
            rowCount = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (rowCount == 0) {
            throw new NoSuchElementException("Location with ID " + id + " does not exist in the database.");
        }
    }
}
*/