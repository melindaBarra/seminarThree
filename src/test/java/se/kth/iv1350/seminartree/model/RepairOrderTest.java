/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package se.kth.iv1350.seminartree.model;

import se.kth.iv1350.seminarthree.model.RepairTask;
import se.kth.iv1350.seminarthree.model.Bike;
import se.kth.iv1350.seminarthree.model.RepairOrder;
import se.kth.iv1350.seminarthree.model.RepairOrderState;
import se.kth.iv1350.seminarthree.model.Customer;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test the class {@link RepairOrder}.
 */
public class RepairOrderTest {
    private Bike customerBike = new Bike("Monark", "Karin", "SVE1234567");
    private Customer customerNils = new Customer("0701234567", "nils@kth.com", "Nils", customerBike);
    private String problemDescr = "The bike is worn out";
    private RepairTask taskToAdd = new RepairTask("Replace chain.");
    private RepairTask additionalTaskToAdd = new RepairTask("Fix breaks.");
    private String diagnosticReport = "Chain snapped";
    private RepairOrder order;

  
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    

    @BeforeEach
    public void setUp() {
        order = new RepairOrder(problemDescr, customerNils);
     
    }

    
    @AfterEach
    public void tearDown() {
        order = null;
    }
    
    @Test
    public void testOrderIdIsGenerated() {
        Integer id = order.getOrderId();
        assertNotNull(id, "Id was not generated");
    }
    
    @Test
    public void testNewOrderIdIsGenerated() {
        Integer id = order.getOrderId();
        
        String otherProbDescr = "Wheel is missing";
        RepairOrder otherOrder = new RepairOrder(otherProbDescr, customerNils);
        Integer otherId = otherOrder.getOrderId();
        
        assertNotEquals(id, otherId, "A new ID was not generated");
    }


   @Test
   public void testIfMultipleTasksWereAdded() {
       order.updateRepairOrderAfterDiagnosis(List.of(taskToAdd, additionalTaskToAdd), diagnosticReport);
       int expResult = 2;
       int result = order.getRepairTasks().size();

       assertEquals(expResult, result, "The correct number of tasks were not added.");
   }
   
   @Test
   public void testIfOneTasksWasAdded() {
       order.updateRepairOrderAfterDiagnosis(List.of(taskToAdd), diagnosticReport);
       int expResult = 1;
       int result = order.getRepairTasks().size();

       assertEquals(expResult, result, "The correct number of tasks were not added.");
   }

  
   @Test
   public void testIfDiagnosticReportIsAdded() {
       order.updateRepairOrderAfterDiagnosis(List.of(taskToAdd, additionalTaskToAdd), diagnosticReport);
       String result = order.getDiagnosticReport();

       assertEquals(diagnosticReport, result, "Diagnostic report was not set correctly.");
   }

    @Test
    public void testStateChangedToReadyForApproval() {
        order.updateRepairOrderAfterDiagnosis(List.of(taskToAdd, additionalTaskToAdd), diagnosticReport);
        RepairOrderState expResult = RepairOrderState.READY_FOR_APPROVAL;
        RepairOrderState result = order.getState();

        assertEquals(expResult, result, "State was not updated correctly.");
    }

   @Test
   public void testStateChangedToAccepted() {
       order.updateAfterAcceptance();
       RepairOrderState expResult = RepairOrderState.ACCEPTED;
       RepairOrderState result = order.getState();
       assertEquals(expResult, result, "State was not updated to correctly.");
   }

   @Test
   public void testIfEstimatedCompletionDateIsCorrect() {
       LocalDate expDate = LocalDate.now().plusDays(RepairOrder.STANDARD_COMPLETION);
       order.updateAfterAcceptance();
       LocalDate resultDate = order.getEstimatedCompletionDate();
       assertEquals(expDate, resultDate, "Estimated completion date was incorrect.");
   }

    
}
