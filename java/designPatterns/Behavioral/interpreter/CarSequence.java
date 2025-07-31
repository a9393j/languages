package designPatterns.Behavioral.interpreter;

import java.util.ArrayList;
import java.util.List;

/**
 * CarSequence - Non-Terminal Expression in the Interpreter pattern.
 * This represents a sequence of car commands that should be executed in order.
 * It allows for complex command sequences like "start; accelerate 50; brake 20".
 */
public class CarSequence implements CarExpression {
    private List<CarExpression> expressions;
    
    /**
     * Constructor for CarSequence
     */
    public CarSequence() {
        this.expressions = new ArrayList<>();
    }
    
    /**
     * Adds an expression to the sequence
     * @param expression The expression to add
     */
    public void addExpression(CarExpression expression) {
        expressions.add(expression);
    }
    
    /**
     * Adds multiple expressions to the sequence
     * @param expressions The expressions to add
     */
    public void addExpressions(CarExpression... expressions) {
        for (CarExpression expression : expressions) {
            this.expressions.add(expression);
        }
    }
    
    @Override
    public void interpret(CarContext context) {
        System.out.println("[" + context.getCar().getModel() + "] Executing sequence of " + expressions.size() + " commands:");
        
        for (int i = 0; i < expressions.size(); i++) {
            CarExpression expression = expressions.get(i);
            System.out.println("  Step " + (i + 1) + ": " + expression.getExpressionType());
            expression.interpret(context);
        }
        
        System.out.println("[" + context.getCar().getModel() + "] Sequence completed");
    }
    
    @Override
    public String getExpressionType() {
        return "Sequence";
    }
    
    /**
     * Gets the number of expressions in the sequence
     * @return The number of expressions
     */
    public int getExpressionCount() {
        return expressions.size();
    }
    
    /**
     * Gets all expressions in the sequence
     * @return List of expressions
     */
    public List<CarExpression> getExpressions() {
        return new ArrayList<>(expressions);
    }
    
    /**
     * Clears all expressions from the sequence
     */
    public void clear() {
        expressions.clear();
    }
} 