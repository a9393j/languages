package designPatterns.Behavioral.memento;

/**
 * CarMemento - This is the Memento in the Memento pattern.
 * It stores the internal state of the Car (Originator) object.
 * The memento is immutable and only the Originator can access its contents.
 */
public class CarMemento {
    private final String model;
    private final int speed;
    private final int fuelLevel;
    private final boolean isEngineRunning;
    private final int gear;
    private final String timestamp;
    
    /**
     * Constructor for CarMemento
     * @param model The car model
     * @param speed The car speed
     * @param fuelLevel The fuel level
     * @param isEngineRunning Whether the engine is running
     * @param gear The current gear
     * @param timestamp When the memento was created
     */
    public CarMemento(String model, int speed, int fuelLevel, boolean isEngineRunning, int gear, String timestamp) {
        this.model = model;
        this.speed = speed;
        this.fuelLevel = fuelLevel;
        this.isEngineRunning = isEngineRunning;
        this.gear = gear;
        this.timestamp = timestamp;
    }
    
    /**
     * Gets the car model
     * @return The car model
     */
    public String getModel() {
        return model;
    }
    
    /**
     * Gets the car speed
     * @return The car speed
     */
    public int getSpeed() {
        return speed;
    }
    
    /**
     * Gets the fuel level
     * @return The fuel level
     */
    public int getFuelLevel() {
        return fuelLevel;
    }
    
    /**
     * Checks if the engine is running
     * @return true if engine is running, false otherwise
     */
    public boolean isEngineRunning() {
        return isEngineRunning;
    }
    
    /**
     * Gets the current gear
     * @return The current gear
     */
    public int getGear() {
        return gear;
    }
    
    /**
     * Gets the timestamp when the memento was created
     * @return The timestamp
     */
    public String getTimestamp() {
        return timestamp;
    }
    
    @Override
    public String toString() {
        return "CarMemento{" +
                "model='" + model + '\'' +
                ", speed=" + speed +
                ", fuelLevel=" + fuelLevel +
                ", isEngineRunning=" + isEngineRunning +
                ", gear=" + gear +
                ", timestamp='" + timestamp + '\'' +
                '}';
    }
} 