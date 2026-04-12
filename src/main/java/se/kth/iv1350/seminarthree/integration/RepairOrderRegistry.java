/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package se.kth.iv1350.seminarthree.integration;


import java.util.HashMap;
import java.util.Map;
import se.kth.iv1350.seminarthree.model.RepairOrder;


/**
 * A registry that stores all repair orders ever created.
 * A {@link RepairOder} is never deleted from this registry.
*/
public class RepairOrderRegistry {
    private final Map<Integer, RepairOrder> repairOrders = new HashMap<>();
    
    /**
     * Adds a {@link RepairOrder} to this repair order registry.
     * @param repairOrder the {@code RepairOrder} wished to be added to this registry. 
     */
    public void addRepairOrder(RepairOrder repairOrder) {
        repairOrders.put(repairOrder.getOrderId(), repairOrder);
    }
    
    /**
     * Searches for a repair order by its order ID in the repair order registry.
     *
     * @param orderId the ID of the repair order to search for.
     * @return the {@link RepairOrder} that matches the order ID. 
    */
    public RepairOrder findRepairOrderById(Integer orderId) {
        return repairOrders.get(orderId);
    }
    
}
