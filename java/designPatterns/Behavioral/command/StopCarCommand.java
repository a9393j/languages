package designPatterns.Behavioral.command;

/**
 * StopCarCommand - Concrete Command in the Command pattern.
 * This command encapsulates the request to stop a car.
 * It knows how to execute the stop operation on the car receiver.
 */
public class StopCarCommand implements CarCommand {
    private Car car;
    private boolean wasRunning; // For undo functionality
    private int previousSpeed; // For undo functionality
    
    /**
     * Constructor for StopCarCommand
     * @param car The car to stop
     */
    public StopCarCommand(Car car) {
        this.car = car;
    }
    
    @Override
    public void execute() {
        // Store the previous state for undo
        wasRunning = car.isRunning();
        previousSpeed = car.getSpeed();
        
        // Execute the command
        car.stop();
    }
    
    @Override
    public void undo() {
        // If the car was running before and now it's stopped, start it
        if (wasRunning && !car.isRunning()) {
            car.start();
            // Note: We can't easily restore the exact speed, so we'll just start the engine
        }
    }
} 