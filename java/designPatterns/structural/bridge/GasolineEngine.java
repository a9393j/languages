package designPatterns.structural.bridge;

/**
 * GasolineEngine - Concrete implementation of the Engine interface.
 * This represents a gasoline-powered engine with specific characteristics.
 */
public class GasolineEngine implements Engine {
    private final int power;
    
    /**
     * Constructor for GasolineEngine
     * @param power Engine power in horsepower
     */
    public GasolineEngine(int power) {
        this.power = power;
    }
    
    @Override
    public void start() {
        System.out.println("Starting gasoline engine with " + power + " HP");
    }
    
    @Override
    public void stop() {
        System.out.println("Stopping gasoline engine");
    }
    
    @Override
    public String getEngineType() {
        return "Gasoline Engine";
    }
    
    @Override
    public int getPower() {
        return power;
    }
} 