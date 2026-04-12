/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package se.kth.iv1350.seminartree.model.dto;

import se.kth.iv1350.seminarthree.model.dto.BikeDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import se.kth.iv1350.seminarthree.model.Bike;


/**
 * Test the class {@link BikeDto}.
 */
public class BikeDtoTest {
    private Bike bike = new Bike("Monark", "Eva", "SVE1234567");
    private BikeDto bikeDto;
    
    public BikeDtoTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
        bikeDto = new BikeDto(bike);
    }
    
    @AfterEach
    public void tearDown() {
        bikeDto = null;
    }

    @Test
    public void testToStringBikeDto() {
        String strToTest = bikeDto.toString();

        assertTrue(strToTest.contains("Bike"), "Wrong class name in string.");
        assertTrue(strToTest.contains(bike.getBrand()), "Wrong brand in string.");
        assertTrue(strToTest.contains(bike.getModelName()), "Wrong model name in string.");
        assertTrue(strToTest.contains(bike.getSerialNo()),"Wrong serial number in string.");
    }
 
    
    
}
