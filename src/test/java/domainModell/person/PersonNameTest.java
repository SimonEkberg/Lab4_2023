package domainModell.person;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PersonNameTest {
    PersonName instance;
    @BeforeEach
    public void setUp() {
        instance = new PersonName("Simon");
    }

    @Test
    void getName() {
        System.out.println("getName");
        String expectedResult = "Simon";
        String result = instance.getName();
        assertEquals(expectedResult, result);
        assertEquals(instance.getName(), result);
    }

    @Test
    void setName() {
        System.out.println("setName");
        instance.setName("Siljamäki");
        String expectedResult = "Siljamäki";
        String result = instance.getName();
        assertEquals(expectedResult, result);
        assertEquals(expectedResult,result);
    }

    @Test
    void isNameAlphaSpace() {
        System.out.println("isNameAlphaSpace");
        String notStringName = "S!mon";
        String expectedResult ="Name contain illegal characters!";
        assertThrows(IllegalArgumentException.class,() -> instance.setName(notStringName),expectedResult);
    }
    @Test
    void isNameToLongOrNullOrEmpty() {
        System.out.println("isNameToLongOrNullOrEmpty");
        String emptpyNameString = "";
        String nullNameString = null;
        String moreThan50CharName = "aaaaaaaaaabbbbbbbbbbccccccccccddddddddddeeeeeeeeeeX";
        String spaceTabName = "             ";
        String result = "Name is too long or null!";
        assertThrows(IllegalArgumentException.class,() -> instance.setName(emptpyNameString),result);
        assertThrows(IllegalArgumentException.class,() -> instance.setName(nullNameString),result);
        assertThrows(IllegalArgumentException.class,() -> instance.setName(moreThan50CharName),result);
        assertThrows(IllegalArgumentException.class,() -> instance.setName(spaceTabName),result);

    }
}