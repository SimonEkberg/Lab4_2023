package domainModell.room;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RoomTypeTest {

    @Test
    public void getRoomTypeEmptyOrNull(){
        System.out.println("getRoomTypeEmptyOrNull");
        RoomType instance = new RoomType("");
        String expectedResultForNullOrEmpty = "UNSPECIFIED";
        String resultForEmptyOrNull = instance.getRoomType();
        assertEquals(expectedResultForNullOrEmpty,resultForEmptyOrNull);
        instance.setRoomType("     ");
        resultForEmptyOrNull = instance.getRoomType();
        assertEquals(expectedResultForNullOrEmpty,resultForEmptyOrNull);
        instance.setRoomType(null);
        resultForEmptyOrNull = instance.getRoomType();
        assertEquals(expectedResultForNullOrEmpty,resultForEmptyOrNull);
    }
    @Test
    public void getRoomType(){
        System.out.println("getRoomType");
        String expectedResultLivingroom = "LIVINGROOM";
        String expectedResultBedroom = "BEDROOM";
        String expectedResultToilet = "TOILET";
        String expectedResultWarehouse = "WAREHOUSE";

        RoomType instance = new RoomType(expectedResultLivingroom);
        String resulLivingroom = instance.getRoomType();
        assertEquals(expectedResultLivingroom,resulLivingroom);

        instance.setRoomType(expectedResultBedroom);
        String resultBedroom = instance.getRoomType();
        assertEquals(expectedResultBedroom,resultBedroom);

        instance.setRoomType(expectedResultToilet);
        String resultToilet = instance.getRoomType();
        assertEquals(expectedResultToilet,resultToilet);

        instance.setRoomType(expectedResultWarehouse);
        String resultWarehose = instance.getRoomType();
        assertEquals(expectedResultWarehouse,resultWarehose);
    }
    @Test
    public void testRoomTypeFromString_validInput() {
        System.out.println("testRoomTypeFromString_validInput");
        RoomType instance = new RoomType("");
        String expected = "BEDROOM";
        instance.setRoomType(expected);
        String result = instance.getRoomType();
        assertEquals(expected, result);
    }
    @Test
    public void testRoomTypeFromString_unspecified() {
        System.out.println("testRoomTypeFromString_unspecified");
        RoomType instance = new RoomType("UNSPECIFIED");
        String expected = "UNSPECIFIED";
        String result = instance.getRoomType();
        assertEquals(expected, result);
    }
    @Test
    public void testRoomTypeFromString_nullInput() {
        System.out.println("testRoomTypeFromString_nullInput");
        RoomType instance = new RoomType(null);
        String expected = "UNSPECIFIED";
        String result = instance.getRoomType();
        assertEquals(expected, result);
    }
    @Test
    public void testRoomTypeFromString_emptyInput() {
        System.out.println("testRoomTypeFromString_emptyInput");
        RoomType instance = new RoomType("");
        String expected = "UNSPECIFIED";
        String result = instance.getRoomType();
        assertEquals(expected, result);
    }
    @Test
    public void illegalArgumentToBeThrown(){
        System.out.println("illegalArgumentToBeThrown");
        RoomType instance = new RoomType("");
        String invalidRoomType = "InvalidRoomType";
        assertThrows(IllegalArgumentException.class,() -> instance.setRoomType(invalidRoomType));
    }
    @Test(expected = IllegalArgumentException.class)
    public void testRoomTypeFromString_invalidInput() {
        System.out.println("testRoomTypeFromString_invalidInput");
        String invalidRoomType = "Invalid Room Type";
        RoomType instance = new RoomType("");
        instance.setRoomType(invalidRoomType);

    }
}
