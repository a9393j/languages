package designPatterns.Behavioral.observer;

/**
 * Car - Represents a car in the dealer's inventory.
 * This is the data that observers are interested in.
 */
public class Car {
    private String make;
    private String model;
    private int year;
    private double price;
    private String color;
    private boolean isAvailable;
    
    /**
     * Constructor for Car
     * @param make The car make
     * @param model The car model
     * @param year The car year
     * @param price The car price
     * @param color The car color
     */
    public Car(String make, String model, int year, double price, String color) {
        this.make = make;
        this.model = model;
        this.year = year;
        this.price = price;
        this.color = color;
        this.isAvailable = true;
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
     * Gets the car price
     * @return The car price
     */
    public double getPrice() {
        return price;
    }
    
    /**
     * Sets the car price
     * @param price The new price
     */
    public void setPrice(double price) {
        this.price = price;
    }
    
    /**
     * Gets the car color
     * @return The car color
     */
    public String getColor() {
        return color;
    }
    
    /**
     * Checks if the car is available
     * @return true if available, false otherwise
     */
    public boolean isAvailable() {
        return isAvailable;
    }
    
    /**
     * Sets the availability of the car
     * @param available The availability status
     */
    public void setAvailable(boolean available) {
        this.isAvailable = available;
    }
    
    @Override
    public String toString() {
        return year + " " + color + " " + make + " " + model + " - $" + price + 
               (isAvailable ? " (Available)" : " (Sold)");
    }
} 