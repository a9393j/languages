package designPatterns.structural.composite.components;

import designPatterns.structural.composite.CarComponent;

public class Wheels implements CarComponent {
    @Override
    public void assemble() {
        System.out.println("Attaching wheels.");
    }
}
