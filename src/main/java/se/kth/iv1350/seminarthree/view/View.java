/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package se.kth.iv1350.seminarthree.view;
import se.kth.iv1350.seminarthree.controller.Controller;
import se.kth.iv1350.seminarthree.model.dto.CustomerDto;
import se.kth.iv1350.seminarthree.model.dto.RepairOrderDto;
import se.kth.iv1350.seminarthree.model.RepairTask;
import se.kth.iv1350.seminarthree.model.RepairOrderState;
import java.util.List;
import java.util.ArrayList;

/**
 * This is a placeholder for the real view. It contains a hardcoded execution
 * with calls to all system operations in the controller.
 */
public class View {
    private final Controller contr; 
    
    /**
     * Creates a new instance that uses the specified controller for all
     * calls to other layers.
     *
     * @param contr the controller used for all calls to other layers.
     */
    public View(Controller contr){
        this.contr = contr;
    }
    
    /**
     * Executes a hardcoded demonstration of the application's workflow.
     *
     * <p>
     * This method simulates a complete repair process by calling several system
     * operations in the {@link Controller}. This method assumes that the customer and bike
     * already exist in the {@link CustomerRegistry} since {@code searchCustomer} requires a
     * pre‑registered customer.
     * </p>
     *
     * @param customerPhoneNo the phone number of the customer used during the simulation.
     */
    public void runFakeExecution(String customerPhoneNo) {
        //contr.ettsystemanrop;
        System.out.println("---Bike repair workshop---\n");
        CustomerDto customerDto = contr.searchCustomer(customerPhoneNo);
        System.out.println("Searching for customer.");
        System.out.println("Found customer and bike details: ");
        System.out.println(customerDto);
        System.out.println("\n");

        String problemDescr = "The customer's bike always gets a flat tire";
        Integer orderId = contr.registerProblemDescription(problemDescr, customerPhoneNo);
        System.out.println("Registers the customer's problem description and creates an order.");
        RepairOrderDto repairOrderDto = contr.getRepairOrderDto(orderId);
        System.out.println("Repair order details:");
        System.out.println(repairOrderDto);
        System.out.println("\n");
  
  
        String diagnosticReport = "A wheel is missing";
        List<RepairTask> tasks = new ArrayList<>();
        tasks.add(new RepairTask("Replace brake pads"));
        tasks.add(new RepairTask("Add a new wheel"));
        contr.updateAfterDiagnostic(tasks, diagnosticReport, orderId);
        System.out.println("Updates the repair order after diagnosis.");
        System.out.println("\n");
        
        System.out.println("Customer accepts the repair tasks and cost proposed by the receptionist.");
        repairOrderDto = contr.handleCustomerDecision(RepairOrderState.ACCEPTED, orderId);
        System.out.println("Accepted repair order details:");
        System.out.println(repairOrderDto);
        System.out.println("\n");
        
        System.out.println("---Customer leaves the workshop---");
    }
    
}
