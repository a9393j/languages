package designPatterns.Behavioral.visitor;

/**
 * Main class demonstrating the Visitor pattern.
 * This example shows how the Visitor pattern allows us to:
 * - Separate algorithms from the objects they operate on
 * - Add new operations without modifying existing classes
 * - Perform operations on collections of different object types
 * - Support double dispatch
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("=== Visitor Pattern Demo ===\n");
        
        // Create a car with various components
        Car car = new Car("Tesla Model S");
        
        // Add components with different conditions
        car.addComponent(new Engine("Electric Motor", "Dual Motor", 50000));
        car.addComponent(new Wheels("All-Season Tires", "Michelin Pilot Sport", 28));
        car.addComponent(new Brakes("Regenerative Brakes", "Electric", 8));
        
        System.out.println("Car: " + car.getModel());
        System.out.println("Components: " + car.getComponentCount());
        System.out.println();
        
        // Create visitors
        CarVisitor maintenanceVisitor = new MaintenanceVisitor();
        CarVisitor inspectionVisitor = new InspectionVisitor();
        
        // Perform maintenance on all components
        System.out.println("1. Performing maintenance on all components:");
        car.accept(maintenanceVisitor);
        
        // Perform inspection on all components
        System.out.println("2. Performing safety inspection on all components:");
        car.accept(inspectionVisitor);
        
        System.out.println("3. Demonstrating component-specific operations:");
        System.out.println("=== Component-Specific Operations ===");
        
        // Show how different visitors handle different components
        for (CarComponent component : car.getComponents()) {
            System.out.println("Component: " + component.getName() + " (" + component.getType() + ")");
            System.out.println("  Condition: " + component.getCondition() + "/10");
            
            // Each component accepts the visitor, and the visitor calls the appropriate method
            System.out.println("  Maintenance Visitor:");
            component.accept(maintenanceVisitor);
            
            System.out.println("  Inspection Visitor:");
            component.accept(inspectionVisitor);
            System.out.println();
        }
        
        System.out.println("4. Demonstrating double dispatch:");
        System.out.println("=== Double Dispatch Example ===");
        System.out.println("When component.accept(visitor) is called:");
        System.out.println("1. Component calls visitor.visit[ComponentType](this)");
        System.out.println("2. Visitor receives the specific component type");
        System.out.println("3. Visitor can perform component-specific operations");
        System.out.println("4. The operation depends on BOTH visitor type AND component type");
        
        System.out.println("\n=== Visitor Pattern Benefits ===");
        System.out.println("✓ Operations are separated from component classes");
        System.out.println("✓ New operations can be added without modifying components");
        System.out.println("✓ Different visitors can perform different operations");
        System.out.println("✓ Double dispatch allows component-specific operations");
        System.out.println("✓ Easy to add new component types");
        System.out.println("✓ Algorithms are encapsulated in visitor classes");
    }
} 