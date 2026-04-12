/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package se.kth.iv1350.seminarthree.model.dto;
import se.kth.iv1350.seminarthree.model.Customer;
import se.kth.iv1350.seminarthree.util.StringRepresentationUtil;

/**
 * A Data Transfer Object (DTO) that provides read-only access to 
 * customer information. 
 * <p>
 * This class is used to safely transfer 
 * customer data between different layers of the application 
 * without exposing the internal state of the domain model..
 * </p>
 */
public class CustomerDto {
    private String phoneNo;
    private String email;
    private String name;
    private BikeDto bikeDto;
    
   /**
    * Creates a instance of {@code CustomerDto}.
    * @param customer is the {@link Customer} which the CustomerDto is based on. 
   */
   public CustomerDto(Customer customer ) {
        this.phoneNo = customer.getPhoneNo();
        this.email = customer.getEmail();
        this.name = customer.getName();
        this.bikeDto = new BikeDto(customer.getBike());
   }

   public String getName(){
       return this.name;
   }
   public String getEmail(){
       return this.email;
   }
   public String getPhoneNo(){
       return this.phoneNo;
   }
   public BikeDto getBikeDto() {
       return this.bikeDto;
   }
    
    /**
     * Returns a string representation of this CustomerDto. 
     * @return A string representation of this CustomerDto.
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("Customer");
        builder.append("[");
        StringRepresentationUtil.addField(builder, "Name", name);
        StringRepresentationUtil.addSeparator(builder);
        StringRepresentationUtil.addField(builder, "Email", email);
        StringRepresentationUtil.addSeparator(builder);
        StringRepresentationUtil.addField(builder, "PhoneNo", phoneNo);
        builder.append("]\n");
        builder.append(bikeDto);
        return builder.toString();
    }



}
