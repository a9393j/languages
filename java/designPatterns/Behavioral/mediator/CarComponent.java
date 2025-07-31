package designPatterns.Behavioral.mediator;

/**
 * CarComponent - This is the Colleague interface in the Mediator pattern.
 * It defines an interface for communicating with the Mediator.
 * All car components must implement this interface to participate in the mediation.
 */
public interface CarComponent {
    /**
     * Sets the mediator for this component
     * @param mediator The mediator to set
     */
    void setMediator(CarMediator mediator);
    
    /**
     * Gets the component name
     * @return The component name
     */
    String getName();
    
    /**
     * Gets the component type
     * @return The component type
     */
    String getType();
    
    /**
     * Gets the component status
     * @return The component status
     */
    String getStatus();
    
    /**
     * Receives a message from the mediator
     * @param message The message to receive
     */
    void receiveMessage(String message);
    
    /**
     * Sends a message to the mediator
     * @param message The message to send
     */
    void sendMessage(String message);
} 