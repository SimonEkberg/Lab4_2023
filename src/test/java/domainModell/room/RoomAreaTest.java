package domainModell.room;

import domainModell.person.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoomAreaTest {
    RoomArea instance;
    @BeforeEach
    public void setUp() {
        instance = new RoomArea(20.2);
    }
    @Test
    void getRoomArea() {
        System.out.println("getRoomArea");
        Double expectedResult = 20.2;
        Double result = instance.getRoomArea();
        assertEquals(expectedResult, result);
    }

    @Test
    void setRoomArea() {
        System.out.println("setRoomArea");
        Double setRoomArea = 99.9;
        Double expectedResult = 99.9;
        instance.setRoomArea(setRoomArea);
        Double result = instance.getRoomArea();
        assertEquals(expectedResult, result);
    }
    @Test
    void exceptionTestTooBigArea(){
        System.out.println("exceptionTestTooBigArea");
        Double excedsMaxRoomArea = 150.1;
        String exceptionExcedMaxRoomArea = "Room Area exceeds the maximum Room Area";
        assertThrows(IllegalArgumentException.class, () -> instance.setRoomArea(excedsMaxRoomArea),
               exceptionExcedMaxRoomArea);
    }
    @Test
    void exceptionTestTooSmallArea(){
        System.out.println("exceptionTestTooSmallArea");
        Double negativeRoomArea = -1.0;
        String exceptionNegativeRoomArea = "Room Area cannot be negative!";
        assertThrows(IllegalArgumentException.class, () -> instance.setRoomArea(negativeRoomArea),
                exceptionNegativeRoomArea);
    }
}