package designPatterns.Behavioral.mediator;

/**
 * Transmission - Concrete Colleague in the Mediator pattern.
 * This represents a transmission component that communicates through the mediator.
 */
public class Transmission implements CarComponent {
    private String name;
    private CarMediator mediator;
    private int currentGear;
    private boolean isEngaged;
    
    /**
     * Constructor for Transmission
     * @param name The transmission name
     */
    public Transmission(String name) {
        this.name = name;
        this.currentGear = 0; // Neutral
        this.isEngaged = false;
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
        return "Transmission";
    }
    
    @Override
    public String getStatus() {
        if (!isEngaged) {
            return "Disengaged";
        }
        return currentGear == 0 ? "Neutral" : "Gear " + currentGear;
    }
    
    @Override
    public void receiveMessage(String message) {
        System.out.println("[" + name + "] Received: " + message);
        
        if (message.contains("engage")) {
            engage();
        } else if (message.contains("disengage")) {
            disengage();
        } else if (message.contains("shift up")) {
            shiftUp();
        } else if (message.contains("shift down")) {
            shiftDown();
        } else if (message.contains("neutral")) {
            setNeutral();
        }
    }
    
    @Override
    public void sendMessage(String message) {
        if (mediator != null) {
            mediator.sendMessage(this, message);
        }
    }
    
    /**
     * Engages the transmission
     */
    public void engage() {
        if (!isEngaged) {
            isEngaged = true;
            currentGear = 1;
            System.out.println("[" + name + "] Transmission engaged in first gear");
            sendMessage("Transmission engaged, ready to drive");
        }
    }
    
    /**
     * Disengages the transmission
     */
    public void disengage() {
        if (isEngaged) {
            isEngaged = false;
            currentGear = 0;
            System.out.println("[" + name + "] Transmission disengaged");
            sendMessage("Transmission disengaged");
        }
    }
    
    /**
     * Shifts to a higher gear
     */
    public void shiftUp() {
        if (isEngaged && currentGear < 6) {
            currentGear++;
            System.out.println("[" + name + "] Shifted up to gear " + currentGear);
            sendMessage("Shifted to gear " + currentGear);
        }
    }
    
    /**
     * Shifts to a lower gear
     */
    public void shiftDown() {
        if (isEngaged && currentGear > 1) {
            currentGear--;
            System.out.println("[" + name + "] Shifted down to gear " + currentGear);
            sendMessage("Shifted to gear " + currentGear);
        }
    }
    
    /**
     * Sets the transmission to neutral
     */
    public void setNeutral() {
        if (isEngaged) {
            currentGear = 0;
            System.out.println("[" + name + "] Transmission set to neutral");
            sendMessage("Transmission in neutral");
        }
    }
    
    /**
     * Gets the current gear
     * @return The current gear
     */
    public int getCurrentGear() {
        return currentGear;
    }
    
    /**
     * Checks if the transmission is engaged
     * @return true if engaged, false otherwise
     */
    public boolean isEngaged() {
        return isEngaged;
    }
} 