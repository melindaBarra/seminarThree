/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package se.kth.iv1350.seminartree.controller;

import se.kth.iv1350.seminarthree.controller.Controller;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import se.kth.iv1350.seminarthree.model.dto.CustomerDto;
import se.kth.iv1350.seminarthree.model.Customer;
import se.kth.iv1350.seminarthree.integration.CustomerRegistry;
import se.kth.iv1350.seminarthree.integration.RepairOrderRegistry;
import se.kth.iv1350.seminarthree.model.Bike;
import se.kth.iv1350.seminarthree.model.dto.BikeDto;
import se.kth.iv1350.seminarthree.model.RepairOrder;
import se.kth.iv1350.seminarthree.model.dto.RepairOrderDto;
import se.kth.iv1350.seminarthree.model.RepairTask;
import se.kth.iv1350.seminarthree.model.RepairOrderState;
import org.junit.jupiter.api.Disabled;
import java.util.List;

/**
 * Test the class {@link Controller}.
 */
public class ControllerTest {
    private String customerPhoneNo = "0730311316";
    private String customerEmail = "mbarra@kth.se";
    private String customerName = "Nils";
    private String bikeSerialNo = "SVE1234567";
    private Bike customerBike = new Bike("Monark", "Karin", bikeSerialNo);
    private Customer customerNils = new Customer(customerPhoneNo, customerEmail, customerName, customerBike);
 
