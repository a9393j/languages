package designPatterns.Behavioral.memento;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Car - This is the Originator in the Memento pattern.
 * It can save its current state to a memento and restore its state from a memento.
 * The car's internal state is encapsulated and can be preserved and restored.
 */
public class Car {
    private String model;
    private int speed;
    private int fuelLevel;
    private boolean isEngineRunning;
    private int gear;
    
    /**
     * Constructor for Car
     * @param model The car model
     */
    public Car(String model) {
        this.model = model;
        this.speed = 0;
        this.fuelLevel = 100;
        this.isEngineRunning = false;
        this.gear = 0; // Neutral
    }
    
    /**
     * Creates a memento with the current state
     * @return A CarMemento containing the current state
     */
    public CarMemento saveState() {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        return new CarMemento(model, speed, fuelLevel, isEngineRunning, gear, timestamp);
    }
    
    /**
     * Restores the car's state from a memento
     * @param memento The memento to restore from
     */
    public void restoreState(CarMemento memento) {
        this.model = memento.getModel();
        this.speed = memento.getSpeed();
        this.fuelLevel = memento.getFuelLevel();
        this.isEngineRunning = memento.isEngineRunning();
        this.gear = memento.getGear();
        
        System.out.println("[" + model + "] State restored from " + memento.getTimestamp());
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
            gear = 0;
            System.out.println("[" + model + "] Engine stopped");
        } else {
            System.out.println("[" + model + "] Engine is already stopped");
        }
    }
    
    /**
     * Accelerates the car
     */
    public void accelerate() {
        if (isEngineRunning && fuelLevel > 0) {
            speed += 10;
            fuelLevel -= 1;
            if (speed > 0 && gear == 0) {
                gear = 1;
            }
            System.out.println("[" + model + "] Accelerated to " + speed + " km/h (Gear: " + gear + ")");
        } else if (!isEngineRunning) {
            System.out.println("[" + model + "] Cannot accelerate - engine not running");
        } else {
            System.out.println("[" + model + "] Cannot accelerate - no fuel");
        }
    }
    
    /**
     * Brakes the car
     */
    public void brake() {
        if (speed > 0) {
            speed = Math.max(0, speed - 10);
            if (speed == 0) {
                gear = 0;
            }
            System.out.println("[" + model + "] Braked to " + speed + " km/h (Gear: " + gear + ")");
        } else {
            System.out.println("[" + model + "] Car is already stopped");
        }
    }
    
    /**
     * Shifts to a higher gear
     */
    public void shiftUp() {
        if (isEngineRunning && gear < 6 && speed > 0) {
            gear++;
            System.out.println("[" + model + "] Shifted up to gear " + gear);
        } else if (!isEngineRunning) {
            System.out.println("[" + model + "] Cannot shift - engine not running");
        } else if (speed == 0) {
            System.out.println("[" + model + "] Cannot shift - car not moving");
        } else {
            System.out.println("[" + model + "] Already in highest gear");
        }
    }
    
    /**
     * Shifts to a lower gear
     */
    public void shiftDown() {
        if (isEngineRunning && gear > 0) {
            gear--;
            System.out.println("[" + model + "] Shifted down to gear " + gear);
        } else if (!isEngineRunning) {
            System.out.println("[" + model + "] Cannot shift - engine not running");
        } else {
            System.out.println("[" + model + "] Already in neutral");
        }
    }
    
    /**
     * Refuels the car
     * @param amount The amount of fuel to add
     */
    public void refuel(int amount) {
        fuelLevel = Math.min(100, fuelLevel + amount);
        System.out.println("[" + model + "] Refueled. Fuel level: " + fuelLevel + "%");
    }
    
    /**
     * Gets the current status of the car
     */
    public void getStatus() {
        System.out.println("=== " + model + " Status ===");
        System.out.println("Speed: " + speed + " km/h");
        System.out.println("Fuel Level: " + fuelLevel + "%");
        System.out.println("Engine: " + (isEngineRunning ? "Running" : "Stopped"));
        System.out.println("Gear: " + (gear == 0 ? "Neutral" : gear));
    }
    
    // Getters for current state
    public String getModel() { return model; }
    public int getSpeed() { return speed; }
    public int getFuelLevel() { return fuelLevel; }
    public boolean isEngineRunning() { return isEngineRunning; }
    public int getGear() { return gear; }
} 