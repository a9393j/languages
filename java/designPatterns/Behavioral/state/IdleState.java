package designPatterns.Behavioral.state;

/**
 * IdleState - Concrete State in the State pattern.
 * This represents the state when the car is idle (engine off, not moving).
 * In this state, the car can be started but cannot accelerate or brake.
 */
public class IdleState implements CarState {
    
    @Override
    public void start(Car car) {
        System.out.println("Starting the car engine...");
        car.setSpeed(0);
        car.setState(new RunningState());
        System.out.println("Car is now running and ready to drive.");
    }
    
    @Override
    public void stop(Car car) {
        System.out.println("Car is already stopped.");
    }
    
    @Override
    public void accelerate(Car car) {
        System.out.println("Cannot accelerate - car engine is not running.");
    }
    
    @Override
    public void brake(Car car) {
        System.out.println("Cannot brake - car is not moving.");
    }
    
    @Override
    public String getStateDescription() {
        return "Idle (Engine Off)";
    }
} 