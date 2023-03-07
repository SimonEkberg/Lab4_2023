package domainModell.person;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PersonTest {
    Person instance;
    @BeforeEach
    public void setUp() {
        instance = new Person(1,"Simon Ekberg", 1989);
    }
    @Test
    void getPersonName() {
        System.out.println("getPersonName");
        String expectedResult = "Simon Ekberg";
        String result = instance.getPersonName();
        assertEquals(expectedResult, result);
    }

    @Test
    void setPersonName() {
        System.out.println("setPersonName");
        String expectedResult = "Ekberg Simon";
        String setName = "Ekberg Simon";
        instance.setPersonName(setName);
        String result = instance.getPersonName();
        assertEquals(expectedResult, result);
    }

    @Test
    void getId() {
        System.out.println("getId");
        int expectedResult = 1;
        int result = instance.getId();
        assertEquals(expectedResult, result);
    }

    @org.junit.jupiter.api.Test
    void getBirthYear() {
        System.out.println("getBirthYear");
        int expectedResult = 1989;
        int result = instance.getBirthYear();
        assertEquals(expectedResult, result);    }

    @Test
    void setBirthYear() {
        System.out.println("setBirthyear");
        int setBirthYerar = 2000;
        int expectedResult = 2000;
        instance.setBirthYear(setBirthYerar);
        int result = instance.getBirthYear();
        assertEquals(expectedResult, result);
    }

    @Test
    void equals(){
        Person person = new Person(1, "Simon", 2000,0);
        Person person2 = new Person(1, "Simon", 2000,0);
        assertTrue(person.equals(person2));
        assertEquals(person2, person);
    }

    @Test
    void testToString() {
        System.out.println("testToString");
        String expectedResult = "Person{Id: 1, Namn: Simon Ekberg, Födelseår: 1989, SiteId: 0}";
        String result = instance.toString();
        assertEquals(expectedResult, result);
    }
}