package api;

import model.Customer;
import model.IRoom;
import model.Reservation;
import service.CustomerService;
import service.ReservationService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AdminResource {
    private static AdminResource ar=null;
    static Collection<Customer> customers=new ArrayList<>();
    static Collection<IRoom> rooms=new ArrayList<>();
    static Collection<Reservation> reservations=new ArrayList<>();
    private AdminResource()
    {}

    public static AdminResource getInstance()
    {
        if(ar==null)
        {
            ar=new AdminResource();
        }
        return ar;
    }
    public static Customer getCustomer(String email)
    {
        Customer customer=CustomerService.getCustomer(email);
        return customer;
    }
    public static void addRoom(List<IRoom> rooms)
    {
        Collection<IRoom> roomCollection=ReservationService.getRooms();
        for(IRoom room:rooms)
        {
                ReservationService.addRoom(room);
        }
    }
    public static Collection<IRoom> getAllRooms()
    {
        return ReservationService.getRooms();
    }
    public static Collection<Customer> getAllCustomers()
    {
        customers= CustomerService.getAllCustomers();
        return customers;
    }
    public static void displayAllReservations()
    {
        ReservationService.printAllReservation();
    }
}
