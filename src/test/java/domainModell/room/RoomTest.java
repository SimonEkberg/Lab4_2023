package domainModell.room;

import domainModell.person.Person;
import domainModell.room.Room;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoomTest {


    @Test
    void getRoomNumber() {
        System.out.println("getRoomNumber");
        Room instance = new Room(5,5,55.5, "LIVINGROOM");
        int expectedResult = 5;
        int result = instance.getRoomNumber();
        assertEquals(expectedResult, result);
    }

    @Test
    void setRoomNumber() {
        System.out.println("setRoomNumber");
        Room instance = new Room(5,5,55.5, "LIVINGROOM");
        int expectedResult = 8;
        int setRoomNumber = 8;
        instance.setRoomNumber(setRoomNumber);
        int result = instance.getRoomNumber();
        assertEquals(expectedResult, result);
    }
    @Test
    void getId() {
        System.out.println("getId");
        Room instance = new Room(88, 88, 88, "");
        int expectedResult = 88;
        int result = instance.getId();
        assertEquals(expectedResult, result);
    }

    @Test
    void getRoomArea(){
        System.out.println("getRoomArea");
        Room instance = new Room(5,5,55.5, "LIVINGROOM");
        Double expectedResult = 55.5;
        Double result = instance.getRoomArea();
        assertEquals(expectedResult, result);
    }

    @Test
    void setRoomArea(){
        System.out.println("setRoomArea");
        Room instance = new Room(5,5,55.5, "LIVINGROOM");
        Double expectedResult = 88.8;
        Double setRoomArea = 88.8;
        instance.setRoomArea(setRoomArea);
        Double result = instance.getRoomArea();
        assertEquals(expectedResult, result);
    }
    @Test
    void getRoomType(){
        System.out.println("getRoomType");
        Room instance = new Room(5,5,55.5, "LIVINGROOM");
        String expectedResultLivingroom = "LIVINGROOM";
        String expectedResultUnspecified = "UNSPECIFIED";
        String resultLivingroom = instance.getRoomType();
        assertEquals(expectedResultLivingroom, resultLivingroom);
        instance.setRoomType(expectedResultUnspecified);
        String resultUnspecified = instance.getRoomType();
        assertEquals(expectedResultUnspecified, resultUnspecified);
    }
    @Test
    void setRoomType(){
        System.out.println("setRoomType");
        Room instance = new Room(5,5,55.5, "LIVINGROOM");
        String expectedResult = "BEDROOM";
        String setRoomType = "BEDROOM";
        instance.setRoomType(setRoomType);
        String result = instance.getRoomType();
        assertEquals(expectedResult,result);
    }
    @Test
    void setRoomTypeBlanc(){
        System.out.println("setRoomTypeBlanc");
        Room instance = new Room(5,5,55.5, "LIVINGROOM");
        String expectedResult = "UNSPECIFIED";
        String setRoomType = "";
        instance.setRoomType(setRoomType);
        String result = instance.getRoomType();
        assertEquals(expectedResult,result);
    }
    @Test
    void exceptionSettingInvalidRoomType() {
        System.out.println("exceptionSettingInvalidRoomType");
        Room instance = new Room(5,5,55.5, "LIVINGROOM");
        String invalidRoomType = "InvalidoomType";
        assertThrows(IllegalArgumentException.class, () -> instance.setRoomType(invalidRoomType));

    }
    @Test
    void testToString() {
        Room instance = new Room(5, 5, 55.5, "LIVINGROOM");
        assertEquals("Room{Room Id: 5, Room Number: 5, Room Area: 55.5," +
                " Room Type: LIVINGROOM}", instance.toString());
    }
}