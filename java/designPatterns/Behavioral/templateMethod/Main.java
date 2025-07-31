package designPatterns.Behavioral.templateMethod;

/**
 * Main class demonstrating the Template Method pattern.
 * This example shows how the Template Method pattern allows us to:
 * - Define the overall structure of an algorithm
 * - Allow subclasses to override specific steps
 * - Avoid code duplication in similar algorithms
 * - Ensure consistent algorithm structure across subclasses
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("=== Template Method Pattern Demo ===\n");
        
        // Create different car manufacturing processes
        CarManufacturingProcess sedanProcess = new SedanManufacturing();
        CarManufacturingProcess suvProcess = new SUVManufacturing();
        
        System.out.println("1. Manufacturing a Sedan:");
        sedanProcess.manufactureCar();
        
        System.out.println("2. Manufacturing an SUV:");
        suvProcess.manufactureCar();
        
        System.out.println("3. Demonstrating algorithm structure consistency:");
        System.out.println("=== Algorithm Structure Analysis ===");
        System.out.println("Both manufacturing processes follow the same 8-step algorithm:");
        System.out.println("1. Design and Planning (Common)");
        System.out.println("2. Material Preparation (Common)");
        System.out.println("3. Body Assembly (Customized)");
        System.out.println("4. Engine Installation (Common)");
        System.out.println("5. Interior Installation (Customized)");
        System.out.println("6. Electrical Systems (Common)");
        System.out.println("7. Quality Testing (Common)");
        System.out.println("8. Final Assembly (Hook method)");
        
        System.out.println("\n4. Key differences between Sedan and SUV manufacturing:");
        System.out.println("=== Customization Points ===");
        System.out.println("Body Assembly:");
        System.out.println("  - Sedan: 4-door configuration, trunk, aerodynamic features");
        System.out.println("  - SUV: 5-door configuration, cargo area, off-road features");
        
        System.out.println("\nInterior Installation:");
        System.out.println("  - Sedan: 5-passenger capacity, trunk organization");
        System.out.println("  - SUV: 7-passenger capacity, cargo management, third-row seating");
        
        System.out.println("\nFinal Assembly:");
        System.out.println("  - Sedan: Sedan-specific badges, wheel covers");
        System.out.println("  - SUV: SUV-specific badges, all-terrain tires, tow hitch");
        
        System.out.println("\n=== Template Method Pattern Benefits ===");
        System.out.println("✓ Algorithm structure is defined once in the abstract class");
        System.out.println("✓ Common steps are shared across all car types");
        System.out.println("✓ Customization points allow for car-specific implementations");
        System.out.println("✓ Consistent manufacturing process across all car types");
        System.out.println("✓ Easy to add new car types without changing the algorithm structure");
        System.out.println("✓ Hook methods provide optional customization points");
    }
} 