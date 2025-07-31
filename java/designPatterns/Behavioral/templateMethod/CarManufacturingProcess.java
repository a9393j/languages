package designPatterns.Behavioral.templateMethod;

/**
 * CarManufacturingProcess - This is the AbstractClass in the Template Method pattern.
 * It defines the skeleton of the car manufacturing algorithm, deferring some steps
 * to concrete subclasses. The template method ensures that all cars follow the same
 * manufacturing process while allowing customization of specific steps.
 */
public abstract class CarManufacturingProcess {
    
    /**
     * Template method that defines the car manufacturing algorithm.
     * This method cannot be overridden by subclasses.
     */
    public final void manufactureCar() {
        System.out.println("=== Starting Car Manufacturing Process ===");
        
        // Step 1: Design and Planning
        designCar();
        
        // Step 2: Material Preparation
        prepareMaterials();
        
        // Step 3: Body Assembly (varies by car type)
        assembleBody();
        
        // Step 4: Engine Installation
        installEngine();
        
        // Step 5: Interior Installation (varies by car type)
        installInterior();
        
        // Step 6: Electrical Systems
        installElectricalSystems();
        
        // Step 7: Quality Testing
        performQualityTesting();
        
        // Step 8: Final Assembly
        finalAssembly();
        
        System.out.println("=== Car Manufacturing Process Completed ===\n");
    }
    
    /**
     * Common step - Design and planning (same for all cars)
     */
    private void designCar() {
        System.out.println("1. Designing car specifications and blueprints");
        System.out.println("   - Creating technical drawings");
        System.out.println("   - Planning manufacturing steps");
        System.out.println("   - Setting quality standards");
    }
    
    /**
     * Common step - Material preparation (same for all cars)
     */
    private void prepareMaterials() {
        System.out.println("2. Preparing manufacturing materials");
        System.out.println("   - Ordering steel, aluminum, and other metals");
        System.out.println("   - Procuring electronic components");
        System.out.println("   - Gathering interior materials");
    }
    
    /**
     * Abstract step - Body assembly (varies by car type)
     * Must be implemented by concrete subclasses
     */
    protected abstract void assembleBody();
    
    /**
     * Common step - Engine installation (same for all cars)
     */
    private void installEngine() {
        System.out.println("4. Installing engine and powertrain");
        System.out.println("   - Mounting engine block");
        System.out.println("   - Connecting transmission");
        System.out.println("   - Installing exhaust system");
    }
    
    /**
     * Abstract step - Interior installation (varies by car type)
     * Must be implemented by concrete subclasses
     */
    protected abstract void installInterior();
    
    /**
     * Common step - Electrical systems (same for all cars)
     */
    private void installElectricalSystems() {
        System.out.println("6. Installing electrical systems");
        System.out.println("   - Wiring harness installation");
        System.out.println("   - Installing dashboard electronics");
        System.out.println("   - Setting up lighting systems");
    }
    
    /**
     * Common step - Quality testing (same for all cars)
     */
    private void performQualityTesting() {
        System.out.println("7. Performing quality assurance tests");
        System.out.println("   - Safety testing");
        System.out.println("   - Performance testing");
        System.out.println("   - Electrical system testing");
    }
    
    /**
     * Hook method - Final assembly (can be overridden by subclasses)
     * Default implementation provided
     */
    protected void finalAssembly() {
        System.out.println("8. Final assembly and finishing touches");
        System.out.println("   - Installing wheels and tires");
        System.out.println("   - Adding final trim pieces");
        System.out.println("   - Cleaning and detailing");
    }
    
    /**
     * Gets the car type being manufactured
     * @return The car type
     */
    protected abstract String getCarType();
} 