package designPatterns.Behavioral.visitor;

/**
 * CarComponent - This is the Element interface in the Visitor pattern.
 * It defines an accept operation that takes a visitor as an argument.
 * All concrete car components must implement this interface.
 */
public interface CarComponent {
    /**
     * Accepts a visitor and calls the appropriate visit method
     * @param visitor The visitor to accept
     */
    void accept(CarVisitor visitor);
    
    /**
     * Gets the component name
     * @return The component name
     */
    String getName();
    
    /**
     * Gets the component condition (1-10, where 10 is perfect)
     * @return The component condition
     */
    int getCondition();
    
    /**
     * Gets the component type
     * @return The component type
     */
    String getType();
} 