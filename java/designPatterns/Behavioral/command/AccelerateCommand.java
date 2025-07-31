package designPatterns.Behavioral.command;

/**
 * AccelerateCommand - Concrete Command in the Command pattern.
 * This command encapsulates the request to accelerate a car.
 * It knows how to execute the accelerate operation on the car receiver.
 */
public class AccelerateCommand implements CarCommand {
    private Car car;
    private int previousSpeed; // For undo functionality
    
    /**
     * Constructor for AccelerateCommand
     * @param car The car to accelerate
     */
    public AccelerateCommand(Car car) {
        this.car = car;
    }
    
    @Override
    public void execute() {
        // Store the previous state for undo
        previousSpeed = car.getSpeed();
        
        // Execute the command
        car.accelerate();
    }
    
    @Override
    public void undo() {
        // If the speed increased, brake to return to previous speed
        if (car.getSpeed() > previousSpeed) {
            car.brake();
        }
    }
} 