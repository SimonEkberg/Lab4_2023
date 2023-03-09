package service;

import domainModell.person.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import repository.PersonDao;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

class PersonServiceTest {
    private PersonService instance;
    @Mock
    private PersonDao personDaoMock;
    @BeforeEach
    void setUp(){
        openMocks(this);
        instance = new PersonService(personDaoMock);
    }
    @Test
    void getPerson() throws SQLException {
        System.out.println("getPerson");
        int id = 1;
        Person person = new Person(id, "Simon Ekberg", 1989);
        when(personDaoMock.get(id)).thenReturn(Optional.of(person));
        Optional<Person> result = instance.getPerson(id);
        assertTrue(person.equals(result));
        assertEquals(person.toString(), result.toString());
        verify(personDaoMock, times(1)).get(id);
    }
   @Test
   void findAll() {
       System.out.println("FindAll");
       List<Person> expResult = List.of(new Person(1, "Simon", 1989),
               new Person(2, "Ekberg", 2000));
       when(personDaoMock.getAll()).thenReturn(List.of(new Person(1, "Simon", 1989),
               new Person(2, "Ekberg", 2000)));
       List<Person> result = instance.findAllPerson();
       assertArrayEquals(expResult.toArray(), result.toArray());
       assertEquals(expResult, result);
       assertTrue(expResult.equals(result));
       assertTrue(result instanceof List<Person>);
       verify(personDaoMock, times(1)).getAll();
   }
    @Test
    void savePerson() {
        System.out.println("savePerson");
        Person expResult = new Person(1,"Simon Ekberg", 1989);
        when(personDaoMock.save(expResult)).thenReturn(Optional.of(expResult));
        Optional<Person> result = instance.savePerson(expResult);
        assertTrue(expResult.equals(result));
        assertEquals(expResult, result);
        verify(personDaoMock, times(1)).save(expResult);
    }

    @Test
    void updatePerson() {
        System.out.println("updatePerson");
        Person expResult = new Person(1, "Simon Ekberg", 1989);
        when(personDaoMock.update(expResult)).thenReturn(Optional.of(expResult));
        Optional<Person> result = instance.updatePerson(expResult);
        assertTrue(expResult.equals(result));
        assertEquals(expResult, result);
        verify(personDaoMock, times(1)).update(expResult);
    }

   /* @Test
    void deletePerson() {
        System.out.println("deletePerson");
        int id = 1;
        Person person = new Person(id, "Simon", 1989);
        when(personDaoMock.delete(id)).thenReturn(true);
        boolean expResult = true;
        Person result = instance.deletePerson(id);
        assertEquals(expResult, result);
        verify(personDaoMock, times(1)).delete(id);
    }*/
   @Test
   void deletePerson() throws SQLException {
       System.out.println("deletePerson");
       int id = 1;
       Person person = new Person(id, "Simon", 1989);
       when(personDaoMock.delete(id)).thenReturn(Optional.of(person));
       Person expResult = person;
       Optional<Person> result = instance.deletePerson(id);
       assertEquals(expResult, result);
       verify(personDaoMock, times(1)).delete(id);
   }


}