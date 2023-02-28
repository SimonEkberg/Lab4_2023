package domainModell.room;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoomNumberTest {
    RoomNumber instance;
    @BeforeEach
    public void setUp() {
        instance = new RoomNumber(8);
    }
    @Test
    void getRoomNumber() {
        System.out.println("getRoomNumber");
        int expectedResult = 8;
        int result = instance.getRoomNumber();
        assertEquals(expectedResult, result);
    }

    @Test
    void setRoomNumber() {
        System.out.println("setRoomNumber");
        int expectedResult = 4;
        int setRoomNumber = 4;
        instance.setRoomNumber(setRoomNumber);
        int result = instance.getRoomNumber();
        assertEquals(expectedResult, result);
    }

    @Test
    void exceptionSettingNegativeRoomNumber() {
        System.out.println("exceptionSettingNegativeRoomNumber");
        String expectedResult = "Room number cannot be negative";
        int setRoomNumber = -1;
        assertThrows(IllegalArgumentException.class, () -> instance.setRoomNumber(setRoomNumber),
                expectedResult);
    }
}