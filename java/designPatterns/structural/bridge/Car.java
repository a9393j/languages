package designPatterns.structural.bridge;

/**
 * Car - This is the Abstraction part of the Bridge pattern.
 * It defines the interface for different types of cars and holds a reference to an Engine.
 * The Bridge pattern allows us to separate car types from engine types, avoiding
 * the need to create separate classes for each combination.
 */
public abstract class Car {
    // This is the "bridge" - the reference to the implementation
    protected Engine engine;
    
    /**
     * Constructor for Car
     * @param engine The engine implementation to use
     */
    public Car(Engine engine) {
        this.engine = engine;
    }
    
    /**
     * Starts the car by starting its engine
     */
    public void start() {
        System.out.println("Starting " + getCarType() + " car");
        engine.start();
    }
    
    /**
     * Stops the car by stopping its engine
     */
    public void stop() {
        System.out.println("Stopping " + getCarType() + " car");
        engine.stop();
    }
    
    /**
     * Drives the car
     */
    public void drive() {
        System.out.println("Driving " + getCarType() + " car with " + engine.getEngineType());
    }
    
    /**
     * Gets information about the car
     */
    public void getInfo() {
        System.out.println("Car Type: " + getCarType());
        System.out.println("Engine: " + engine.getEngineType());
        System.out.println("Power: " + engine.getPower() + " HP");
    }
    
    /**
     * Abstract method to get the car type
     * @return String representing the car type
     */
    protected abstract String getCarType();
} 