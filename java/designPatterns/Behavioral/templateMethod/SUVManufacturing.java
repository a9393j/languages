package designPatterns.Behavioral.templateMethod;

/**
 * SUVManufacturing - Concrete Class in the Template Method pattern.
 * This implements the car manufacturing process specifically for SUVs.
 * It provides concrete implementations for the abstract methods defined
 * in the CarManufacturingProcess abstract class.
 */
public class SUVManufacturing extends CarManufacturingProcess {
    
    @Override
    protected void assembleBody() {
        System.out.println("3. Assembling SUV body");
        System.out.println("   - Welding SUV frame structure (higher ground clearance)");
        System.out.println("   - Installing SUV-specific body panels");
        System.out.println("   - Adding SUV roof and cargo area");
        System.out.println("   - Installing SUV doors (5-door configuration)");
        System.out.println("   - Adding SUV-specific off-road features");
        System.out.println("   - Installing SUV-specific roof rails");
    }
    
    @Override
    protected void installInterior() {
        System.out.println("5. Installing SUV interior");
        System.out.println("   - Installing SUV seats (7-passenger capacity)");
        System.out.println("   - Adding SUV dashboard and console");
        System.out.println("   - Installing SUV-specific cargo management system");
        System.out.println("   - Adding SUV third-row seating");
        System.out.println("   - Installing SUV-specific climate control zones");
        System.out.println("   - Adding SUV-specific storage solutions");
    }
    
    @Override
    protected String getCarType() {
        return "SUV";
    }
    
    /**
     * Override the hook method to add SUV-specific final assembly steps
     */
    @Override
    protected void finalAssembly() {
        super.finalAssembly(); // Call the default implementation
        System.out.println("   - Adding SUV-specific badges and trim");
        System.out.println("   - Installing SUV-specific all-terrain tires");
        System.out.println("   - Adding SUV-specific skid plates");
        System.out.println("   - Installing SUV-specific tow hitch");
    }
} 