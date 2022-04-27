import api.AdminResource;
import api.HotelResource;
import model.*;
import service.CustomerService;
import service.ReservationService;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class AdminMenu {
    Collection<Customer> customers=new ArrayList<>();
    Collection<IRoom> rooms=new ArrayList<>();
    boolean loop=false;
    AdminMenu(){

    }
    void display() {
        System.out.println("1. See all Customers");
        System.out.println("2. See all Rooms");
        System.out.println("3. See all Reservations");
        System.out.println("4. Add a room");
        System.out.println("5. Back to main menu");

        Scanner sc = new Scanner(System.in);
        boolean valid=false;
        do {
            int opt = sc.nextInt();

            switch (opt) {
                case 1:
                    //see all customers
                    System.out.println("-------All the customers----------");
                    customers= AdminResource.getAllCustomers();
                    customers.forEach(System.out::println);
                    display();
                    break;
                case 2:
                    //see all rooms
                    System.out.println("-------All the rooms----------");
                    rooms=AdminResource.getAllRooms();
                    rooms.forEach(System.out::println);
                    display();
                    break;
                case 3:
                    //see all reservations
                    System.out.println("-------All the reservations----------");
                    AdminResource.displayAllReservations();
                    display();
                    break;
                case 4:
                    //add a room
                    List<IRoom> r=new CopyOnWriteArrayList<>();
                    Collection<IRoom> roomCollection=AdminResource.getAllRooms();
                    String input="";
                    ValidInput vi=new ValidInput();
                        do {
                            System.out.println("-----------Create a Room-----------");
                            String roomno = "Enter Room number";
                            String vroomno = "";
                            String room_no = (String) vi.validateInput(roomno, vroomno, sc);
                            String rpmsg = "Enter Room Price";
                            Double vprice = 0.0;
                            Double price = (Double) vi.validateInput(rpmsg, vprice, sc);
                            String roomtyepmsg = "Enter Room Type";
                            //String roomtype="";
                            RoomType roomType = null;
                            RoomType type = (RoomType) vi.validateInput(roomtyepmsg, roomType, sc);
                            Room room = new Room(room_no, price, type);
                        MainFor:    if(!roomCollection.isEmpty()) {
                                IRoom room1= HotelResource.getRoom(room_no);
                                if(room1!=null) {
                                            System.out.println("Room already exists");
                                            break MainFor;
                                }else
                                    {
                                        r.add(room);
                                    }
                                }
                            else
                                {
                                    Iterator<IRoom> it = r.iterator();
                                    if (it.hasNext()) {
                                        if (it.next().getRoomNumber().equals(room_no)) {
                                            System.out.println("Room alreadyy exists");
                                        } else {
                                            r.add(room);
                                        }
                                    } else {
                                        r.add(room);
                                    }
                                }
                            System.out.println("If you want to enter more rooms enter Y/y or N/n");
                            input=sc.next();
                            if(input.equals("y") || input.equals("Y")) {
                                    valid = false;
                            }
                            else if(input.equals("n") || input.equals("N"))
                            {
                                    valid=true;
                            }
                            else
                            {
                                    System.out.println("Please enter again");
                                    valid=false;
                                    input=sc.next();
                            }
                        } while (!(valid));
                    r.forEach(System.out::println);
                    AdminResource.addRoom(r);
                    display();
                    break;
                case 5:
                    //back to main menu
                    loop=true;
                    return;
                default:
                    break;
            }
        }while(!loop);
    }
}
