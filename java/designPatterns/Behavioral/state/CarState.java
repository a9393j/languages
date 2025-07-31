package designPatterns.Behavioral.state;

/**
 * CarState - This is the State interface in the State pattern.
 * It defines an interface for encapsulating the behavior associated
 * with a particular state of the Car context.
 */
public interface CarState {
    /**
     * Starts the car engine
     * @param car The car context
     */
    void start(Car car);
    
    /**
     * Stops the car engine
     * @param car The car context
     */
    void stop(Car car);
    
    /**
     * Accelerates the car
     * @param car The car context
     */
    void accelerate(Car car);
    
    /**
     * Brakes the car
     * @param car The car context
     */
    void brake(Car car);
    
    /**
     * Gets the current state description
     * @return String describing the current state
     */
    String getStateDescription();
} 