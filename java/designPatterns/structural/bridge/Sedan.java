package designPatterns.structural.bridge;

/**
 * Sedan - Refined Abstraction in the Bridge pattern.
 * This represents a specific type of car (Sedan) that can work with any engine implementation.
 * The Bridge pattern allows us to create a Sedan with any type of engine without
 * creating separate classes like GasolineSedan, ElectricSedan, etc.
 */
public class Sedan extends Car {
    
    /**
     * Constructor for Sedan
     * @param engine The engine implementation to use
     */
    public Sedan(Engine engine) {
        super(engine);
    }
    
    @Override
    protected String getCarType() {
        return "Sedan";
    }
    
    /**
     * Sedan-specific behavior
     */
    public void comfortableRide() {
        System.out.println("Enjoying a comfortable ride in the sedan");
    }
} 