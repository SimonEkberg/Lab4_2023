package domainModell.site;

import domainModell.person.Person;
import domainModell.room.Room;

import java.util.ArrayList;
import java.util.List;

public class Site {
    private int id;
    private SiteName siteName;
    private List<Person> persons;
    private List<Room> rooms;

    public Site(int id, String siteName) {
        this.id = id;
        this.siteName = new SiteName(siteName);
        persons = new ArrayList<>();
        rooms = new ArrayList<>();
    }

    public String getSiteName() {
        return siteName.getName();
    }

    public void addPerson(Person person) {
        persons.add(person);
    }

    public void addRoom(Room room) {
        rooms.add(room);
    }

    public List<Person> getPersons() {
        return persons;
    }

    public List<Room> getRooms() {
        return rooms;
    }
}
