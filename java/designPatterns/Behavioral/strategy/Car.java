package designPatterns.Behavioral.strategy;

/**
 * Car - This is the Context in the Strategy pattern.
 * It maintains a reference to a strategy object and delegates
 * driving behavior to the current strategy.
 */
public class Car {
    private String model;
    private DrivingStrategy drivingStrategy;
    
    /**
     * Constructor for Car
     * @param model The car model
     */
    public Car(String model) {
        this.model = model;
        // Default to normal driving strategy
        this.drivingStrategy = new NormalDriving();
    }
    
    /**
     * Sets the driving strategy
     * @param strategy The new driving strategy
     */
    public void setDrivingStrategy(DrivingStrategy strategy) {
        this.drivingStrategy = strategy;
        System.out.println("Switched to " + strategy.getStrategyName() + " mode");
    }
    
    /**
     * Drives the car using the current strategy
     */
    public void drive() {
        drivingStrategy.drive(this);
    }
    
    /**
     * Gets information about the current driving strategy
     */
    public void getStrategyInfo() {
        System.out.println("=== " + model + " Driving Strategy Info ===");
        System.out.println("Current Strategy: " + drivingStrategy.getStrategyName());
        System.out.println("Fuel Efficiency: " + drivingStrategy.getFuelEfficiency() + "/10");
        System.out.println("Performance Rating: " + drivingStrategy.getPerformanceRating() + "/10");
    }
    
    /**
     * Gets the car model
     * @return The car model
     */
    public String getModel() {
        return model;
    }
    
    /**
     * Gets the current driving strategy
     * @return The current driving strategy
     */
    public DrivingStrategy getDrivingStrategy() {
        return drivingStrategy;
    }
} 