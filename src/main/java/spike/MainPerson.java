package spike;

import db.DbConnectionManager;
import domainModell.person.Person;
import repository.Dao;
import repository.DaoFactory;
import service.PersonService;
import service.ServiceCommand;
import service.ServiceRunner;
import service.person.DeletePersonByIdService;
import service.person.FindAllPersonService;
import service.person.GetPersonByIdService;
import service.person.SavePersonService;

import java.util.List;

public class MainPerson {
    public static void main(String[] args) {
        PersonService personService = new PersonService();
    //    DbConnectionManager.getInstance().open();
     /*   int id = 22;
        ServiceRunner<Person> runner2 = new ServiceRunner(new GetPersonByIdService(id));
        Person person = runner2.execute();
        System.out.println(person);*/
        /*
        ServiceRunner<List<Person>> serviceRunner = new ServiceRunner(new FindAllPersonService());
        List<Person> personList = serviceRunner.execute();
        for(Person p: personList){
            System.out.println(p);
        }
        */
        ServiceRunner<Person> serviceRunner = new ServiceRunner(new DeletePersonByIdService(26));
        Person person = serviceRunner.execute();
        System.out.println(person);


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