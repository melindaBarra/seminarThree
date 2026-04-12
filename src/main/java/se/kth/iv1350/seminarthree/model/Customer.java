/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package se.kth.iv1350.seminarthree.model;

/**
 * Represents a customer who brings a {@link Bike} to the bike repair workshop.
 *
 * <p>
 * A customer is identified by a unique phone number and contains information
 * about the customer's name, email address, and the bike that needs to be repaired.
 * </p>
 *
 * <p>
 * A customer is registered in a {@link CustomerRegistry} and visits a bike
 * repair workshop to request a {@link RepairOrder} for its associated {@link Bike}.
 * </p>
 */
public class Customer {
    private String phoneNo;
    private String email;
    private String name;
    private Bike bike;    

    /**
     * Creates a instance of {@code Customer}.
     * @param phoneNo the customer's phone number.
     * @param email the customer's email address.
     * @param name the customer's name.
     * @param bike the customer's {@code bike} that is wished to be repaired.
    */
    public Customer(String phoneNo, String email, String name, Bike bike) {
        this.phoneNo = phoneNo;
        this.email = email;
        this.name = name;
        this.bike = bike;
    }

    public String getPhoneNo() {
        return this.phoneNo;
    }
    public String getEmail() {
        return this.email;
    }
    public String getName() {
        return this.name;
    }
    public Bike getBike() {
        return this.bike;
    }

    /**
     * Computes this customer's hash code based on its unique phone number.
     *
     * <p>
     * This makes the hash code consistent with the {@link #equals(Object)} method,
     * since two customers with the same phone number will produce the same hash code.
     * </p>
     *
     * @return the hash code for this customer.
     */
    @Override
    public int hashCode() {
        return this.phoneNo.hashCode();
    }
    
    /**  
     * Compares this object with another to determine equality.
     * <p>
     * Two instances are considered equal if:
     * the other object is of the same class and their identifying fields (the phone number) are equal.
     * This method must be consistent with {@link #hashCode()}.
     * </p>
     *
     * @param obj the object to compare with this instance.
     * @return {@code true} if the objects are equal, else {@code false}.
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Customer)) {
            return false;
        }
        Customer other = (Customer) obj;
        return this.phoneNo.equals(other.phoneNo);
    }
}

