package designPatterns.Creational.AbstractFactory.cars.products;

import designPatterns.Creational.AbstractFactory.cars.Car;

public class ElectricSedan implements Car {
    @Override
    public void drive() {
        System.out.println("Driving an electric sedan.");
    }
}
