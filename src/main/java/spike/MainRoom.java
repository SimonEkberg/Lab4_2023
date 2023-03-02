package spike;

import db.DbConnectionManager;
import domainModell.person.Person;
import domainModell.room.Room;
import service.CleaningManagerServiceException;
import service.RoomService;
import service.ServiceRunner;
import service.person.DeletePersonByIdService;
import service.person.FindAllPersonService;
import service.room.*;

import java.util.List;

public class MainRoom {
    public static void main(String[] args) throws CleaningManagerServiceException {

    /*    ServiceRunner<Room> serviceRunner = new ServiceRunner(new DeleteRoomByIdService(32));
        Room room = serviceRunner.execute();
        System.out.println(room);*/

     /*   ServiceRunner<Room> serviceRunner = new ServiceRunner(new FindAllRoomService());
        List<Room> list = (List<Room>) serviceRunner.execute();
        for (Room room: list){
            System.out.println(room);
        }*/
    /*    ServiceRunner<Room> serviceRunner = new ServiceRunner<>(new GetRoomByIdService(41));
        Room room = serviceRunner.execute();
        System.out.println(room);*/
    /*    Room room = new Room(41, 1,1,"BEDROOM");
        ServiceRunner<Room> serviceRunner = new ServiceRunner<>(new UpdateRoomService(room));
        Room updatedRoom = serviceRunner.execute();
        System.out.println(updatedRoom);*/
        int id = 1;
        ServiceRunner<Room> serviceRunner = new ServiceRunner<>(new GetRoomByIdService(id));
        Room room = serviceRunner.execute();
        System.out.println(room);
    }
}
