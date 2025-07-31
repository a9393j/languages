package designPatterns.Behavioral.mediator;

/**
 * Main class demonstrating the Mediator pattern.
 * This example shows how the Mediator pattern allows us to:
 * - Reduce coupling between components
 * - Centralize complex communications
 * - Simplify object interactions
 * - Make the system easier to understand and maintain
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("=== Mediator Pattern Demo ===\n");
        
        // Create the mediator (car control system)
        CarControlSystem controlSystem = new CarControlSystem();
        
        // Create car components
        Engine engine = new Engine("V6 Engine");
        Transmission transmission = new Transmission("6-Speed Automatic");
        Brakes brakes = new Brakes("Disc Brakes");
        
        // Register components with the mediator
        System.out.println("1. Registering components with the control system:");
        controlSystem.registerComponent(engine);
        controlSystem.registerComponent(transmission);
        controlSystem.registerComponent(brakes);
        
        System.out.println("\n2. Initial system status:");
        System.out.println(controlSystem.getSystemStatus());
        
        System.out.println("3. Starting the car (simulating driver actions):");
        System.out.println("=== Starting Sequence ===");
        engine.sendMessage("start engine");
        
        System.out.println("\n4. Accelerating the car:");
        System.out.println("=== Acceleration Sequence ===");
        engine.sendMessage("accelerate engine");
        engine.sendMessage("accelerate engine");
        
        System.out.println("\n5. Applying brakes:");
        System.out.println("=== Braking Sequence ===");
        brakes.sendMessage("apply brakes");
        brakes.sendMessage("hard brake");
        
        System.out.println("\n6. Emergency stop:");
        System.out.println("=== Emergency Stop Sequence ===");
        brakes.sendMessage("emergency stop");
        
        System.out.println("\n7. Final system status:");
        System.out.println(controlSystem.getSystemStatus());
        
        System.out.println("8. Demonstrating direct component communication:");
        System.out.println("=== Direct Communication Example ===");
        System.out.println("Components don't communicate directly - all messages go through the mediator:");
        
        // Show how components communicate through the mediator
        System.out.println("Engine sends message to transmission:");
        controlSystem.sendMessageToType(engine, "Transmission", "request gear change");
        
        System.out.println("\nTransmission sends message to engine:");
        controlSystem.sendMessageToType(transmission, "Engine", "request RPM adjustment");
        
        System.out.println("\n=== Mediator Pattern Benefits ===");
        System.out.println("✓ Loose coupling: Components don't know about each other");
        System.out.println("✓ Centralized control: All communication goes through the mediator");
        System.out.println("✓ Simplified communication: Complex interactions are managed centrally");
        System.out.println("✓ Easier maintenance: Changes to communication logic are centralized");
        System.out.println("✓ System coordination: Mediator can implement complex coordination logic");
        System.out.println("✓ Reusability: Components can be reused in different contexts");
        
        System.out.println("\n=== Communication Flow ===");
        System.out.println("Component A → Mediator → Component B");
        System.out.println("Component A → Mediator → Component C");
        System.out.println("Component B → Mediator → Component A");
        System.out.println("(No direct communication between components)");
    }
} 