package designPatterns.Behavioral.mediator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * CarControlSystem - Concrete Mediator in the Mediator pattern.
 * This implements the car control system that coordinates communication
 * between all car components.
 */
public class CarControlSystem implements CarMediator {
    private List<CarComponent> components;
    private Map<String, List<CarComponent>> componentsByType;
    
    /**
     * Constructor for CarControlSystem
     */
    public CarControlSystem() {
        this.components = new ArrayList<>();
        this.componentsByType = new HashMap<>();
    }
    
    @Override
    public void registerComponent(CarComponent component) {
        components.add(component);
        component.setMediator(this);
        
        // Group components by type
        String type = component.getType();
        componentsByType.computeIfAbsent(type, k -> new ArrayList<>()).add(component);
        
        System.out.println("[" + getClass().getSimpleName() + "] Registered " + component.getName() + " (" + type + ")");
    }
    
    @Override
    public void sendMessage(CarComponent sender, String message) {
        System.out.println("[" + getClass().getSimpleName() + "] Routing message from " + sender.getName() + ": " + message);
        
        // Send message to all components except the sender
        for (CarComponent component : components) {
            if (component != sender) {
                component.receiveMessage(message);
            }
        }
        
        // Handle system-level coordination
        handleSystemCoordination(sender, message);
    }
    
    @Override
    public void sendMessageToType(CarComponent sender, String targetType, String message) {
        System.out.println("[" + getClass().getSimpleName() + "] Routing message from " + sender.getName() + " to " + targetType + ": " + message);
        
        List<CarComponent> targetComponents = componentsByType.get(targetType);
        if (targetComponents != null) {
            for (CarComponent component : targetComponents) {
                if (component != sender) {
                    component.receiveMessage(message);
                }
            }
        }
    }
    
    @Override
    public String getSystemStatus() {
        StringBuilder status = new StringBuilder("=== Car Control System Status ===\n");
        for (CarComponent component : components) {
            status.append(component.getName()).append(" (").append(component.getType()).append("): ")
                   .append(component.getStatus()).append("\n");
        }
        return status.toString();
    }
    
    /**
     * Handles system-level coordination based on component messages
     * @param sender The component sending the message
     * @param message The message content
     */
    private void handleSystemCoordination(CarComponent sender, String message) {
        if (sender.getType().equals("Engine")) {
            if (message.contains("started")) {
                // When engine starts, engage transmission
                sendMessageToType(sender, "Transmission", "engage transmission");
            } else if (message.contains("stopped")) {
                // When engine stops, disengage transmission and release brakes
                sendMessageToType(sender, "Transmission", "disengage transmission");
                sendMessageToType(sender, "Brakes", "release brakes");
            } else if (message.contains("RPM increased")) {
                // When engine accelerates, shift up transmission if needed
                sendMessageToType(sender, "Transmission", "shift up transmission");
            } else if (message.contains("RPM decreased")) {
                // When engine decelerates, shift down transmission if needed
                sendMessageToType(sender, "Transmission", "shift down transmission");
            }
        } else if (sender.getType().equals("Brakes")) {
            if (message.contains("applied")) {
                // When brakes are applied, reduce engine RPM
                sendMessageToType(sender, "Engine", "decelerate engine");
            } else if (message.contains("released")) {
                // When brakes are released, allow engine to accelerate
                sendMessageToType(sender, "Engine", "accelerate engine");
            } else if (message.contains("EMERGENCY STOP")) {
                // Emergency stop - stop engine and set transmission to neutral
                sendMessageToType(sender, "Engine", "stop engine");
                sendMessageToType(sender, "Transmission", "set transmission to neutral");
            }
        } else if (sender.getType().equals("Transmission")) {
            if (message.contains("engaged")) {
                // When transmission engages, allow engine to run
                System.out.println("[" + getClass().getSimpleName() + "] System: Transmission engaged, car ready to drive");
            } else if (message.contains("disengaged")) {
                // When transmission disengages, reduce engine to idle
                sendMessageToType(sender, "Engine", "reduce engine to idle");
            }
        }
    }
    
    /**
     * Gets the number of registered components
     * @return The number of components
     */
    public int getComponentCount() {
        return components.size();
    }
    
    /**
     * Gets components by type
     * @param type The component type
     * @return List of components of the specified type
     */
    public List<CarComponent> getComponentsByType(String type) {
        return componentsByType.getOrDefault(type, new ArrayList<>());
    }
} 