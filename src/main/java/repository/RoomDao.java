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
import java.util.Optional;

/**
 * DAO for the persistent handling of a Room object. It manages all
 * CRUD operations and conversion between the object world student and
 * the relational version room (DB version).
 * Due to the use of a DbConnectionManager the DAO doesn't need to
 * use, or even know, about any of lower level connections to the Database.
 * It 'speaks' in Objects with the object world (Domain model)and in
 * relational sql strings, tables, columns and result sets with the database.
 *
 * @author awi
 */
public class RoomDao implements Dao<Room> {

    DbConnectionManager dbConManagerSingleton = null;

    public RoomDao() {
        this(new DbConnectionManager());
        dbConManagerSingleton = DbConnectionManager.getInstance();
    }

    public RoomDao(DbConnectionManager dbConnectionManager) {
        this.dbConManagerSingleton = dbConnectionManager;

    }

    @Override
    public Optional<Room> get(int id) throws NoSuchElementException, SQLException {
        Room room = null;
        ResultSet resultSet = dbConManagerSingleton.excecuteQuery("SELECT id, room_number, room_area," +
                " room_type FROM rooms WHERE id=" + id);
        if (resultSet.next()) {
            room = new Room(resultSet.getInt(1),
                    resultSet.getInt(2),
                    resultSet.getDouble(3),
                    resultSet.getString(4));
        }
        return Optional.ofNullable(room);
    }

    @Override
    public List<Room> getAll() throws SQLException {
        ArrayList<Room> list = new ArrayList<>();
        ResultSet resultSet = dbConManagerSingleton.excecuteQuery("SELECT id, room_number, room_area," +
                " room_type FROM rooms");
        while (resultSet.next()) {
            list.add(new Room(resultSet.getInt(1),
                    resultSet.getInt(2),
                    resultSet.getDouble(3),
                    resultSet.getString(4))
            );
        }
        return list;
    }

    @Override
    public Optional<Room> save(Room t) throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet;
        preparedStatement = dbConManagerSingleton.prepareStatement(
                "INSERT INTO rooms (room_number, room_area, room_type) " +
                        "VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setInt(1, t.getRoomNumber());
        preparedStatement.setDouble(2, t.getRoomArea());
        preparedStatement.setString(3, t.getRoomType());
        int rowsAffected = preparedStatement.executeUpdate();
        if (rowsAffected == 1) {
            resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();
            return Optional.of(new Room(resultSet.getInt(1), t.getRoomNumber(), t.getRoomArea(), t.getRoomType()));
        }
        return Optional.ofNullable(t);
    }

    public Optional<Room> update(Room room) throws NoSuchElementException, SQLException {
        PreparedStatement preparedStatement = null;
        int rowCount = 0;
        preparedStatement = dbConManagerSingleton.prepareStatement(
                "UPDATE rooms SET room_number=?, room_area=?, room_type=? WHERE id=" + room.getId(), Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setInt(1, room.getRoomNumber());
        preparedStatement.setDouble(2, room.getRoomArea());
        preparedStatement.setString(3, room.getRoomType());
        rowCount = preparedStatement.executeUpdate();
        return rowCount == 1 ? Optional.of(room) : Optional.empty();
    }

    @Override
    public Optional<Room> delete(int id) throws SQLException {
        Room room = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = dbConManagerSingleton.excecuteQuery("SELECT id, room_number, room_area," +
                " room_type FROM rooms WHERE id=" + id);
        if(resultSet.next()) {
            room = new Room(id,
                            resultSet.getInt(2),
                            resultSet.getDouble(3),
                            resultSet.getString(4));
            preparedStatement = dbConManagerSingleton.prepareStatement(
                    "DELETE FROM rooms WHERE id = ?", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        }
        return Optional.ofNullable(room);
    }
}
