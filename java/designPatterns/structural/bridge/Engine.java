package designPatterns.structural.bridge;

/**
 * Engine interface - This is the Implementation part of the Bridge pattern.
 * It defines the interface for different types of engines that can be used in cars.
 * This allows the car abstraction to work with any engine implementation.
 */
public interface Engine {
    /**
     * Starts the engine
     */
    void start();
    
    /**
     * Stops the engine
     */
    void stop();
    
    /**
     * Gets the engine type description
     * @return String describing the engine type
     */
    String getEngineType();
    
    /**
     * Gets the engine power in horsepower
     * @return int representing engine power
     */
    int getPower();
} 