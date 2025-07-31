package designPatterns.Behavioral.visitor;

import java.util.ArrayList;
import java.util.List;

/**
 * Car - This class contains a collection of car components and can accept visitors.
 * It demonstrates how the Visitor pattern can be used to perform operations
 * on a collection of different component types.
 */
public class Car {
    private String model;
    private List<CarComponent> components;
    
    /**
     * Constructor for Car
     * @param model The car model
     */
    public Car(String model) {
        this.model = model;
        this.components = new ArrayList<>();
    }
    
    /**
     * Adds a component to the car
     * @param component The component to add
     */
    public void addComponent(CarComponent component) {
        components.add(component);
    }
    
    /**
     * Accepts a visitor and applies it to all components
     * @param visitor The visitor to accept
     */
    public void accept(CarVisitor visitor) {
        System.out.println("=== " + model + " - " + visitor.getVisitorName() + " ===");
        for (CarComponent component : components) {
            component.accept(visitor);
            System.out.println();
        }
    }
    
    /**
     * Gets the car model
     * @return The car model
     */
    public String getModel() {
        return model;
    }
    
    /**
     * Gets all components
     * @return List of all components
     */
    public List<CarComponent> getComponents() {
        return new ArrayList<>(components);
    }
    
    /**
     * Gets the number of components
     * @return The number of components
     */
    public int getComponentCount() {
        return components.size();
    }
} 