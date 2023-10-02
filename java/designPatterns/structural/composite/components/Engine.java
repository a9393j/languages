package designPatterns.structural.composite.components;

import designPatterns.structural.composite.CarComponent;

public class Engine implements CarComponent {
    @Override
    public void assemble() {
        System.out.println("Assembling engine.");
    }
}
