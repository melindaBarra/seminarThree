/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package se.kth.iv1350.seminarthree.util;

/**
 * Utility class that provides helper methods for building string representations in DTO classes.
 * <p>
 * The methods in this class support the implementation of {@code toString()} methods.
 * </p>
 */
public class StringRepresentationUtil {

    /**
     * Prevents creating an instance of {@code StringRepresentationUtil}. 
     */
    private StringRepresentationUtil() {
    }

    /**
     * Appends a field and its value to a provided StringBuilder.
     * Uses the format name:value. Null values are written as "Not specified yet".
     *
     * @param builder the StringBuilder to append to.
     * @param name the field name.
     * @param value the field value.
     */
    public static void addField(StringBuilder builder, String name, Object value) {
        builder.append(name);
        builder.append(":");
        builder.append(value == null ? "Not specified yet" : value.toString());
    }

    /**
     * Appends a standard separator (", ") between fields in a string representation.
     *
     * @param builder the StringBuilder to append to.
     */
    public static void addSeparator(StringBuilder builder) {
        builder.append(", ");
    }
}
