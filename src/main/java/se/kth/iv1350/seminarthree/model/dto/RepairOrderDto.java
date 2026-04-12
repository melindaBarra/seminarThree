/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package se.kth.iv1350.seminarthree.model.dto;
import se.kth.iv1350.seminarthree.model.RepairOrder;
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;
import se.kth.iv1350.seminarthree.model.RepairOrderState;
import se.kth.iv1350.seminarthree.model.RepairTask;
import se.kth.iv1350.seminarthree.util.StringRepresentationUtil;

/**
 * A Data Transfer Object (DTO) that provides read-only access to 
 * {@link RepairOrder} information. 
 * <p>
 * This class is used to safely transfer 
 * repair order data between different layers of the application 
 * without exposing the internal state of the domain model..
 * </p>
 */

public class RepairOrderDto {
    private Integer orderID;
    private String problemDescription;
    private CustomerDto customerDto;
    private LocalDate date;
    private LocalDate estimatedCompletionDate;
    private RepairOrderState state;
    private String diagnosticReport;
    private List<RepairTaskDto> repairTasks;

    /**
     * Creates a new {@code RepairOrderDto} that represents the given {@code RepairOrder}.
     * <p>
     * Before the customer accepts this order, only some order information is initialized.
     * This includes information about the order's ID, problem description, state, date,
     * {@code ustomerDto} and {@code BikeDto}.
     * After acceptance, this DTO also includes the order's diagnostic report, proposed repair tasks and the
     * estimated completion date.
     * </p>
     *
     * @param repairOrder the repair order to create a DTO from. Must not be {@code null}.
     */
    public RepairOrderDto(RepairOrder repairOrder) {
        this.orderID = repairOrder.getOrderId();
        this.problemDescription = repairOrder.getProblemDescription();
        this.state = repairOrder.getState();
        this.date = repairOrder.getDate();
        this.customerDto = new CustomerDto(repairOrder.getCustomer());

        this.diagnosticReport = repairOrder.getDiagnosticReport();
        this.estimatedCompletionDate = repairOrder.getEstimatedCompletionDate();
        this.repairTasks = createRepairTaskDtos(repairOrder);
    }

    /**
     * Converts the list of {@code RepairTask} objects in the given {@code RepairOrder}
     * into a list of {@code RepairTaskDto} objects. 
     * <p>
     * If the repair order has no repair tasks
     * the returned list will be empty.
     * </p>
     *
     * @param repairOrder the repair order containing the domain repair tasks
     * @return a list of {@code RepairTaskDto} representing the repair tasks.
     */
    private List<RepairTaskDto> createRepairTaskDtos(RepairOrder repairOrder) {
        List<RepairTaskDto> taskDtos = new ArrayList<>();

        if (repairOrder.getRepairTasks() != null) {
            for (RepairTask task : repairOrder.getRepairTasks()) {
                taskDtos.add(new RepairTaskDto(task));
            }
        }
        
        return taskDtos;
    }

   public String getProblemDescription(){
       return this.problemDescription;
   }
  
   public Integer getOrderId(){
       return this.orderID;
   }
   
   public CustomerDto getCustomerDto(){
       return this.customerDto;
   }
   
   public RepairOrderState getState(){
       return this.state;
   }
    
    /**
     * Adds all repair tasks to the given string builder, each on a new line.
     * 
     * @param builder the given StringBuilder.
     */
   private void appendRepairTasks(StringBuilder builder) {
        for (RepairTaskDto task : repairTasks) {
            builder.append(task);
            builder.append("\n");
        }
    }

    
    /**
     * Returns a string representation of this {@code RepairOrderDto}.
     * @return A string representation of this RepairOrderDto.
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("Repair order[");

        StringRepresentationUtil.addField(builder, "Order ID", orderID);
        StringRepresentationUtil.addSeparator(builder);

        StringRepresentationUtil.addField(builder, "State", state);
        StringRepresentationUtil.addSeparator(builder);
        
        StringRepresentationUtil.addField(builder, "Date of order placement", date);
        StringRepresentationUtil.addSeparator(builder);
        builder.append("\n");

        StringRepresentationUtil.addField(builder, "Problem Description", problemDescription);
        StringRepresentationUtil.addSeparator(builder);

        
        StringRepresentationUtil.addField(builder, "Diagnostic report", diagnosticReport);
        builder.append("]\n");
        
        appendRepairTasks(builder);
        
        builder.append(customerDto);
        
        return builder.toString();
    }


}