package designPatterns.Behavioral.state;

/**
 * Car - This is the Context in the State pattern.
 * It maintains a reference to the current state and delegates
 * state-specific behavior to the current state object.
 */
public class Car {
    private String model;
    private CarState currentState;
    private int speed;
    
    /**
     * Constructor for Car
     * @param model The car model
     */
    public Car(String model) {
        this.model = model;
        this.speed = 0;
        // Start in idle state
        this.currentState = new IdleState();
    }
    
    /**
     * Starts the car engine
     */
    public void start() {
        currentState.start(this);
    }
    
    /**
     * Stops the car engine
     */
    public void stop() {
        currentState.stop(this);
    }
    
    /**
     * Accelerates the car
     */
    public void accelerate() {
        currentState.accelerate(this);
    }
    
    /**
     * Brakes the car
     */
    public void brake() {
        currentState.brake(this);
    }
    
    /**
     * Gets the current state description
     * @return String describing the current state
     */
    public String getCurrentState() {
        return currentState.getStateDescription();
    }
    
    /**
     * Sets the current state
     * @param state The new state
     */
    public void setState(CarState state) {
        this.currentState = state;
    }
    
    /**
     * Gets the current speed
     * @return Current speed in km/h
     */
    public int getSpeed() {
        return speed;
    }
    
    /**
     * Sets the current speed
     * @param speed The new speed
     */
    public void setSpeed(int speed) {
        this.speed = speed;
    }
    
    /**
     * Gets the car model
     * @return The car model
     */
    public String getModel() {
        return model;
    }
    
    /**
     * Gets the car status
     */
    public void getStatus() {
        System.out.println("=== " + model + " Status ===");
        System.out.println("State: " + getCurrentState());
        System.out.println("Speed: " + speed + " km/h");
    }
} 