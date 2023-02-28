package spike;

import db.DbConnectionManager;
import domainModell.person.Person;
import repository.Dao;
import service.PersonService;

import java.util.List;

public class MainPerson {
    public static void main(String[] args) {
        PersonService personService = new PersonService();
        DbConnectionManager.getInstance().open();
        Person person = new Person(62,"Testar utskrift vid update", 1950);

        System.out.println(personService.updatePerson(person));
      //  personService.updatePerson(new Person(24, "Gustav", 1989));
      //      personService.deletePerson(29);
     //   System.out.println(personService.getPerson(24));
     //   PersonDao personDao = new PersonDao();
      //  personDao.save(new Person(0,"Hej Hopp", 1920));

    /*    List<Person> personList = personService.findAllPerson();
        System.out.println(personList);
        for(Person person : personList){
            System.out.println("Id: "+person.getId()+
                    " Name: "+person.getPersonName()+
                    " Birt year: "+person.getBirthYear());
        }*/
    //    Person updatePerson = new Person(1, "Sillen", 1990);
    //    personDao.update(updatePerson);
      //  Person deletePerson = new Person(6,"jk",2000);
      //  personDao.delete(deletePerson);





    }
}