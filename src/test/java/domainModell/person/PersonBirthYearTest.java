package domainModell.person;

import domainModell.person.PersonBirthYear;
import org.junit.jupiter.api.*;

import java.time.Year;

import static org.junit.jupiter.api.Assertions.*;

class PersonBirthYearTest {
    PersonBirthYear instance;

    @BeforeEach
    public void setUp() {
        instance = new PersonBirthYear(2000);
    }
    @Test
    void getBirthYear() {
        System.out.println("getName");
        int expectedResult = 2000;
        int result = instance.getBirthYear();
        assertEquals(expectedResult, result);
    }

    @Test
    void setBirthYear() {
        System.out.println("setBirthYear");
        int expectedResult = 1999;
        instance.setBirthYear(expectedResult);
        int result = instance.getBirthYear();
        assertEquals(expectedResult, result);
    }
    @Test
    void exceptionSettingSmallBirthYear(){
        System.out.println("exceptionSettingSmallBirthYear");
        int currentYear = Year.now().getValue();
        int minimumBirthYear = currentYear - 120;
        String expectedResult = "Birth year out of range: " +(currentYear-120) + " < Birth year < "+ (currentYear+1);
        assertThrows(IllegalArgumentException.class,() -> instance.setBirthYear(minimumBirthYear-1), expectedResult);
    }
    @Test
    void exceptionSettingLargeBirthYear(){
        System.out.println("exceptionSettingSmallBirthYear");
        int currentYear = Year.now().getValue();
        String expectedResult = "Birth year out of range: " +(currentYear-120) + " < Birth year < "+ (currentYear+1);
        assertThrows(IllegalArgumentException.class,() -> instance.setBirthYear(currentYear+1),expectedResult);
    }
    @Test
    void settingMinimumBirthYear() {
        System.out.println("settingMinimumBirthYear");
        int currentYear = Year.now().getValue();
        int minimumBirthYear = currentYear - 120;
        instance.setBirthYear(minimumBirthYear);
        int expectedResult = minimumBirthYear;
        int result = instance.getBirthYear();
        assertEquals(expectedResult,result);
    }
    @Test
    void settingMaximumBirthYear() {
        System.out.println("settingMaximumBirthYear");
        int currentYear = Year.now().getValue();
        int maximumBirthYear = currentYear;
        instance.setBirthYear(maximumBirthYear);
        int expectedResult = maximumBirthYear;
        int result = instance.getBirthYear();
        assertEquals(expectedResult,result);
    }
}