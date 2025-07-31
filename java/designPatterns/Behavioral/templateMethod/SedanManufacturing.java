package designPatterns.Behavioral.templateMethod;

/**
 * SedanManufacturing - Concrete Class in the Template Method pattern.
 * This implements the car manufacturing process specifically for sedans.
 * It provides concrete implementations for the abstract methods defined
 * in the CarManufacturingProcess abstract class.
 */
public class SedanManufacturing extends CarManufacturingProcess {
    
    @Override
    protected void assembleBody() {
        System.out.println("3. Assembling sedan body");
        System.out.println("   - Welding sedan frame structure");
        System.out.println("   - Installing sedan-specific body panels");
        System.out.println("   - Adding sedan roof and trunk");
        System.out.println("   - Installing sedan doors (4-door configuration)");
        System.out.println("   - Adding sedan-specific aerodynamic features");
    }
    
    @Override
    protected void installInterior() {
        System.out.println("5. Installing sedan interior");
        System.out.println("   - Installing sedan seats (5-passenger capacity)");
        System.out.println("   - Adding sedan dashboard and console");
        System.out.println("   - Installing sedan-specific storage compartments");
        System.out.println("   - Adding sedan trunk lining and organization");
        System.out.println("   - Installing sedan-specific climate control");
    }
    
    @Override
    protected String getCarType() {
        return "Sedan";
    }
    
    /**
     * Override the hook method to add sedan-specific final assembly steps
     */
    @Override
    protected void finalAssembly() {
        super.finalAssembly(); // Call the default implementation
        System.out.println("   - Adding sedan-specific badges and trim");
        System.out.println("   - Installing sedan-specific wheel covers");
        System.out.println("   - Adding sedan-specific paint protection");
    }
} 