    private String problemDescr = "A problem description";
    private CustomerRegistry customerRegistry;
    private RepairOrderRegistry repairOrderRegistry;
    private Controller ctrl;
    private RepairTask task = new RepairTask("A task");
    private String diagnosticReport = "The bike is in bad condition" ;
    

    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
        customerRegistry = new CustomerRegistry();
        customerRegistry.addCustomer(customerNils);
        repairOrderRegistry = new RepairOrderRegistry();
        ctrl = new Controller(customerRegistry, repairOrderRegistry);
    }
    

    @AfterEach
    public void tearDown() {
        customerRegistry  = null;
    }

   
    @Test
    public void testSearchCustomerCorrectName() {
        CustomerDto returnedCustomerDto = ctrl.searchCustomer(customerPhoneNo);
        String result = returnedCustomerDto.getName();
        assertEquals(customerName, result, "Wrong name given by the returned DTO");
    }
    
    @Test
    public void testSearchCustomerCorrectPhoneNo() {
        CustomerDto returnedCustomerDto = ctrl.searchCustomer(customerPhoneNo);
        String result = returnedCustomerDto.getPhoneNo();
        assertEquals(customerPhoneNo, result,"Wrong phone number given by the returned DTO");

    }
        @Test
    public void testSearchCustomerCorrectEmail() {
        CustomerDto returnedCustomerDto = ctrl.searchCustomer(customerPhoneNo);
        String result = returnedCustomerDto.getEmail();
        assertEquals(customerEmail, result, "Wrong email given by the returned DTO");
    }
        @Test
    public void testSearchCustomerCorrectBike() {
        CustomerDto returnedCustomerDto = ctrl.searchCustomer(customerPhoneNo);
        BikeDto returnedBikeDto = returnedCustomerDto.getBikeDto();
        String result = returnedBikeDto.getSerialNo();
        assertEquals(bikeSerialNo, result, "Wrong bike given by the returned DTO");
    }

    @Disabled("Alt flow")
    @Test
    public void testSearchCustomerWithUnknownPhoneNo() {
        CustomerDto result = ctrl.searchCustomer("0000000000");

        assertNull(result, "Expected null when customer is not found.");
    }


 
    @Test
    public void testIfRepairOrderGetsRegistered() {
        RepairOrder result = getRegisteredRepairOrder();

        assertNotNull(result, "No repair order was created.");
    }

    @Test
    public void testCorrectProblemDescriptionIsRegistered() {
        RepairOrder registeredOrder = getRegisteredRepairOrder();
        String registeredProbDescr = registeredOrder.getProblemDescription();

        assertEquals(problemDescr, registeredProbDescr, "Wrong problem description was registered.");
    }

    @Test
    public void testCorrectCustomerIsRegistered() {
        RepairOrder registeredOrder = getRegisteredRepairOrder();
        Customer registeredCustomer = registeredOrder.getCustomer();

        assertEquals(customerNils, registeredCustomer, "Wrong customer was registered for the repair order.");
    }

    @Test
    public void testCorrectBikeIsRegistered() {
        RepairOrder registeredOrder = getRegisteredRepairOrder();
        Bike registeredBike = registeredOrder.getCustomer().getBike();

        assertEquals(customerBike, registeredBike, "Wrong bike was registered for the repair order.");
    }

    
    
    
    @Test
    public void testGetRepairOrderDtoContainsCorrectOrder() {
        Integer expResultId = ctrl.registerProblemDescription(problemDescr, customerPhoneNo);
        RepairOrderDto orderDto = ctrl.getRepairOrderDto(expResultId);
        Integer resultId = orderDto.getOrderId();

        assertEquals(expResultId, resultId, "Wrong order ID in RepairOrderDto.");
    }

    @Test
    public void testGetRepairOrderDtoContainsCorrectProblemDescription() {
        RepairOrderDto orderDto = getRegisteredRepairOrderDto();

        assertEquals(problemDescr, orderDto.getProblemDescription(), "Wrong problem description in RepairOrderDto.");
    }

    @Test
    public void testGetRepairOrderDtoContainsCorrectCustomer() {
        RepairOrderDto orderDto = getRegisteredRepairOrderDto();
        CustomerDto customerDto = orderDto.getCustomerDto();
        String result = customerDto.getPhoneNo();

        assertEquals(customerPhoneNo, result, "Wrong customer in CustomerDto.");
    }


    @Test
    public void testGetRepairOrderDtoContainsCorrectBike() {
        RepairOrderDto orderDto = getRegisteredRepairOrderDto();
        CustomerDto customerDto = orderDto.getCustomerDto();
        BikeDto bikeDto = customerDto.getBikeDto();
        
        String result = bikeDto.getSerialNo();
        assertEquals(bikeSerialNo, result, "Wrong bike in BikeDto.");
    }

    
    @Test
    public void testUpdateAfterDiagnosticUpdatesTask() {
        RepairOrder resultOrder = getUpdatedRepairOrder();
        
        List<RepairTask> resultTasks = resultOrder.getRepairTasks();
        String expTask = task.getTaskDescription();
        String resultTask = resultTasks.get(0).getTaskDescription();
        assertEquals(expTask, resultTask, "Wrong repair task was added to repair order.");
    }
    
    @Test
    public void testUpdateAfterDiagnosticUpdatesReport() {
        
        RepairOrder resultOrder = getUpdatedRepairOrder();
        String addedReport = resultOrder.getDiagnosticReport();
        
        assertEquals(diagnosticReport, addedReport, "Wrong report was added to repair order.");
    }

    
    
    
    @Test
   public void testHandleCustomerDecisionReturnsDto() {
       RepairOrderDto resultDto = getRepairOrderDtoAfterAcceptance();
       assertNotNull(resultDto, "RepairOrderDto was not returned.");
   }
   
   @Test
    public void testIfHandleCustomerDecisionUpdatesState() {
        RepairOrderDto orderDto = getRepairOrderDtoAfterAcceptance();

        assertEquals(RepairOrderState.ACCEPTED, orderDto.getState());
    }


   
   private RepairOrderDto getRegisteredRepairOrderDto() {
        Integer orderId = ctrl.registerProblemDescription(problemDescr, customerPhoneNo);
        return ctrl.getRepairOrderDto(orderId);
   }
   
    private RepairOrder getRegisteredRepairOrder() {
        Integer orderId = ctrl.registerProblemDescription(problemDescr, customerPhoneNo);
        return repairOrderRegistry.findRepairOrderById(orderId);
   }
    
    private RepairOrderDto getRepairOrderDtoAfterAcceptance() {
        Integer orderId = ctrl.registerProblemDescription(problemDescr, customerPhoneNo);
        return ctrl.handleCustomerDecision(RepairOrderState.ACCEPTED, orderId);
   }
    
    private RepairOrder getUpdatedRepairOrder() {
        Integer orderId = ctrl.registerProblemDescription(problemDescr, customerPhoneNo);
        List<RepairTask> tasksToAdd = List.of(task);
        
        ctrl.updateAfterDiagnostic(tasksToAdd, diagnosticReport, orderId); 
        
        return  repairOrderRegistry.findRepairOrderById(orderId);
    }

   

}
