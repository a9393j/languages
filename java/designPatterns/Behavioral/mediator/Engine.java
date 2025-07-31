package designPatterns.Behavioral.mediator;

/**
 * Engine - Concrete Colleague in the Mediator pattern.
 * This represents an engine component that communicates through the mediator.
 */
public class Engine implements CarComponent {
    private String name;
    private CarMediator mediator;
    private boolean isRunning;
    private int rpm;
    
    /**
     * Constructor for Engine
     * @param name The engine name
     */
    public Engine(String name) {
        this.name = name;
        this.isRunning = false;
        this.rpm = 0;
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
        return "Engine";
    }
    
    @Override
    public String getStatus() {
        return isRunning ? "Running at " + rpm + " RPM" : "Stopped";
    }
    
    @Override
    public void receiveMessage(String message) {
        System.out.println("[" + name + "] Received: " + message);
        
        if (message.contains("start")) {
            start();
        } else if (message.contains("stop")) {
            stop();
        } else if (message.contains("accelerate")) {
            accelerate();
        } else if (message.contains("brake")) {
            decelerate();
        }
    }
    
    @Override
    public void sendMessage(String message) {
        if (mediator != null) {
            mediator.sendMessage(this, message);
        }
    }
    
    /**
     * Starts the engine
     */
    public void start() {
        if (!isRunning) {
            isRunning = true;
            rpm = 800; // Idle RPM
            System.out.println("[" + name + "] Engine started");
            sendMessage("Engine started, ready for operation");
        }
    }
    
    /**
     * Stops the engine
     */
    public void stop() {
        if (isRunning) {
            isRunning = false;
            rpm = 0;
            System.out.println("[" + name + "] Engine stopped");
            sendMessage("Engine stopped");
        }
    }
    
    /**
     * Accelerates the engine
     */
    public void accelerate() {
        if (isRunning) {
            rpm += 500;
            System.out.println("[" + name + "] Engine accelerated to " + rpm + " RPM");
            sendMessage("Engine RPM increased to " + rpm);
        }
    }
    
    /**
     * Decelerates the engine
     */
    public void decelerate() {
        if (isRunning && rpm > 800) {
            rpm -= 500;
            System.out.println("[" + name + "] Engine decelerated to " + rpm + " RPM");
            sendMessage("Engine RPM decreased to " + rpm);
        }
    }
    
    /**
     * Gets the current RPM
     * @return The current RPM
     */
    public int getRpm() {
        return rpm;
    }
    
    /**
     * Checks if the engine is running
     * @return true if running, false otherwise
     */
    public boolean isRunning() {
        return isRunning;
    }
} 