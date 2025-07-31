package designPatterns.Behavioral.command;

/**
 * Main class demonstrating the Command pattern.
 * This example shows how the Command pattern allows us to:
 * - Encapsulate requests as objects
 * - Parameterize clients with different requests
 * - Queue and log requests
 * - Support undo operations
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("=== Command Pattern Demo ===\n");
        
        // Create the receiver (the car)
        Car car = new Car("Tesla Model S");
        
        // Create concrete commands
        CarCommand startCommand = new StartCarCommand(car);
        CarCommand stopCommand = new StopCarCommand(car);
        CarCommand accelerateCommand = new AccelerateCommand(car);
        CarCommand brakeCommand = new BrakeCommand(car);
        
        // Create the invoker (remote control)
        RemoteControl remoteControl = new RemoteControl();
        
        // Set up the remote control with commands
        remoteControl.setStartCommand(startCommand);
        remoteControl.setStopCommand(stopCommand);
        remoteControl.setAccelerateCommand(accelerateCommand);
        remoteControl.setBrakeCommand(brakeCommand);
        
        // Demonstrate the Command pattern
        System.out.println("1. Initial car status:");
        car.getStatus();
        
        System.out.println("\n2. Using remote control to operate the car:");
        remoteControl.pressStartButton();
        car.getStatus();
        
        remoteControl.pressAccelerateButton();
        remoteControl.pressAccelerateButton();
        remoteControl.pressAccelerateButton();
        car.getStatus();
        
        remoteControl.pressBrakeButton();
        car.getStatus();
        
        System.out.println("\n3. Demonstrating undo functionality:");
        System.out.println("Commands in history: " + remoteControl.getCommandHistorySize());
        
        remoteControl.undoLastCommand(); // Undo brake
        car.getStatus();
        
        remoteControl.undoLastCommand(); // Undo accelerate
        car.getStatus();
        
        remoteControl.undoLastCommand(); // Undo accelerate
        car.getStatus();
        
        remoteControl.undoLastCommand(); // Undo accelerate
        car.getStatus();
        
        remoteControl.undoLastCommand(); // Undo start
        car.getStatus();
        
        System.out.println("\n4. Final demonstration - driving sequence:");
        remoteControl.pressStartButton();
        remoteControl.pressAccelerateButton();
        remoteControl.pressAccelerateButton();
        remoteControl.pressStopButton();
        
        System.out.println("\n=== Command Pattern Benefits ===");
        System.out.println("✓ Commands are encapsulated as objects");
        System.out.println("✓ Easy to add new commands without changing existing code");
        System.out.println("✓ Commands can be queued, logged, and undone");
        System.out.println("✓ Remote control doesn't know about car implementation");
        System.out.println("✓ Commands can be composed into more complex operations");
    }
} 