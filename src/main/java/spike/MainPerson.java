package spike;

import db.DbConnectionManager;
import domainModell.person.Person;
import domainModell.room.Room;
import domainModell.site.Site;
import repository.Dao;
import repository.DaoFactory;
import service.PersonService;
import service.ServiceCommand;
import service.ServiceRunner;
import service.person.*;

import java.util.List;
import java.util.Optional;

public class MainPerson {
    public static void main(String[] args) {
     //   PersonService personService = new PersonService();
    //    DbConnectionManager.getInstance().open();
     /*   int id = 22;
        ServiceRunner<Person> runner2 = new ServiceRunner(new GetPersonByIdService(id));
        Person person = runner2.execute();
        System.out.println(person);*/
     /*   Site site = new Site(1, "Donken", "GÄVLE");
        Person person = new Person(40, "Simon Ekberg", 2019,1);
        ServiceRunner<Person> serviceRunner1 = new ServiceRunner(new SavePersonService(person));
        serviceRunner1.execute();*/

        ServiceRunner<List<Person>> serviceRunner = new ServiceRunner(new FindAllPersonBySiteId(8));
        List<Person> personList = serviceRunner.execute();
        for(Person p: personList){
            System.out.println(p);
        }

    /*    ServiceRunner<Person> serviceRunner = new ServiceRunner(new DeletePersonByIdService(26));
        Person person = serviceRunner.execute();*/


      /*  int id = 1;
      //  Person person = (new Person(id, "Optional", 2020));
        ServiceRunner<Optional<Person>> serviceRunner = new ServiceRunner<>(new GetPersonByIdService((id)));
        Optional<Optional<Person>> updatedPerson = Optional.ofNullable(serviceRunner.execute());
        System.out.println(updatedPerson);*/



    }
}