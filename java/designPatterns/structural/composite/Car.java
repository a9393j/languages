package designPatterns.structural.composite;

import java.util.ArrayList;
import java.util.List;

public class Car implements CarComponent {
    private final List<CarComponent> components = new ArrayList<>();

    public void addComponent(CarComponent component) {
        components.add(component);
    }

    @Override
    public void assemble() {
        System.out.println("Assembling the car:");
        for (CarComponent component : components) {
            component.assemble();
        }
        System.out.println("Car assembly complete.");
    }
}
