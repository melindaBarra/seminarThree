/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package se.kth.iv1350.seminarthree.model.dto;
import se.kth.iv1350.seminarthree.model.Bike;
import se.kth.iv1350.seminarthree.util.StringRepresentationUtil;

/**
 * A Data Transfer Object (DTO) that provides read-only access to 
 * {@link Bike} information. 
 * <p>
 * This class is used to safely transfer 
 * bike data between different layers of the application 
 * without exposing the internal state of the domain model.
 * </p>
 */
public class BikeDto {

    private String brand;
    private String modelName;
    private String serialNo;

    /**
     * Creates a {@code BikeDto} based on the given {@code Bike} object.
     * 
     * @param bike the bike that the DTO is based on.
     */
    public BikeDto(Bike bike) {
        this.brand = bike.getBrand();
        this.modelName = bike.getModelName();
        this.serialNo = bike.getSerialNo();
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return modelName;
    }

    public String getSerialNo() {
        return serialNo;
    }
    
    
    /**
     * Returns a string representation of this {@code BikeDto}. 
     * @return A string representation of this BikeDto.
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("Bike");
        builder.append("[");
        StringRepresentationUtil.addField(builder, "Brand", brand);
        StringRepresentationUtil.addSeparator(builder);
        StringRepresentationUtil.addField(builder, "Model name", modelName);
        StringRepresentationUtil.addSeparator(builder);
        StringRepresentationUtil.addField(builder, "SerialNo", serialNo);
        builder.append("]");
        return builder.toString();
    }


}

