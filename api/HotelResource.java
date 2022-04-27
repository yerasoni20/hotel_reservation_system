package api;

import model.Customer;
import model.IRoom;
import model.Reservation;
import service.CustomerService;
import service.ReservationService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public class HotelResource {
    private static HotelResource hr=new HotelResource();

    private HotelResource()
    {}

    static Collection<Reservation> reservations=new ArrayList<>();
    static Collection<Customer> customers=new ArrayList<>();
    static Collection<IRoom> rooms=new ArrayList<>();
    public static HotelResource getInstance()
    {
        return hr;
    }

    public static Customer getCustomer(String email)
    {
        customers= CustomerService.getAllCustomers();
        for(Customer customer:customers)
        {
            if(customer.getEmail().equals(email))
            {
                return customer;
            }
        }
        return null;
    }
    public static void createACustomer(String email,String firstname,String lastname)
    {
        CustomerService.addCustomer(firstname,lastname,email);
    }
    public static IRoom getRoom(String roomNumber)
    {
        rooms=AdminResource.getAllRooms();

        for(IRoom room:rooms)
        {
            if(room.getRoomNumber().equals(roomNumber))
            {
                return room;
            }
        }
        return null;
    }
    public static Reservation bookARoom(String customerEmail, IRoom room, Date checkInDate, Date checkOutDate)
    {
        Reservation r=null;
        customers= CustomerService.getAllCustomers();
        if(reservations.isEmpty()) {
            for (Customer customer : customers) {
                System.out.println(customer);
                if (customer.getEmail().equals(customerEmail)) {
                    r = ReservationService.reserveARoom(customer, room, checkInDate, checkOutDate);
                    return r;
                }
            }
        }
        return null;
    }
    public static Collection<Reservation> getCustomerReservations(String customerEmail)
    {
        Customer customer=HotelResource.getCustomer(customerEmail);
        reservations=ReservationService.getCustomersReservation(customer);
        return reservations;
    }
    public static Collection<IRoom> findARoom(Date checkIn, Date checkOut)
    {
        Collection<IRoom> availablerooms=ReservationService.findRooms(checkIn,checkOut);
        if(availablerooms==null)
        {
            return rooms;
        }
        //rooms=ReservationService.findRooms(checkIn,checkOut);
        return availablerooms;
    }
}
