package designPatterns.Behavioral.visitor;

/**
 * CarVisitor - This is the Visitor interface in the Visitor pattern.
 * It declares a visit operation for each concrete element class.
 * Each visitor can implement different operations for different component types.
 */
public interface CarVisitor {
    /**
     * Visits an Engine component
     * @param engine The engine to visit
     */
    void visitEngine(Engine engine);
    
    /**
     * Visits a Wheels component
     * @param wheels The wheels to visit
     */
    void visitWheels(Wheels wheels);
    
    /**
     * Visits a Brakes component
     * @param brakes The brakes to visit
     */
    void visitBrakes(Brakes brakes);
    
    /**
     * Gets the visitor name
     * @return The visitor name
     */
    String getVisitorName();
} 