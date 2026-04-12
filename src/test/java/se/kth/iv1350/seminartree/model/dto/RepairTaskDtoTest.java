/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package se.kth.iv1350.seminartree.model.dto;

import se.kth.iv1350.seminarthree.model.dto.RepairTaskDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import se.kth.iv1350.seminarthree.model.RepairTask;

/**
 * Test the class {@link RepairTaskDto}.
 */
public class RepairTaskDtoTest {
    private String taskDescription = "Change tire.";
    private RepairTask repairTask = new RepairTask(taskDescription);
    private RepairTaskDto repairTaskDto;

    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
        repairTaskDto = new RepairTaskDto(repairTask);
    }
    
    @AfterEach
    public void tearDown() {
        repairTaskDto = null;
    }

    @Test
    public void testToStringRepairTaskDto() {
        String strToTest = repairTaskDto.toString();

        assertTrue(strToTest.contains("Repair task"), "Wrong class name in string.");
        assertTrue(strToTest.contains(repairTask.getTaskDescription()), "Wrong task description in string.");
        assertTrue(strToTest.contains(String.valueOf(repairTask.getCost())), "Wrong task cost in string.");
        assertTrue(strToTest.contains(String.valueOf(repairTask.getState())), "Wrong state in string.");
    }
    
}
