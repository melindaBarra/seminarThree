/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package se.kth.iv1350.seminarthree.startup;

import se.kth.iv1350.seminarthree.controller.Controller;
import se.kth.iv1350.seminarthree.model.Customer;
import se.kth.iv1350.seminarthree.model.Bike;
import se.kth.iv1350.seminarthree.integration.RepairOrderRegistry;
import se.kth.iv1350.seminarthree.integration.CustomerRegistry;
import se.kth.iv1350.seminarthree.view.View;
/**
 * This class contains the applications main-method, which is used
 * to start the application. 
 */
public class Main {
    /**
     * The main method used to start the application. 
     * <p>
     * Initializes a demo setup containing a customer, a bike and
     * the required registries. This demo data is required so that
     * parts of the program that need existing domain objects can run.
     * </p>
     * 
     * @param args The application does not take any command lines.
     */
    public static void main(String[] args){
        String demoBikeSerialNo = "SVE1234567";
        Bike demoBike = new Bike("Monark", "Karin", demoBikeSerialNo);
        CustomerRegistry customerRegistry = new CustomerRegistry();
        String demoCustomerPhoneNo = "0731234567";
        Customer demoCustomer = new Customer(demoCustomerPhoneNo, "nils@kth.se", "Nils", demoBike);
        customerRegistry.addCustomer(demoCustomer);
        
        RepairOrderRegistry repairOrderRegistry = new RepairOrderRegistry();
        Controller contr = new Controller(customerRegistry, repairOrderRegistry);
        View view = new View(contr);
        view.runFakeExecution(demoCustomerPhoneNo);
    }
    
}


