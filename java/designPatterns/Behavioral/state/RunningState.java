package designPatterns.Behavioral.state;

/**
 * RunningState - Concrete State in the State pattern.
 * This represents the state when the car is running (engine on, can move).
 * In this state, the car can accelerate, brake, or be stopped.
 */
public class RunningState implements CarState {
    
    @Override
    public void start(Car car) {
        System.out.println("Car engine is already running.");
    }
    
    @Override
    public void stop(Car car) {
        System.out.println("Stopping the car engine...");
        car.setSpeed(0);
        car.setState(new StoppedState());
        System.out.println("Car is now stopped.");
    }
    
    @Override
    public void accelerate(Car car) {
        int currentSpeed = car.getSpeed();
        int newSpeed = currentSpeed + 10;
        car.setSpeed(newSpeed);
        System.out.println("Accelerating... Speed: " + currentSpeed + " -> " + newSpeed + " km/h");
        
        // If speed reaches 0, transition to idle state
        if (newSpeed == 0) {
            car.setState(new IdleState());
            System.out.println("Car has come to a complete stop.");
        }
    }
    
    @Override
    public void brake(Car car) {
        int currentSpeed = car.getSpeed();
        int newSpeed = Math.max(0, currentSpeed - 10);
        car.setSpeed(newSpeed);
        System.out.println("Braking... Speed: " + currentSpeed + " -> " + newSpeed + " km/h");
        
        // If speed reaches 0, transition to idle state
        if (newSpeed == 0) {
            car.setState(new IdleState());
            System.out.println("Car has come to a complete stop.");
        }
    }
    
    @Override
    public String getStateDescription() {
        return "Running (Engine On)";
    }
} 