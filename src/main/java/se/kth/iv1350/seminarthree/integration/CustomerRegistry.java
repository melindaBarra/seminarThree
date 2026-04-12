/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package se.kth.iv1350.seminarthree.integration;
import java.util.HashMap;
import java.util.Map;
import se.kth.iv1350.seminarthree.model.Customer;

/**
 * A registry containing details of all customers who have ever consulted the workshop.
 * Customers are stored using their phone number as the lookup key.
 */
public class CustomerRegistry {

    private final Map<String, Customer> customers = new HashMap<>();
    
    /**
     * Adds a {@link Customer} to this customer registry.
     * @param customer the {@code Customer} to be added to this registry. 
     */
    public void addCustomer(Customer customer) {
        customers.put(customer.getPhoneNo(), customer);
    }

    /**
     * Searches for a customer by phone number in the customer registry.
     * <p>
     * Because the application does not handle alternative flows,
     * all given phone numbers are expected to belong to an already registered customer.
     * Therefore, this method is expected to always return a customer reference and never {@code null}.
     * </p>
     * @param phoneNo the searched customer's phone number, used as a lookup key.
     * @return the matching customer.
    */
    public Customer findCustomerByPhoneNo(String phoneNo) {
        return customers.get(phoneNo);
    }

}

