package repository;

import db.DbConnectionManager;
import domainModell.room.Room;

import java.sql.ResultSet;
import java.sql.SQLException;
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
 * @author Simon Siljamäki Ekberg
 */
public class RoomDao extends BasicDao<Room> implements Dao<Room> {
    public RoomDao() {
        super();
    }
    public RoomDao(DbConnectionManager dbConnectionManager) {
        super(dbConnectionManager);
    }
    @Override
    public Optional<Room> get(int id) throws NoSuchElementException, SQLException {
        return super.get("SELECT id, room_number, room_area, room_type FROM rooms WHERE id=" + id);
    }

    @Override
    public List<Room> getAll() throws SQLException {
        return super.getAll("SELECT id, room_number, room_area, room_type FROM rooms");
    }

    @Override
    public Optional<Room> save(Room room) throws SQLException {
        return super.save("INSERT INTO rooms (room_number, room_area, room_type) VALUES (?, ?, ?)",
                room.getRoomNumber(), room.getRoomArea(), room.getRoomType());
    }

    public Optional<Room> update(Room room) throws NoSuchElementException, SQLException {
      return super.update("UPDATE rooms SET room_number=?, room_area=?, room_type=? WHERE id=?",
              room.getRoomNumber(), room.getRoomArea(), room.getRoomType(), room.getId());
    }

    @Override
    public Optional<Room> delete(int id) throws SQLException {
        Optional<Room> room = super.get("SELECT id, room_number, room_area, room_type FROM rooms WHERE id=" + id);
        if(room.isEmpty())
            return room;
        super.delete("DELETE FROM rooms WHERE id=" +id);
        return room;
    }

    @Override
    protected Room convertResultSetToDomainObject(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        int number = resultSet.getInt("room_number");
        double area = resultSet.getDouble("room_area");
        String type = resultSet.getString("room_type");
        return new Room(id, number, area, type);
    }

    @Override
    protected Room convertFromSaveOrUpdateToDomainObject(Optional<ResultSet> resultSet, List<Object> argLlist) throws SQLException {
        int id;
        if (resultSet != null) {
            id = resultSet.get().getInt(1);
        } else
            id = (int) argLlist.get(3);
        int number = (int) argLlist.get(0);
        double area = (double) argLlist.get(1);
        String type = (String) argLlist.get(2);
        return new Room(id, number, area, type);
    }


}