package domainModell.room;

public class RoomNumber {

    private int roomNnumber;

    public RoomNumber(int roomNumber){
        setRoomNumber(roomNumber);
    }

    public int getRoomNumber() {
        return this.roomNnumber;
    }

    public void setRoomNumber(int roomNumber) {
        if(roomNumber < 0)
            throw new IllegalArgumentException("Room number cannot be negative");
        this.roomNnumber = roomNumber;
    }
}
