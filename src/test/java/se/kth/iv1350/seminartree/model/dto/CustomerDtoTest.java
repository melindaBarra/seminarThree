/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package se.kth.iv1350.seminartree.model.dto;

import se.kth.iv1350.seminarthree.model.dto.CustomerDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import se.kth.iv1350.seminarthree.model.Bike;
import se.kth.iv1350.seminarthree.model.Customer;

/**
 * Test the class {@link CustomerDto}.
 */
public class CustomerDtoTest {
    private Bike customerBike = new Bike("Monark", "Eva", "SVE1234567");
    private Customer customer = new Customer("0730000000","nils@kth.se", "Nils", customerBike);
    private CustomerDto customerDto;
    
    public CustomerDtoTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
        customerDto = new CustomerDto(customer);
    }
    
    @AfterEach
    public void tearDown() {
        customerDto = null;
    }

    @Test
    public void testToStringCustomerDto() {
        String strToTest = customerDto.toString();

        assertTrue(strToTest.contains("Customer"), "Wrong class name in string.");
        assertTrue(strToTest.contains(customer.getName()), "Wrong name in string.");
        assertTrue(strToTest.contains(customer.getEmail()), "Wrong email in string.");
        assertTrue(strToTest.contains(customer.getPhoneNo()), "Wrong phone number in string.");
        assertTrue(strToTest.contains("Bike"), "Missing bike details.");
    }

    
}
