package repository;

public class DaoFactory {
    public PersonDao getPersonDao(){
        return new PersonDao();
    }
    public RoomDao getRoomDao(){
        return new RoomDao();
    }
    public Dao get(type type){
        return switch (type) {
            case PERSON -> new PersonDao();
            case ROOM -> new RoomDao();
            case SITE -> new SiteDao();
        };
    }
    public enum type {PERSON, SITE, ROOM}
}
