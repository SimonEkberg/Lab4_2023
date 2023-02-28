package domainModell.room;

import domainModell.person.Person;

import java.util.Objects;

public class Room {
    private int id;
    private RoomArea roomArea;
    private RoomNumber roomNumber;
    private RoomType roomType;

    public Room(int id, int roomNumber, double roomArea, String roomType) {
        this.id = id;
        this.roomNumber = new RoomNumber(roomNumber);
        this.roomArea = new RoomArea(roomArea);
        this.roomType = new RoomType(roomType);

    }
    public Room(int roomNumber, double roomArea, String roomType){
        this(0,roomNumber,roomArea,roomType);
    }

    public int getRoomNumber(){
        return this.roomNumber.getRoomNumber();
    }

    public void setRoomNumber(int roomNumber){
        this.roomNumber = new RoomNumber(roomNumber);
    }
    public double getRoomArea(){
        return this.roomArea.getRoomArea();
    }
    public void setRoomArea(double roomArea){
        this.roomArea = new RoomArea(roomArea);
    }
    public String getRoomType(){
        return this.roomType.getRoomType();
    }
    public void setRoomType(String roomType){
        this.roomType = new RoomType(roomType);
    }

    public int getId(){
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { //if same object
            return true;
        }
        if (o == null || getClass() != o.getClass()) { //if null or not the same class
            return false;
        }
        Room room = (Room) o;
        return getId() == room.getId() &&
                getRoomNumber() == room.getRoomNumber() &&
                getRoomArea() == room.getRoomArea() &&
                getRoomType() == room.getRoomType();
    }

    @Override
    public String toString() {
        return "Room{" +
                "Room Id: " + id +
                ", Room Number: " + roomNumber.getRoomNumber() +
                ", Room Area: "+ roomArea.getRoomArea() +
                ", Room Type: "+ roomType.getRoomType() +
                '}';
    }
}
