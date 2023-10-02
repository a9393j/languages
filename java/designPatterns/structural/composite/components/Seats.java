package designPatterns.structural.composite.components;

import designPatterns.structural.composite.CarComponent;

public class Seats implements CarComponent {
    @Override
    public void assemble() {
        System.out.println("Installing seats.");
    }
}
