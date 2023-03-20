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
import service.room.GetRoomByIdService;
import service.room.SaveRoomService;

import java.util.List;
import java.util.Optional;

public class MainPerson {
    public static void main(String[] args) {
     //   PersonService personService = new PersonService();
    //    DbConnectionManager.getInstance().open();
     /*   int id = 1;
        ServiceRunner<Person> runner2 = new ServiceRunner(new GetPersonByIdService(id));
        runner2.execute();*/

     //   Site site = new Site(1, "Donken", "GÄVLE");
  /*      Person person = new Person(40, "Simon Ekberg", 2019,1);
        ServiceRunner<Person> serviceRunner1 = new ServiceRunner(new SavePersonService(person));
        serviceRunner1.execute();*/

     /*   ServiceRunner<List<Person>> serviceRunner = new ServiceRunner(new FindAllPersonService());
        List<Person> personList = serviceRunner.execute();
        for(Person p: personList){
            System.out.println(p);
        }*/

   //     ServiceRunner<Optional<Person>> serviceRunner = new ServiceRunner(new FindAllPersonBySiteId(2));
    //    serviceRunner.execute();
    //    Optional<Optional<Person>> updatedPerson = Optional.ofNullable(serviceRunner.execute());

        int id = 50;
        Person person = (new Person(id, "Bengt", 1999, 3));
        ServiceRunner<Optional> serviceRunner = new ServiceRunner(new SavePersonService(person));
        serviceRunner.execute();
      //  Optional<Optional<Person>> updatedPerson = Optional.ofNullable(serviceRunner.execute());
     //   System.out.println(updatedPerson);



    }
}