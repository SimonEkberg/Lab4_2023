package service.person;

import domainModell.person.Person;
import service.ServiceRunner;

public class Main {
    public static void main(String[] args) {
     //   PersonService personService = new PersonService();
        //      DbConnectionManager.getInstance().open();
       /*     FindAllPersonService findAllPersonService = new FindAllPersonService();
        List<Person> personList = findAllPersonService.execute();
        for(Person person : personList){
            System.out.println("Id: "+person.getId()+
                    " Name: "+person.getPersonName()+
                    " Birt year: "+person.getBirthYear());
        }*/
       /* int id = 41;
        DeleteByIdService deleteByIdService = new DeleteByIdService(id);
        boolean person = deleteByIdService.execute();
        System.out.println(person);
*/
   /*     UpdateService updateService = new UpdateService(new Person(40, "TestarUpdate", 2023));
        updateService.execute();
*/
  /*      Person person = new Person("Controll Service", 1923);
        SavePersonService savePersonService = new SavePersonService(person);
        ServiceRunner controllService = new ServiceRunner(commandService);
        controllService.setCommand(savePersonService);
        controllService.executeService();*/

    }

}
