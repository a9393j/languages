package designPatterns.Behavioral.state;

/**
 * Main class demonstrating the State pattern.
 * This example shows how the State pattern allows us to:
 * - Handle complex state-dependent behavior
 * - Avoid large conditional statements
 * - Make state transitions explicit and well-defined
 * - Encapsulate state-specific behavior in separate classes
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("=== State Pattern Demo ===\n");
        
        // Create a car
        Car car = new Car("Tesla Model S");
        
        System.out.println("1. Initial car status:");
        car.getStatus();
        
        System.out.println("\n2. Trying to accelerate while idle:");
        car.accelerate();
        
        System.out.println("\n3. Starting the car:");
        car.start();
        car.getStatus();
        
        System.out.println("\n4. Accelerating the car:");
        car.accelerate();
        car.accelerate();
        car.accelerate();
        car.getStatus();
        
        System.out.println("\n5. Braking the car:");
        car.brake();
        car.brake();
        car.getStatus();
        
        System.out.println("\n6. Braking to complete stop:");
        car.brake();
        car.getStatus();
        
        System.out.println("\n7. Trying to accelerate while stopped:");
        car.accelerate();
        
        System.out.println("\n8. Starting the car again:");
        car.start();
        car.getStatus();
        
        System.out.println("\n9. Stopping the car engine:");
        car.stop();
        car.getStatus();
        
        System.out.println("\n10. Trying to brake while stopped:");
        car.brake();
        
        System.out.println("\n=== State Pattern Benefits ===");
        System.out.println("✓ State-specific behavior is encapsulated in separate classes");
        System.out.println("✓ State transitions are explicit and well-defined");
        System.out.println("✓ No large conditional statements based on state");
        System.out.println("✓ Easy to add new states without changing existing code");
        System.out.println("✓ Each state class has a single responsibility");
        System.out.println("✓ Object behavior changes dynamically based on state");
    }
} 