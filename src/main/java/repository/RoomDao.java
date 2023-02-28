package repository;

import db.DbConnectionManager;
import domainModell.room.Room;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * DAO for the persistent handling of a Room object. It manages all
 * CRUD operations and conversion between the object world student and
 * the relational version room (DB version).
 * Due to the use of a DbConnectionManager the DAO doesn't need to
 * use, or even know, about any of lower level connections to the Database.
 * It 'speaks' in Objects with the object world (Domain model)and in
 * relational sql strings, tables, columns and result sets with the database.
 * @author awi
 *
 */
public class RoomDao implements Dao<Room> {

    DbConnectionManager dbConManagerSingleton = null;
    public RoomDao() {
        this(new DbConnectionManager());
        dbConManagerSingleton = DbConnectionManager.getInstance();
    }
    public RoomDao(DbConnectionManager dbConnectionManager){
        this.dbConManagerSingleton = dbConnectionManager;

    }

    @Override
    public Room get(int id) throws NoSuchElementException {
        Room room = null;
        try{
            ResultSet resultSet = dbConManagerSingleton.excecuteQuery("SELECT id, room_number, room_area," +
                    " room_type FROM rooms WHERE id=" + id);
            if( !resultSet.next())
                throw new NoSuchElementException("The room with id " + id + " doesen't exist in database");
            else
                room = new Room(resultSet.getInt(1),
                        resultSet.getInt(2),
                        resultSet.getDouble(3),
                        resultSet.getString(4));
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return room;
    }

    @Override
    public List<Room> getAll() {
        ArrayList<Room> list = new ArrayList<>();
        try {
            ResultSet resultSet = dbConManagerSingleton.excecuteQuery("SELECT id, room_number, room_area," +
                    " room_type FROM rooms");
            while (resultSet.next()) {
                list.add(new Room(resultSet.getInt(1),
                        resultSet.getInt(2),
                        resultSet.getDouble(3),
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
    public Room save(Room t) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet;
        try {
            preparedStatement = dbConManagerSingleton.prepareStatement(
                    "INSERT INTO rooms (room_number, room_area, room_type) " +
                            "VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, t.getRoomNumber());
            preparedStatement.setDouble(2, t.getRoomArea());
            preparedStatement.setString(3, t.getRoomType());
            int rowsAffected = preparedStatement.  executeUpdate();
            if(rowsAffected == 1) {
                resultSet = preparedStatement.getGeneratedKeys();
                resultSet.next();
                return new Room(resultSet.getInt(1),t.getRoomNumber(), t.getRoomArea(), t.getRoomType());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return t;
    }

    public Room update(Room t) throws NoSuchElementException {
        PreparedStatement preparedStatement = null;
        int rowCount = 0;
        try {
            preparedStatement = dbConManagerSingleton.prepareStatement(
                    "UPDATE rooms SET room_number=?, room_area=?, room_type=? WHERE id=" + t.getId(), Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, t.getRoomNumber());
            preparedStatement.setDouble(2, t.getRoomArea());
            preparedStatement.setString(3, t.getRoomType());
            rowCount = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (rowCount == 0)
            throw new NoSuchElementException("Room with ID " + t.getId() + " does not exist in the database.");
        return new Room(t.getId(), t.getRoomNumber(), t.getRoomArea(), t.getRoomType());
    }

    @Override
    public boolean delete(int id) {
        PreparedStatement preparedStatement = null;
        int rowCount = 0;
        try {
            preparedStatement = dbConManagerSingleton.prepareStatement(
                    "DELETE FROM rooms WHERE id = ?", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, id);
            rowCount = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (rowCount == 0) {
            throw new NoSuchElementException("Room with ID " + id + " does not exist in the database.");
        }
        return true;
    }
}
