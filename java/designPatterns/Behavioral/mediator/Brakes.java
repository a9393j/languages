package designPatterns.Behavioral.mediator;

/**
 * Brakes - Concrete Colleague in the Mediator pattern.
 * This represents a brakes component that communicates through the mediator.
 */
public class Brakes implements CarComponent {
    private String name;
    private CarMediator mediator;
    private boolean isApplied;
    private int brakePressure;
    
    /**
     * Constructor for Brakes
     * @param name The brakes name
     */
    public Brakes(String name) {
        this.name = name;
        this.isApplied = false;
        this.brakePressure = 0;
    }
    
    @Override
    public void setMediator(CarMediator mediator) {
        this.mediator = mediator;
    }
    
    @Override
    public String getName() {
        return name;
    }
    
    @Override
    public String getType() {
        return "Brakes";
    }
    
    @Override
    public String getStatus() {
        if (!isApplied) {
            return "Released";
        }
        return "Applied with " + brakePressure + "% pressure";
    }
    
    @Override
    public void receiveMessage(String message) {
        System.out.println("[" + name + "] Received: " + message);
        
        if (message.contains("apply")) {
            apply();
        } else if (message.contains("release")) {
            release();
        } else if (message.contains("emergency")) {
            emergencyStop();
        } else if (message.contains("light")) {
            lightBrake();
        } else if (message.contains("hard")) {
            hardBrake();
        }
    }
    
    @Override
    public void sendMessage(String message) {
        if (mediator != null) {
            mediator.sendMessage(this, message);
        }
    }
    
    /**
     * Applies the brakes
     */
    public void apply() {
        if (!isApplied) {
            isApplied = true;
            brakePressure = 50; // Default pressure
            System.out.println("[" + name + "] Brakes applied");
            sendMessage("Brakes applied, vehicle slowing down");
        }
    }
    
    /**
     * Releases the brakes
     */
    public void release() {
        if (isApplied) {
            isApplied = false;
            brakePressure = 0;
            System.out.println("[" + name + "] Brakes released");
            sendMessage("Brakes released, vehicle can accelerate");
        }
    }
    
    /**
     * Applies emergency brakes
     */
    public void emergencyStop() {
        isApplied = true;
        brakePressure = 100;
        System.out.println("[" + name + "] Emergency brake applied!");
        sendMessage("EMERGENCY STOP - Maximum brake pressure applied");
    }
    
    /**
     * Applies light brakes
     */
    public void lightBrake() {
        if (isApplied) {
            brakePressure = 25;
            System.out.println("[" + name + "] Light brake applied");
            sendMessage("Light brake pressure applied");
        }
    }
    
    /**
     * Applies hard brakes
     */
    public void hardBrake() {
        if (isApplied) {
            brakePressure = 75;
            System.out.println("[" + name + "] Hard brake applied");
            sendMessage("Hard brake pressure applied");
        }
    }
    
    /**
     * Gets the brake pressure
     * @return The brake pressure percentage
     */
    public int getBrakePressure() {
        return brakePressure;
    }
    
    /**
     * Checks if the brakes are applied
     * @return true if applied, false otherwise
     */
    public boolean isApplied() {
        return isApplied;
    }
} 