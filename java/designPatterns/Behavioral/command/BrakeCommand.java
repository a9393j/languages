package designPatterns.Behavioral.command;

/**
 * BrakeCommand - Concrete Command in the Command pattern.
 * This command encapsulates the request to brake a car.
 * It knows how to execute the brake operation on the car receiver.
 */
public class BrakeCommand implements CarCommand {
    private Car car;
    private int previousSpeed; // For undo functionality
    
    /**
     * Constructor for BrakeCommand
     * @param car The car to brake
     */
    public BrakeCommand(Car car) {
        this.car = car;
    }
    
    @Override
    public void execute() {
        // Store the previous state for undo
        previousSpeed = car.getSpeed();
        
        // Execute the command
        car.brake();
    }
    
    @Override
    public void undo() {
        // If the speed decreased, accelerate to return to previous speed
        if (car.getSpeed() < previousSpeed) {
            car.accelerate();
        }
    }
} 