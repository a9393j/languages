package designPatterns.Behavioral.memento;

/**
 * Main class demonstrating the Memento pattern.
 * This example shows how the Memento pattern allows us to:
 * - Save and restore object state
 * - Implement undo/redo functionality
 * - Provide checkpoint and rollback capabilities
 * - Maintain object state history
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("=== Memento Pattern Demo ===\n");
        
        // Create a car and its history manager
        Car car = new Car("Tesla Model S");
        CarHistory history = new CarHistory(car);
        
        System.out.println("1. Initial car status:");
        car.getStatus();
        
        System.out.println("\n2. Starting the car and saving state:");
        car.startEngine();
        history.saveState();
        
        System.out.println("\n3. Accelerating and saving states:");
        car.accelerate();
        history.saveState();
        
        car.accelerate();
        history.saveState();
        
        car.shiftUp();
        history.saveState();
        
        car.accelerate();
        history.saveState();
        
        System.out.println("\n4. Current car status:");
        car.getStatus();
        
        System.out.println("\n5. Demonstrating undo functionality:");
        System.out.println("=== Undo Sequence ===");
        
        history.undo(); // Undo last acceleration
        car.getStatus();
        
        history.undo(); // Undo gear shift
        car.getStatus();
        
        history.undo(); // Undo acceleration
        car.getStatus();
        
        System.out.println("\n6. Demonstrating redo functionality:");
        System.out.println("=== Redo Sequence ===");
        
        history.redo(); // Redo acceleration
        car.getStatus();
        
        history.redo(); // Redo gear shift
        car.getStatus();
        
        System.out.println("\n7. Performing more actions:");
        car.brake();
        history.saveState();
        
        car.refuel(20);
        history.saveState();
        
        System.out.println("\n8. Current car status:");
        car.getStatus();
        
        System.out.println("\n9. Multiple undos:");
        System.out.println("=== Multiple Undo ===");
        
        history.undo(); // Undo refuel
        car.getStatus();
        
        history.undo(); // Undo brake
        car.getStatus();
        
        history.undo(); // Undo redo
        car.getStatus();
        
        System.out.println("\n10. History information:");
        history.getHistoryInfo();
        
        System.out.println("\n11. Demonstrating state encapsulation:");
        System.out.println("=== State Encapsulation ===");
        System.out.println("The CarHistory (Caretaker) manages mementos but cannot access their internal state.");
        System.out.println("Only the Car (Originator) can create and restore mementos.");
        System.out.println("This ensures that the car's internal state is properly encapsulated.");
        
        System.out.println("\n=== Memento Pattern Benefits ===");
        System.out.println("✓ State Preservation: Can save and restore object state");
        System.out.println("✓ Encapsulation: Internal state is not exposed");
        System.out.println("✓ Undo/Redo: Easy to implement undo and redo functionality");
        System.out.println("✓ Checkpointing: Can create checkpoints for rollback");
        System.out.println("✓ History Management: Can maintain a history of states");
        System.out.println("✓ Immutable Mementos: Mementos cannot be modified once created");
    }
} 