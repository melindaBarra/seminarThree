/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package se.kth.iv1350.seminartree.model.dto;

import se.kth.iv1350.seminarthree.model.dto.RepairOrderDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import se.kth.iv1350.seminarthree.model.Bike;
import se.kth.iv1350.seminarthree.model.Customer;
import se.kth.iv1350.seminarthree.model.RepairOrder;

/**
 * Test the class {@link RepairOrderDto}.
 */
public class RepairOrderDtoTest {
    private Bike customerBike = new Bike("Monark", "Eva", "SVE1234567");
    private Customer customer = new Customer("0730000000","nils@kth.se", "Nils", customerBike);
    private String problemDescr = "Bike has a flat tire.";
    private RepairOrder repairOrder = new RepairOrder(problemDescr, customer);
    private RepairOrderDto repairOrderDto;
    

    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
        repairOrderDto = new RepairOrderDto(repairOrder);
    }
    
    @AfterEach
    public void tearDown() {
        repairOrderDto = null;
    }

    @Test
    public void testToStringRepairOrderDto() {
        String strToTest = repairOrderDto.toString();

        assertTrue(strToTest.contains("Repair order"), "Wrong class name in string.");
        assertTrue(strToTest.contains(repairOrder.getProblemDescription()), "Wrong problem description in string.");
        assertTrue(strToTest.contains(String.valueOf(repairOrder.getOrderId())), "Wrong order ID in string.");
        assertTrue(strToTest.contains(String.valueOf(repairOrder.getState())), "Wrong state in string.");
        assertTrue(strToTest.contains(String.valueOf(repairOrder.getDate())), "Wrong date in string.");
        assertTrue(strToTest.contains("Customer"), "Missing customer details.");
        assertTrue(strToTest.contains("Bike"), "Missing bike details.");
    }
    
}
