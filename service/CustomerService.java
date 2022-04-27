package service;

import model.Customer;

import java.util.ArrayList;
import java.util.Collection;

public class CustomerService {
    private static CustomerService cs=null;

    private CustomerService()
    {}

    public static CustomerService getInstance()
    {
        if(cs==null)
        {
            cs=new CustomerService();
        }
        return cs;
    }
    static Collection<Customer> customers=new ArrayList<>();
    public static void addCustomer(String firstname, String lastname,String email)
    {
        customers.add(new Customer(firstname,lastname,email));
    }

    public static Customer getCustomer(String customerEmail)
    {
        for(Customer c: customers)
        {
            if(c.getEmail().equals(customerEmail))
            {
                return c;
            }
        }
        return null;
    }

    public static Collection<Customer> getAllCustomers()
    {
        return customers;
    }
}
