/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package se.kth.iv1350.seminarthree.model;
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;
import java.security.SecureRandom;


/**
 * Represents a repair order for a {@link Bike}. 
 * <p>
 * A repair order is created when a {@link Customer} reports a problem.
 * An order includes customer and bike details, the reported
 * {@code problemDescription}, the {@link DiagnosticReport} created by the technician and
 * the list of {@code repairTasks} which specify the repair work to be performed.
 * </p>
 * <p>
 * A repair order progresses through different {@link RepairOrderState} values, from initial registration
 * to diagnostic evaluation, customer
 * approval and lastly completion of the repair.
 * This application does not support the alternative workflow
 * in which the customer rejects the repair order.
 * </p>
 * <p>
 * The constant {@code STANDARD_COMPLETION} defines the default number of days
 * typically needed to complete a repair after the customer has accepted the order.
 * This value is used to calculate the repair's estimated completion date.
 * </p>
 * <p>
 * The constant {@code MAX_ORDER_ID_VALUE} defines exclusive upper bound
 * used when generating random order IDs.
 * </p>
 */
public class RepairOrder {
    public static final int STANDARD_COMPLETION = 5;
    public static final int MAX_ORDER_ID_VALUE = 1000;
    private SecureRandom idGenerator = new SecureRandom();
    private Integer orderId;
    private String problemDescription;
    private String diagnosticReport;
    private Customer customer;
    private LocalDate date;
    private LocalDate estimatedCompletionDate;
    private RepairOrderState state;
    private List<RepairTask> repairTasks = new ArrayList<>();
    
    /**
     * Creates an instance of {@code RepairOrder}.
     * <p>
     * This constructor saves the date when the order was created
     * assigns a unique order ID and sets the order state to
     * {@code NEWLY_CREATED}.
     * </p>
     * @param problemDescription  a description of the bike's problems (provided by the customer).
     * @param customer the {@code Customer} who requested the repair.
    */
    public RepairOrder(String problemDescription, Customer customer) {
        this.problemDescription = problemDescription;
        this.customer = customer;
        setDate();
        this.orderId = generateOrderId();
        this.state = RepairOrderState.NEWLY_CREATED;
    }

    /**
     * Adds a single instance of {@link RepairTask} to this repair order.
     *
     * @param task the repair task to add
     */
    public void addRepairTask(RepairTask task) {
        repairTasks.add(task);
    }
    
    /**
     * Adds all repair tasks given by a technician to this repair order.
     * This method is called after the diagnostic phase when the
     * technician has identified multiple tasks that need to be performed.
     *
     * @param tasks the list of repair tasks to add.
     */
    public void addRepairTasks(List<RepairTask> tasks) {
        repairTasks.addAll(tasks);
    }

    /**
     * Generates a unique identifier for this repair order.
     * The value is randomly generated and used to distinguish
     * this order from all other repair orders in the system.
     *
     * @return a randomly generated order ID
     */
    private Integer generateOrderId() {
        return idGenerator.nextInt(MAX_ORDER_ID_VALUE);
    }
    
    /**
     * Creates a new {@code RepairOrder} instance with default values.
     * <p>
     * The order's creation date is initialized to the current
     * local date and time to show when the instance was created.
     * </p>
    */  
    public RepairOrder() {
        setDate();
    }
    
    private void setDate() {
        this.date = LocalDate.now();
    }
    
    public Integer getOrderId() {
        return this.orderId;
    }
    
    public String getProblemDescription() {
        return this.problemDescription;
    }
    
    public Customer getCustomer() {
        return this.customer;
    }
    
    public RepairOrderState getState() {
        return this.state;
    }
        
    public LocalDate getEstimatedCompletionDate() {
        return this.estimatedCompletionDate;
    }
    
    public LocalDate getDate() {
        return this.date;
    }
    
    public String getDiagnosticReport() {
        return this.diagnosticReport;
    }
    
    public List<RepairTask> getRepairTasks() {
        return this.repairTasks;
    }
    
    /**
     * Updates this {@code RepairOrder} after the diagnostic phase has been completed.
     * <p>
     * The repair order is updated by adding repair tasks, attaching the
     * provided diagnostic report and changing the order's state to
     * {@code READY_FOR_APPROVAL}.
     * </p>
     *
     * @param tasks the list of repair tasks identified during the diagnostic.
     * @param diagnosticReport the diagnostic report created by the technician.
     */
   public void updateRepairOrderAfterDiagnosis(List<RepairTask> tasks, String diagnosticReport) {
        addRepairTasks(tasks);
        this.diagnosticReport = diagnosticReport;
        this.state = RepairOrderState.READY_FOR_APPROVAL;
    }
   
   /**
    * Updates this repair order after the customer has accepted the repair.
    * The order state is set to {@code ACCEPTED} and an estimated completion
    * date is calculated based on the standard number of days needed for a repair.
    */
   public void updateAfterAcceptance() {
       this.state = RepairOrderState.ACCEPTED;
       estimateCompletionDate();
   }

    /**
      * Calculates and sets the estimated completion date for this repair order.
      * The date is based on the date when the repair was accepted and the
      * standard number of days needed for a repair
      * defined by {@code STANDARD_COMPLETION}.
      */
    private void estimateCompletionDate() {
        LocalDate dateOfAcceptance = LocalDate.now();
        this.estimatedCompletionDate = dateOfAcceptance.plusDays(STANDARD_COMPLETION);
    }
       
}
