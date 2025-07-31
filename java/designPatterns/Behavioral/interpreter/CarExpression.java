package designPatterns.Behavioral.interpreter;

/**
 * CarExpression - This is the AbstractExpression in the Interpreter pattern.
 * It declares an interpret operation that all concrete expressions must implement.
 * This interface defines the grammar for car commands.
 */
public interface CarExpression {
    /**
     * Interprets the expression and executes the corresponding car action
     * @param context The context containing the car and any variables
     */
    void interpret(CarContext context);
    
    /**
     * Gets the expression type
     * @return The expression type
     */
    String getExpressionType();
} 