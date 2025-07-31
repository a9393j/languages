package designPatterns.Behavioral.command;

/**
 * StartCarCommand - Concrete Command in the Command pattern.
 * This command encapsulates the request to start a car.
 * It knows how to execute the start operation on the car receiver.
 */
public class StartCarCommand implements CarCommand {
    private Car car;
    private boolean wasRunning; // For undo functionality
    
    /**
     * Constructor for StartCarCommand
     * @param car The car to start
     */
    public StartCarCommand(Car car) {
        this.car = car;
    }
    
    @Override
    public void execute() {
        // Store the previous state for undo
        wasRunning = car.isRunning();
        
        // Execute the command
        car.start();
    }
    
    @Override
    public void undo() {
        // If the car wasn't running before and now it is, stop it
        if (!wasRunning && car.isRunning()) {
            car.stop();
        }
    }
} 