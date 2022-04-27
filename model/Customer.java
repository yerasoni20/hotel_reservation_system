package model;

public class Customer {
    private String firstname,lastname,email;
    public static final String regex = "^(.+)@(.+)$";
    public String getFirstname(){
        return firstname;
    }
    public String getLastname(){
        return lastname;
    }
    public String getEmail(){
        return email;
    }
    public Customer(String fname,String lname,String email)
    {

            if (email.matches(regex)) {
                this.email = email;
                this.firstname = fname;
                this.lastname = lname;
            } else {
                System.out.println("Invalid email. Please provide correct email address");
            }
    }
    @Override
    public String toString()
    {
        return firstname+" "+lastname+" "+email;
    }
}
