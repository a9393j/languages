package designPatterns.Behavioral.command;

/**
 * Car - This is the Receiver in the Command pattern.
 * It knows how to perform the operations associated with carrying out a request.
 * The commands will call methods on this class to execute their operations.
 */
public class Car {
    private String model;
    private boolean isRunning;
    private int speed;
    
    /**
     * Constructor for Car
     * @param model The car model
     */
    public Car(String model) {
        this.model = model;
        this.isRunning = false;
        this.speed = 0;
    }
    
    /**
     * Starts the car engine
     */
    public void start() {
        if (!isRunning) {
            isRunning = true;
            System.out.println(model + " engine started");
        } else {
            System.out.println(model + " engine is already running");
        }
    }
    
    /**
     * Stops the car engine
     */
    public void stop() {
        if (isRunning) {
            isRunning = false;
            speed = 0;
            System.out.println(model + " engine stopped");
        } else {
            System.out.println(model + " engine is already stopped");
        }
    }
    
    /**
     * Accelerates the car
     */
    public void accelerate() {
        if (isRunning) {
            speed += 10;
            System.out.println(model + " accelerated to " + speed + " km/h");
        } else {
            System.out.println("Cannot accelerate - " + model + " engine is not running");
        }
    }
    
    /**
     * Brakes the car
     */
    public void brake() {
        if (speed > 0) {
            speed = Math.max(0, speed - 10);
            System.out.println(model + " braked to " + speed + " km/h");
        } else {
            System.out.println(model + " is already stopped");
        }
    }
    
    /**
     * Gets the current status of the car
     */
    public void getStatus() {
        System.out.println("=== " + model + " Status ===");
        System.out.println("Engine: " + (isRunning ? "Running" : "Stopped"));
        System.out.println("Speed: " + speed + " km/h");
    }
    
    /**
     * Gets the car model
     * @return The car model
     */
    public String getModel() {
        return model;
    }
    
    /**
     * Checks if the car is running
     * @return true if running, false otherwise
     */
    public boolean isRunning() {
        return isRunning;
    }
    
    /**
     * Gets the current speed
     * @return Current speed in km/h
     */
    public int getSpeed() {
        return speed;
    }
} 