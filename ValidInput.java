import model.Room;
import model.RoomType;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class ValidInput {

    Object input = null;
    SimpleDateFormat sdf=null;
    //Scanner sc=new Scanner(System.in);
    public Object validateInput(String msg, Object e, Scanner sc){
        boolean valid=false;

        if (e instanceof String) {
            do {
                System.out.println(msg);
                if (sc.hasNext()) {
                    e = sc.next();
                    valid = true;
                } else {
                    System.out.println("Please enter again");
                    valid = false;
                    e = sc.next();
                }
            } while (!valid);
        } else if (e instanceof Double) {
            do {
                System.out.println(msg);

                    if (sc.hasNextDouble()) {
                        e = sc.nextDouble();
                        valid = true;
                    } else if(((Double) e).doubleValue()<=0) {
                        System.out.println("Please Enter again");
                        valid = false;
                        e = sc.next();
                    }
                    else {
                        System.out.println("Please Enter again");
                        valid = false;
                        e = sc.next();
                    }

            } while (!valid);
        } else if (e instanceof Integer) {
            do {
                System.out.println(msg);
                if (sc.hasNextInt()) {
                    e = sc.nextInt();
                    valid = true;
                } else if(((Integer) e).intValue()<=0) {
                    System.out.println("Please Enter again");
                    valid = false;
                    e = sc.next();
                }
                else {
                    System.out.println("Please Enter again");
                    valid = false;
                    e = sc.next();
                }
            } while (!valid);
        }
        else if(e instanceof Date)
        {
            System.out.println(msg);
            do {
                sdf = new SimpleDateFormat("dd/MM/yyyy");
                sdf.setLenient(false);
                if (sc.hasNext()) {
                    try {
                        e = sdf.parse(sc.next());
                        valid = true;
                        return e;
                    } catch (ParseException pe) {
                        System.out.println("Invalid date Please try again");
                        valid = false;
                    }
                }
            }while(!valid);
        }
        else if(!(e instanceof RoomType))
        {
            ArrayList<String> al=new ArrayList<>();
            System.out.println(msg);
            do {
                    if (sc.hasNext()) {
                        e = sc.next();
                        RoomType[] types = RoomType.values();
                            for (RoomType t : types) {
                                al.add(t.toString().toUpperCase());
                                if ((t.toString().toUpperCase()).equals(e.toString().toUpperCase())) {
                                    e = t;
                                    valid = true;
                                    return e;
                                }
                            }
                            if(!al.contains(e.toString().toUpperCase()))
                            {
                                System.out.println("Please enter again Valid values are 'Single' or 'double'");
                                valid = false;
                                //e = sc.next();
                            }
                        }
                        else
                        {
                            System.out.println("Please enter again Valid values are 'Single' or 'double'");
                            valid = false;
                            e = sc.next();
                        }


            } while(!valid);
        }
        return e;
    }
    public String validateEmail(String emailmsg, String email, Scanner sc)
    {
        String regex = "^(.+)@(.+)$";
        String validateemail="";
        boolean valid=false;
        System.out.println(emailmsg);
        do {
            if(sc.hasNext())
            {
                validateemail=sc.next();
                if(validateemail.matches(regex)) {
                    valid = true;
                }
                else
                {
                    System.out.println("Please enter again");
                    valid=false;
                    //validateemail=sc.next();
                }
            }
            else
            {
                System.out.println("Please enter again");
                valid=false;
                validateemail=sc.next();
            }
        }while(!valid);
        return validateemail;
    }
}