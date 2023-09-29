package designPatterns.Creational.AbstractFactory;

import designPatterns.Creational.AbstractFactory.cars.Car;

public class ElectricSUV implements Car {
    @Override
    public void drive() {
        System.out.println("Driving an electric SUV.");
    }
}
