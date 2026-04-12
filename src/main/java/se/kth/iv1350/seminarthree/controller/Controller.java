/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package se.kth.iv1350.seminarthree.controller;
import java.util.List;
import se.kth.iv1350.seminarthree.integration.CustomerRegistry;
import se.kth.iv1350.seminarthree.model.Bike;
import se.kth.iv1350.seminarthree.model.Customer;
import se.kth.iv1350.seminarthree.model.dto.CustomerDto;
import se.kth.iv1350.seminarthree.model.RepairOrder;
import se.kth.iv1350.seminarthree.model.dto.RepairOrderDto;
import se.kth.iv1350.seminarthree.model.RepairTask;
import se.kth.iv1350.seminarthree.integration.RepairOrderRegistry;
import se.kth.iv1350.seminarthree.model.RepairOrderState;

/**
 * This is the application's only controller. All method calls to the model
 * must pass through this controller.
 */

public class Controller {
    private final CustomerRegistry customerRegistry;
    private final RepairOrderRegistry repairOrderRegistry;
    
    /**
     * Creates an instance of {@code Controller}.
     *
     * @param customerRegistry the registry used for storing and retrieving instances of {@link Customer}.
     * @param repairOrderRegistry the registry that handles instances of {@link RepairOrder}.
     */
    public Controller(CustomerRegistry customerRegistry, RepairOrderRegistry repairOrderRegistry) {
        this.customerRegistry = customerRegistry;
        this.repairOrderRegistry = repairOrderRegistry;
    }
    
    /**
     * Searches for a {@link Customer} in the {@link CustomerRegistry}.
     *
     * <p>
     * This application does not handle alternative flows where a customer is missing
     * from the registry. Therefore, this method returns {@code null} if the requested
     * customer cannot be found.
     * </p>
     *
     * @param phoneNo the customer's phone number used as a lookup key.
     * @return a new {@link CustomerDto} based on the found customer reference, or {@code null}.
     */
    public CustomerDto searchCustomer(String phoneNo){
        Customer customer = customerRegistry.findCustomerByPhoneNo(phoneNo);
        return new CustomerDto(customer);
    }
    
    /**
     * Creates a {@link RepairOrder} based on the customer's problem description.
     *
     * <p>
     * The created repair order is then added to the {@link RepairOrderRegistry}.
     * </p>
     *
     * @param problemDescr the problem description provided by the customer.
     * @param phoneNo the customer's phone number.
     * @return the order ID of the created {@link RepairOrder}.
     */
    public Integer registerProblemDescription(String problemDescr, String phoneNo){
        Customer customer = customerRegistry.findCustomerByPhoneNo(phoneNo);
        RepairOrder repairOrder = new RepairOrder(problemDescr, customer); 
        repairOrderRegistry.addRepairOrder(repairOrder);
        return repairOrder.getOrderId();
    }
    
    /**
     * Returns a {@code RepairOrderDto} containing the information needed by the
     * technician to write a {@link DiagnosticReport} for a {@link Bike}.
     *
     * <p>
     * This method provides a DTO representation of the repair order that exposes only
     * the fields relevant to the technician's work.
     * </p>
     *
     * @param orderId the identifier of the repair order.
     * @return a DTO with technician‑relevant repair order data.
     */
    public RepairOrderDto getRepairOrderDto(Integer orderId) {
        RepairOrder repairOrder = repairOrderRegistry.findRepairOrderById(orderId);
        RepairOrderDto repairOrderDto = new RepairOrderDto(repairOrder);
        return repairOrderDto;
    }

    /**
     * Updates an existing {@link RepairOrder} after the technician completes the diagnostic phase.
     *
     * <p>
     * The repair order is updated by adding the determined repair tasks and the diagnostic report
     * written by the technician. The order's state is updated accordingly.
     * </p>
     *
     * @param orderId the identifier of the repair order to update.
     * @param tasks the list of {@code RepairTask} instances.
     * @param diagnosticReport the diagnostic report written by the technician.
     */
    public void updateAfterDiagnostic(List<RepairTask> tasks, String diagnosticReport, Integer orderId) {
        RepairOrder repairOrder = repairOrderRegistry.findRepairOrderById(orderId);
        repairOrder.updateRepairOrderAfterDiagnosis(tasks, diagnosticReport);
    }
    
    /**
     * Handles the customer's decision regarding the proposed repair tasks.
     *
     * <p>
     * If the repair order is accepted, the order state is updated and a receipt
     * containing all relevant repair order information is returned to the view layer.
     * The receipt is represented by a {@link RepairOrderDto}.
     * </p>
     *
     * <p>
     * This application does not support the alternative flow in which the customer
     * rejects the repair order.
     * </p>
     *
     * @param decision the customer's decision (accepted or rejected).
     * @param orderId  the identifier of the repair order.
     * @return a DTO representing the accepted repair order.
     */
    public RepairOrderDto handleCustomerDecision(RepairOrderState decision, Integer orderId) {
        RepairOrder repairOrder = repairOrderRegistry.findRepairOrderById(orderId);
        repairOrder.updateAfterAcceptance();
        RepairOrderDto acceptedOrderDto = new RepairOrderDto(repairOrder);
        return acceptedOrderDto;
    }

}
