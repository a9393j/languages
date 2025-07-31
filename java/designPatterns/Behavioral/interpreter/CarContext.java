package designPatterns.Behavioral.interpreter;

import java.util.HashMap;
import java.util.Map;

/**
 * CarContext - This is the Context in the Interpreter pattern.
 * It contains the car object and any variables that the interpreter needs.
 * The context is passed to all expressions during interpretation.
 */
public class CarContext {
    private Car car;
    private Map<String, Integer> variables;
    
    /**
     * Constructor for CarContext
     * @param car The car to control
     */
    public CarContext(Car car) {
        this.car = car;
        this.variables = new HashMap<>();
    }
    
    /**
     * Gets the car
     * @return The car
     */
    public Car getCar() {
        return car;
    }
    
    /**
     * Sets a variable
     * @param name The variable name
     * @param value The variable value
     */
    public void setVariable(String name, int value) {
        variables.put(name, value);
    }
    
    /**
     * Gets a variable
     * @param name The variable name
     * @return The variable value, or 0 if not found
     */
    public int getVariable(String name) {
        return variables.getOrDefault(name, 0);
    }
    
    /**
     * Checks if a variable exists
     * @param name The variable name
     * @return true if the variable exists, false otherwise
     */
    public boolean hasVariable(String name) {
        return variables.containsKey(name);
    }
    
    /**
     * Gets all variables
     * @return Map of all variables
     */
    public Map<String, Integer> getVariables() {
        return new HashMap<>(variables);
    }
    
    /**
     * Clears all variables
     */
    public void clearVariables() {
        variables.clear();
    }
} 