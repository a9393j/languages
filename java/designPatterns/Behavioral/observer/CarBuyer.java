package designPatterns.Behavioral.observer;

/**
 * CarBuyer - This is the Observer interface in the Observer pattern.
 * It defines an interface for objects that should be notified of changes
 * in the subject (CarDealer).
 */
public interface CarBuyer {
    /**
     * Called when the subject (CarDealer) notifies observers of a change
     * @param car The car that triggered the notification
     * @param eventType The type of event (NEW_CAR, PRICE_CHANGE, CAR_SOLD)
     */
    void update(Car car, String eventType);
    
    /**
     * Gets the buyer's name
     * @return The buyer's name
     */
    String getName();
    
    /**
     * Gets the buyer's preferred car make (for filtering notifications)
     * @return The preferred car make, or null if no preference
     */
    String getPreferredMake();
} 