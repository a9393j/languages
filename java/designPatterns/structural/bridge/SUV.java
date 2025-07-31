package designPatterns.structural.bridge;

/**
 * SUV - Refined Abstraction in the Bridge pattern.
 * This represents a specific type of car (SUV) that can work with any engine implementation.
 * The Bridge pattern allows us to create an SUV with any type of engine without
 * creating separate classes like GasolineSUV, ElectricSUV, etc.
 */
public class SUV extends Car {
    
    /**
     * Constructor for SUV
     * @param engine The engine implementation to use
     */
    public SUV(Engine engine) {
        super(engine);
    }
    
    @Override
    protected String getCarType() {
        return "SUV";
    }
    
    /**
     * SUV-specific behavior
     */
    public void offRoadCapability() {
        System.out.println("SUV is ready for off-road adventure");
    }
    
    /**
     * SUV-specific behavior for cargo space
     */
    public void loadCargo() {
        System.out.println("Loading cargo into the spacious SUV");
    }
} 