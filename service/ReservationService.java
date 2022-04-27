package service;

import api.AdminResource;
import model.Customer;
import model.IRoom;
import model.Reservation;
import model.Room;

import java.util.*;
import java.util.stream.Collectors;

public class ReservationService {

    private static ReservationService rs=null;

    private ReservationService()
    {}

    public static ReservationService getInstance()
    {
        if(rs==null)
        {
            rs=new ReservationService();
        }
        return rs;
    }
    static Collection<IRoom> rooms=new ArrayList<>();
    static Collection<Reservation> reservations=new ArrayList<>();
    public static void addRoom(IRoom room)
    {
        rooms.add(new Room(room.getRoomNumber(),room.getRoomPrice(),room.getRoomType()));
    }
    public static IRoom getARoom(String roomId)
    {
        for(IRoom room:rooms)
        {
            if(room.getRoomNumber().equals(roomId))
            {
                return room;
            }
        }
        return null;
    }
    public static Reservation reserveARoom(Customer customer, IRoom room, Date checkInDate, Date checkOutDate)
    {
        Reservation r=new Reservation(customer,room,checkInDate,checkOutDate);
        reservations.add(r);
        return r;
    }
    public static Collection<IRoom> findRooms(Date checkInDate, Date checkOutDate)
    {
        Collection<IRoom> roomsc=new ArrayList<>();
        Collection<IRoom> roomscopy=new ArrayList<>(rooms);

        for(IRoom room:rooms)
        {
            if(!reservations.isEmpty()) {
                for (Reservation r : reservations) {
                    IRoom room1=ReservationService.getARoom(room.getRoomNumber());
                    if (room1.getRoomNumber().equals(r.getRoom().getRoomNumber()) &&
                        ((r.getCheckInDate().equals(checkInDate))
                            || (r.getCheckOutDate().equals(checkOutDate)))) {
                            System.out.println("Reservation already exists");
                            roomscopy = new ArrayList<>(rooms);
                            roomscopy.remove(room);
                            return roomscopy;
                        }
                    }
                }
            else
            {
                return rooms;
            }
        }
        return null;
    }
    public static Collection<Reservation> getCustomersReservation(Customer customer)
    {
        Collection<Reservation> r=reservations.stream().filter(reservation -> reservation.getCustomer().equals(customer)).collect(Collectors.toCollection(ArrayList::new));
        return r;
    }
    public static void printAllReservation()
    {
        reservations.forEach(System.out::println);
    }
    public static Collection<IRoom> getRooms() { return rooms;}
}
