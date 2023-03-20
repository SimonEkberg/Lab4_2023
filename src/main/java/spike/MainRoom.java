package spike;

import db.DbConnectionManager;
import domainModell.person.Person;
import domainModell.room.Room;
import service.CleaningManagerServiceException;
import service.RoomService;
import service.ServiceRunner;
import service.person.DeletePersonByIdService;
import service.person.FindAllPersonService;
import service.person.SavePersonService;
import service.room.*;

import java.util.List;
import java.util.Optional;

public class MainRoom {
    public static void main(String[] args) throws CleaningManagerServiceException {

    /*    ServiceRunner<Room> serviceRunner = new ServiceRunner(new DeleteRoomByIdService(32));
        Room room = serviceRunner.execute();
        System.out.println(room);*/

   /*     ServiceRunner<Room> serviceRunner = new ServiceRunner(new FindAllRoomService());
        List<Room> list = (List<Room>) serviceRunner.execute();
        for (Room room: list){
            System.out.println(room);
        }*/
    /*    ServiceRunner<Room> serviceRunner = new ServiceRunner<>(new GetRoomByIdService(41));
        Room room = serviceRunner.execute();
        System.out.println(room);*/
      /*  Room room = new Room(41, 50, 50, "");
        ServiceRunner<Room> serviceRunner = new ServiceRunner(new SaveRoomService(room));
        serviceRunner.execute();*/
        int id = 80;
        Room room = new Room(id, 8, 50, "");
        ServiceRunner<Optional> serviceRunner = new ServiceRunner<>(new SaveRoomService(room));
        serviceRunner.execute();
    }
}
