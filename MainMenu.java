import api.AdminResource;
import api.HotelResource;
import model.Customer;
import model.IRoom;
import model.Reservation;

import java.text.SimpleDateFormat;
import java.util.*;

public class MainMenu {

    static AdminMenu menu = new AdminMenu();

    public static void main(String[] args) {
        ValidInput vi = new ValidInput();
        boolean valid = false;
        boolean findAndReserveFlag=false;

        do {
            System.out.println("1. Find and Reserve a Room");
            System.out.println("2. See my Reservations");
            System.out.println("3. Create an Account");
            System.out.println("4. Admin");
            System.out.println("5. Exit");
            Scanner sc = new Scanner(System.in);
                int opt = sc.nextInt();
                switch (opt) {
                    case 1:
                        //find and reserve a room
                        do{
                        System.out.println("--------Find and Reserve a Room---------");
                        final Collection<IRoom> rooms = AdminResource.getAllRooms();
                        Collection<IRoom> availablerooms = new ArrayList<>();
                        Collection<Customer> customers = new ArrayList<>();
                        String checkinmsg = "Enter a check in date format:dd/MM/yyyy";
                        Date vcheckin = new Date();
                        Date checkin = (Date) vi.validateInput(checkinmsg, vcheckin, sc);
                        String checkoutmsg = "Enter a check out date format:dd/MM/yyyy";
                        Date vcheckout = new Date();
                        Date checkout = (Date) vi.validateInput(checkoutmsg, vcheckout, sc);
                        if (checkin.compareTo(checkout) > 0) {
                            System.out.println("Check in date can not be later than Check out date");
                        } else if (checkin.compareTo(checkout) == 0) {
                            System.out.println("Check in date and Check out date cannot be equal");
                        } else {
                            String emailmsg = "Enter your email";
                            String vemail = "";
                            String email = vi.validateEmail(emailmsg, vemail, sc);
                            availablerooms = HotelResource.findARoom(checkin, checkout);
                            System.out.println("Rooms "+rooms);
                            System.out.println(" Available Rooms "+availablerooms);
                            String roomnomsg = "Enter Room Number";
                            String vroomno = "";
                            String roomno = (String) vi.validateInput(roomnomsg, vroomno, sc);
                            Customer c = HotelResource.getCustomer(email);
                            if (c != null) {
                                if (rooms != null) {
                                    System.out.println();
                                    Reservation r = null;
                                    for (IRoom room : rooms) {
                                        if (roomno.equals(room.getRoomNumber())) {
                                            try {
                                                r = HotelResource.bookARoom(email, room, checkin, checkout);
                                                if (r != null) {
                                                    System.out.println("Reservation successful");
                                                    System.out.println(r);
                                                    findAndReserveFlag=true;
                                                }
                                            } catch (Exception e) {
                                                System.out.println("Reservation was not successful");
                                            }
                                        }
                                    }
                                } else {
                                    System.out.println("No rooms available");
                                    Date oneweeklatercheckin = new Date();
                                    SimpleDateFormat formattedDate = new SimpleDateFormat("dd/MM/yyyy");
                                    Calendar calendar = Calendar.getInstance();
                                    calendar.add(Calendar.DATE, 7);
                                    oneweeklatercheckin = calendar.getTime();

                                    Date oneweeklatercheckout = new Date();
                                    Calendar calendar2 = Calendar.getInstance();
                                    calendar2.add(Calendar.DATE, 7);
                                    oneweeklatercheckout = calendar.getTime();
                                    String input = "";
                                    System.out.println("Are you interested to book the date after 1 week?");

                                    System.out.println("If you want to enter more rooms enter Y/y or N/n");
                                    input = sc.next();
                                    if (input.equals("y") || input.equals("Y")) {
                                        valid = false;
                                    } else if (input.equals("n") || input.equals("N")) {
                                        valid = true;
                                    } else {
                                        System.out.println("Please enter again");
                                        valid = false;
                                        input = sc.next();
                                    }
                                }
                            } else {
                                System.out.println("No Customer is registered with this email address");
                            }
                        }
                        }while(!findAndReserveFlag);
                        break;
                    case 2:
                        //see my reservations
                        System.out.println("---------Check the Reservations---------");
                        String customeremailmsg = "Enter customer email";
                        String vcustomeremail = "";
                        String customeremail = vi.validateEmail(customeremailmsg, vcustomeremail, sc);
                            Customer c = HotelResource.getCustomer(customeremail);
                            if (c != null) {
                                System.out.println(customeremail);
                                Collection<Reservation> reserve = HotelResource.getCustomerReservations(customeremail);
                                if (reserve != null)
                                    reserve.forEach(System.out::println);
                                else
                                    System.out.println("No Reservation available");
                            } else {
                                System.out.println("No customer is available");
                            }
                        break;
                    case 3:
                        //create an account
                        System.out.println("-----------Create an Account-----------");
                        String fnamemsg = "Enter your firstname";
                        String vfname = "";
                        String fname = (String) vi.validateInput(fnamemsg, vfname, sc);
                        String lnamemsg = "Enter your lastname";
                        String vlname = "";
                        String lname = (String) vi.validateInput(lnamemsg, vlname, sc);
                        String emailmsg = "Enter your email address format:abc@domain.com";
                        String vemail = "";
                        String email = vi.validateEmail(emailmsg, vemail, sc);
                                try {
                                    HotelResource.createACustomer(email, fname, lname);
                                    System.out.println(fname + " | " + " " + lname + " | " + " " + email);
                                } catch (Exception e) {
                                    System.out.println("Customer Account not created successfully");
                                }
                                break;
                    case 4:
                        //Admin
                        menu.display();
                        break;
                    case 5:
                        System.exit(0);
                    default:
                        System.out.println("Invalid Input. Enter again");
                        valid=false;
                }
        } while (!valid);
    }
}
