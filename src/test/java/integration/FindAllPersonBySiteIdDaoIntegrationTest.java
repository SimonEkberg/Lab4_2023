package integration;

import db.DbConnectionManager;
import domainModell.person.Person;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import service.person.FindAllPersonBySiteIdDao;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FindAllPersonBySiteIdDaoIntegrationTest {

    private static DbConnectionManager dbConnectionManager;

    @BeforeAll
    static void setUp() {
        dbConnectionManager = DbConnectionManager.getInstance();
        dbConnectionManager.open();
    }

    @AfterAll
    static void tearDown() {
        dbConnectionManager.close();
    }

    @Test
    void testGetPersonsBySiteId() throws SQLException {
        int siteId = 1;
        FindAllPersonBySiteIdDao service = new FindAllPersonBySiteIdDao(siteId);
        List<Person> persons = service.execute();

        assertNotNull(persons);
        assertTrue(persons.size() > 0);
        for (Person person : persons) {
            assertEquals(siteId, person.getSiteId());
        }
    }
}
