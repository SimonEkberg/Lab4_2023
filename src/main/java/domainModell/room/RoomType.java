package domainModell.room;

public class RoomType {
    private String roomType;
    private enum RoomTypes{TOILET, LIVINGROOM, BEDROOM, WAREHOUSE;}

    public RoomType(String roomType) {
        this.roomType = roomTypefromString(roomType);
    }
    public String getRoomType(){
        return this.roomType;
    }
    public void setRoomType(String roomType){
        this.roomType = roomTypefromString(roomType);
    }
     private String roomTypefromString(String roomType) {
        if (roomType == null || roomType.trim().isEmpty())
            return "UNSPECIFIED";
        for (RoomTypes type : RoomTypes.values()) {
            if (roomType.equalsIgnoreCase("UNSPECIFIED") || type.toString().equalsIgnoreCase(roomType))
                return roomType.trim();
        }
        throw new IllegalArgumentException("Invalid room type: " + roomType);
    }
}
