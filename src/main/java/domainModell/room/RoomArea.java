package domainModell.room;

public class RoomArea {
    private double roomArea;
    private int minRoomArea = 0;
    private int maxRoomArea = 150;

    public RoomArea(double roomArea){
        setRoomArea(roomArea);
    }

    public double getRoomArea(){
        return roomArea;
    }

    public void setRoomArea(double roomArea){
        if(roomArea < minRoomArea)
            throw  new IllegalArgumentException("Room Area cannot be negative!");
        if(roomArea > maxRoomArea)
            throw new IllegalArgumentException("Room Area exceeds the maximum Room Area");
        this.roomArea = roomArea;
    }

}
