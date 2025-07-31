package designPatterns.Behavioral.mediator;

/**
 * CarMediator - This is the Mediator interface in the Mediator pattern.
 * It defines an interface for communicating with Colleague objects.
 * The mediator centralizes complex communications between car components.
 */
public interface CarMediator {
    /**
     * Registers a component with the mediator
     * @param component The component to register
     */
    void registerComponent(CarComponent component);
    
    /**
     * Sends a message from one component to others
     * @param sender The component sending the message
     * @param message The message to send
     */
    void sendMessage(CarComponent sender, String message);
    
    /**
     * Sends a message to a specific component
     * @param sender The component sending the message
     * @param targetType The type of component to send to
     * @param message The message to send
     */
    void sendMessageToType(CarComponent sender, String targetType, String message);
    
    /**
     * Gets the system status
     * @return The system status
     */
    String getSystemStatus();
} 