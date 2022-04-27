package model;

import java.util.Date;

public class Reservation {
    Customer customer;
    IRoom room;
    Date checkInDate;
    Date checkOutDate;

    public Reservation(Customer c,IRoom r,Date in,Date out)
    {
        this.customer=c;
        this.room=r;
        this.checkInDate=in;
        this.checkOutDate=out;
    }
    public Customer getCustomer()
    {
        return customer;
    }
    public IRoom getRoom()
    {
        return room;
    }
    public Date getCheckInDate() {
        return checkInDate;
    }

    public Date getCheckOutDate() {
        return checkOutDate;
    }

    @Override
    public String toString(){
        return  "Customer "+customer+"\n "+
                "Room Details "+room+"\n "+
                "Check In Date "+checkInDate+"\n "+
                "Check out Date "+" "+checkOutDate+"\n";
    }

    @Override
    public boolean equals(Object o){
        if(o==this)
            return true;
        if(!(o instanceof Customer) && !(o instanceof IRoom))
            return false;
        Reservation reserve=(Reservation) o;
        boolean customerequal=(this.customer==null && reserve.customer==null) ||
                (this.customer!=null && this.customer.equals(reserve.customer));
        boolean roomequal=(this.room==null && reserve.room==null) ||
                (this.room!=null && this.room.equals(reserve.room));
        boolean checkindateequal=(this.checkInDate==null && reserve.checkInDate==null) ||
                (this.checkInDate!=null && this.checkInDate.equals(reserve.checkInDate));
        boolean checkoutdateequal=(this.checkOutDate==null && reserve.checkOutDate==null) ||
                (this.checkOutDate!=null && this.checkOutDate.equals(reserve.checkOutDate));
        return customerequal && roomequal && checkindateequal && checkoutdateequal;
    }

    @Override
    public final int hashCode(){
        int result = 17;
        if (checkInDate != null) {
            result = 31 * result + checkInDate.hashCode();
        }
        if (checkOutDate != null) {
            result = 31 * result + checkOutDate.hashCode();
        }
        return result;
    }
}
