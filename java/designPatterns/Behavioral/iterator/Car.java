package designPatterns.Behavioral.iterator;

/**
 * Car - A simple car class that will be stored in our collection.
 * This represents the elements that we want to iterate over.
 */
public class Car {
    private String make;
    private String model;
    private int year;
    private String color;
    
    /**
     * Constructor for Car
     * @param make The car make
     * @param model The car model
     * @param year The car year
     * @param color The car color
     */
    public Car(String make, String model, int year, String color) {
        this.make = make;
        this.model = model;
        this.year = year;
        this.color = color;
    }
    
    /**
     * Gets the car make
     * @return The car make
     */
    public String getMake() {
        return make;
    }
    
    /**
     * Gets the car model
     * @return The car model
     */
    public String getModel() {
        return model;
    }
    
    /**
     * Gets the car year
     * @return The car year
     */
    public int getYear() {
        return year;
    }
    
    /**
     * Gets the car color
     * @return The car color
     */
    public String getColor() {
        return color;
    }
    
    @Override
    public String toString() {
        return year + " " + color + " " + make + " " + model;
    }
} 