package designPatterns.structural.composite;

import designPatterns.structural.composite.components.*; 

public class Main {
    public static void main(String[] args) {
        CarComponent engine = new Engine();
        CarComponent wheels = new Wheels();
        CarComponent seats = new Seats();

        Car myCar = new Car();
        myCar.addComponent(engine);
        myCar.addComponent(wheels);
        myCar.addComponent(seats);

        myCar.assemble();
    }
}
