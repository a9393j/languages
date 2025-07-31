package designPatterns.Behavioral.strategy;

/**
 * DrivingStrategy - This is the Strategy interface in the Strategy pattern.
 * It defines an interface common to all supported driving algorithms.
 * Different driving strategies can be implemented and used interchangeably.
 */
public interface DrivingStrategy {
    /**
     * Executes the driving strategy
     * @param car The car to drive
     */
    void drive(Car car);
    
    /**
     * Gets the strategy name
     * @return The strategy name
     */
    String getStrategyName();
    
    /**
     * Gets the fuel efficiency of this strategy
     * @return Fuel efficiency rating (1-10, where 10 is most efficient)
     */
    int getFuelEfficiency();
    
    /**
     * Gets the performance rating of this strategy
     * @return Performance rating (1-10, where 10 is highest performance)
     */
    int getPerformanceRating();
} 