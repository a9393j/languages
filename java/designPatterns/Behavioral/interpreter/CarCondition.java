package designPatterns.Behavioral.interpreter;

/**
 * CarCondition - Non-Terminal Expression in the Interpreter pattern.
 * This represents a conditional expression like "if speed > 30 then brake".
 * It evaluates a condition and executes an action if the condition is true.
 */
public class CarCondition implements CarExpression {
    private String property;
    private String operator;
    private int value;
    private CarExpression action;
    
    /**
     * Constructor for CarCondition
     * @param property The car property to check (speed, fuel, etc.)
     * @param operator The comparison operator (>, <, ==, etc.)
     * @param value The value to compare against
     * @param action The action to execute if condition is true
     */
    public CarCondition(String property, String operator, int value, CarExpression action) {
        this.property = property.toLowerCase();
        this.operator = operator;
        this.value = value;
        this.action = action;
    }
    
    @Override
    public void interpret(CarContext context) {
        Car car = context.getCar();
        boolean conditionMet = false;
        
        // Get the current value of the property
        int currentValue = getPropertyValue(car, property);
        
        // Evaluate the condition
        switch (operator) {
            case ">":
                conditionMet = currentValue > value;
                break;
            case "<":
                conditionMet = currentValue < value;
                break;
            case ">=":
                conditionMet = currentValue >= value;
                break;
            case "<=":
                conditionMet = currentValue <= value;
                break;
            case "==":
                conditionMet = currentValue == value;
                break;
            case "!=":
                conditionMet = currentValue != value;
                break;
            default:
                System.out.println("[" + car.getModel() + "] Unknown operator: " + operator);
                return;
        }
        
        // Execute action if condition is met
        if (conditionMet) {
            System.out.println("[" + car.getModel() + "] Condition met: " + property + " " + operator + " " + value);
            action.interpret(context);
        } else {
            System.out.println("[" + car.getModel() + "] Condition not met: " + property + " " + operator + " " + value + " (current: " + currentValue + ")");
        }
    }
    
    @Override
    public String getExpressionType() {
        return "Condition";
    }
    
    /**
     * Gets the value of a car property
     * @param car The car
     * @param property The property name
     * @return The property value
     */
    private int getPropertyValue(Car car, String property) {
        switch (property) {
            case "speed":
                return car.getSpeed();
            case "fuel":
            case "fuellevel":
                return car.getFuelLevel();
            case "engine":
                return car.isEngineRunning() ? 1 : 0;
            default:
                System.out.println("[" + car.getModel() + "] Unknown property: " + property);
                return 0;
        }
    }
    
    /**
     * Gets the property
     * @return The property
     */
    public String getProperty() {
        return property;
    }
    
    /**
     * Gets the operator
     * @return The operator
     */
    public String getOperator() {
        return operator;
    }
    
    /**
     * Gets the value
     * @return The value
     */
    public int getValue() {
        return value;
    }
    
    /**
     * Gets the action
     * @return The action
     */
    public CarExpression getAction() {
        return action;
    }
} 