package spike;

import db.DbConnectionManager;
import domainModell.person.Person;
import domainModell.room.Room;
import service.RoomService;
import service.ServiceRunner;
import service.person.FindAllPersonService;
import service.room.FindAllRoomService;

import java.util.List;

public class MainRoom {
    public static void main(String[] args) {

        RoomService roomService = new RoomService();
        ServiceRunner<List<Room>> runner = new ServiceRunner(new FindAllRoomService());
        List<Room> roomList = runner.execute();
        for(Room p: roomList){
            System.out.println(p);
        }
    /*    Room room = new Room(24,4,44,"     ");
        DbConnectionManager.getInstance().open();
        System.out.println(roomService.saveRoom(room));*/
     //   roomService.updateRoom(new Room(30,33,33,"TOILET"));
     //   roomService.deleteRoom(new Room(33,1,1," "));
      //  System.out.println(roomService.getAllRoom());

      //  RoomDao myRoom = new RoomDao();
     //   myRoom.save(new Room(0,21,22, "BEDROOM"));
    //    myRoom.save(new Room(0,2,75, "LIVINGROOM"));
   //     myRoom.save(new Room(0,50,140, "WAREHOUSE"));
    //    Room updateRoom = new Room(29,3,7, "TOILET");
   //     myRoom.update(updateRoom);
     //   Room deleteRoom = new Room(29,0,0, "WAREHOUSE");
     //   myRoom.delete(deleteRoom);
      //  System.out.println(myRoom.getAll());
    }
}
