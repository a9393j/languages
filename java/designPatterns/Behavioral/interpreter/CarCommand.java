package designPatterns.Behavioral.interpreter;

/**
 * CarCommand - Terminal Expression in the Interpreter pattern.
 * This represents a simple car command like "start", "stop", "accelerate", etc.
 * It is a terminal expression that directly controls the car.
 */
public class CarCommand implements CarExpression {
    private String command;
    private Integer value; // Optional value for commands like "accelerate 50"
    
    /**
     * Constructor for CarCommand
     * @param command The command to execute
     */
    public CarCommand(String command) {
        this.command = command.toLowerCase();
        this.value = null;
    }
    
    /**
     * Constructor for CarCommand with a value
     * @param command The command to execute
     * @param value The value for the command
     */
    public CarCommand(String command, int value) {
        this.command = command.toLowerCase();
        this.value = value;
    }
    
    @Override
    public void interpret(CarContext context) {
        Car car = context.getCar();
        
        switch (command) {
            case "start":
                car.startEngine();
                break;
            case "stop":
                car.stopEngine();
                break;
            case "accelerate":
                if (value != null) {
                    car.accelerate(value);
                } else {
                    car.accelerate(10); // Default acceleration
                }
                break;
            case "brake":
                if (value != null) {
                    car.brake(value);
                } else {
                    car.brake(10); // Default braking
                }
                break;
            case "speed":
                if (value != null) {
                    car.setSpeed(value);
                }
                break;
            case "refuel":
                if (value != null) {
                    car.refuel(value);
                } else {
                    car.refuel(20); // Default refuel amount
                }
                break;
            case "status":
                car.getStatus();
                break;
            default:
                System.out.println("[" + car.getModel() + "] Unknown command: " + command);
        }
    }
    
    @Override
    public String getExpressionType() {
        return "Command";
    }
    
    /**
     * Gets the command
     * @return The command
     */
    public String getCommand() {
        return command;
    }
    
    /**
     * Gets the value
     * @return The value, or null if not set
     */
    public Integer getValue() {
        return value;
    }
} 