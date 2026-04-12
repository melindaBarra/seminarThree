/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package se.kth.iv1350.seminarthree.model.dto;
import se.kth.iv1350.seminarthree.model.RepairTaskState;
import se.kth.iv1350.seminarthree.model.RepairTask;
import se.kth.iv1350.seminarthree.util.StringRepresentationUtil;

/**
 * A Data Transfer Object (DTO) that provides read-only access to 
 * {@link RepairTask} information. 
 * <p>
 * This class is used to safely transfer 
 * repair task data between different layers of the application 
 * without exposing the internal state of the domain model..
 * </p>
 */
public class RepairTaskDto {
    private int cost;
    private RepairTaskState state = RepairTaskState.INCOMPLETE;
    private String taskDescription;
    
    /**
     * Creates a new instance of {@code RepairTaskDto} that represents the given {@code RepairTask}.
     *
     * @param repairTask the repair order to create a DTO from. Must not be {@code null}.
     */
    
    public RepairTaskDto(RepairTask repairTask) {
        this.cost = repairTask.getCost();
        this.state = repairTask.getState();
        this.taskDescription = repairTask.getTaskDescription();
    }

    
    /**
     * Returns a string representation of this {@code RepairTaskDto}.
     * @return A string representation of this RepairOrderDto.
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("Repair task");
        builder.append("[");

        StringRepresentationUtil.addField(builder, "Task description", taskDescription);
        StringRepresentationUtil.addSeparator(builder);

        StringRepresentationUtil.addField(builder, "cost", cost);
        StringRepresentationUtil.addSeparator(builder);

        StringRepresentationUtil.addField(builder, "state", state);
        builder.append("]");

        return builder.toString();
    }
    

    
    
}