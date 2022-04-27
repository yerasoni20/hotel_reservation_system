package model;

public class Room implements IRoom {

    String roomNumber;
    Double price;
    final RoomType enumeration;

    public Room(String s, Double p, RoomType e ){
        this.roomNumber=s;
        this.price=p;
        this.enumeration=e;
    }

    @Override
    public String getRoomNumber() {
        return roomNumber;
    }

    @Override
    public Double getRoomPrice() {
        return price;
    }

    @Override
    public RoomType getRoomType() {
        return enumeration;
    }

    @Override
    public boolean isFree() {
        return false;
    }

    @Override
    public String toString() {
        return roomNumber+" | "+" "+price+" | "+" "+enumeration;
    }
}
