package designPatterns.Behavioral.interpreter;

/**
 * Car - This class represents a car that can be controlled by interpreted commands.
 * It provides methods that can be called by the interpreter to control the car.
 */
public class Car {
    private String model;
    private int speed;
    private boolean isEngineRunning;
    private int fuelLevel;
    
    /**
     * Constructor for Car
     * @param model The car model
     */
    public Car(String model) {
        this.model = model;
        this.speed = 0;
        this.isEngineRunning = false;
        this.fuelLevel = 100;
    }
    
    /**
     * Starts the engine
     */
    public void startEngine() {
        if (!isEngineRunning && fuelLevel > 0) {
            isEngineRunning = true;
            System.out.println("[" + model + "] Engine started");
        } else if (fuelLevel <= 0) {
            System.out.println("[" + model + "] Cannot start engine - no fuel");
        } else {
            System.out.println("[" + model + "] Engine is already running");
        }
    }
    
    /**
     * Stops the engine
     */
    public void stopEngine() {
        if (isEngineRunning) {
            isEngineRunning = false;
            speed = 0;
            System.out.println("[" + model + "] Engine stopped");
        } else {
            System.out.println("[" + model + "] Engine is already stopped");
        }
    }
    
    /**
     * Sets the speed to a specific value
     * @param targetSpeed The target speed
     */
    public void setSpeed(int targetSpeed) {
        if (isEngineRunning) {
            this.speed = Math.max(0, targetSpeed);
            System.out.println("[" + model + "] Speed set to " + this.speed + " km/h");
        } else {
            System.out.println("[" + model + "] Cannot set speed - engine not running");
        }
    }
    
    /**
     * Accelerates the car
     * @param amount The amount to accelerate
     */
    public void accelerate(int amount) {
        if (isEngineRunning) {
            speed += amount;
            fuelLevel = Math.max(0, fuelLevel - amount / 10);
            System.out.println("[" + model + "] Accelerated by " + amount + " km/h. Current speed: " + speed + " km/h");
        } else {
            System.out.println("[" + model + "] Cannot accelerate - engine not running");
        }
    }
    
    /**
     * Brakes the car
     * @param amount The amount to brake
     */
    public void brake(int amount) {
        speed = Math.max(0, speed - amount);
        System.out.println("[" + model + "] Braked by " + amount + " km/h. Current speed: " + speed + " km/h");
    }
    
    /**
     * Refuels the car
     * @param amount The amount of fuel to add
     */
    public void refuel(int amount) {
        fuelLevel = Math.min(100, fuelLevel + amount);
        System.out.println("[" + model + "] Refueled by " + amount + "%. Current fuel level: " + fuelLevel + "%");
    }
    
    /**
     * Gets the current speed
     * @return The current speed
     */
    public int getSpeed() {
        return speed;
    }
    
    /**
     * Gets the fuel level
     * @return The fuel level
     */
    public int getFuelLevel() {
        return fuelLevel;
    }
    
    /**
     * Checks if the engine is running
     * @return true if engine is running, false otherwise
     */
    public boolean isEngineRunning() {
        return isEngineRunning;
    }
    
    /**
     * Gets the car model
     * @return The car model
     */
    public String getModel() {
        return model;
    }
    
    /**
     * Gets the current status of the car
     */
    public void getStatus() {
        System.out.println("=== " + model + " Status ===");
        System.out.println("Speed: " + speed + " km/h");
        System.out.println("Fuel Level: " + fuelLevel + "%");
        System.out.println("Engine: " + (isEngineRunning ? "Running" : "Stopped"));
    }
} 