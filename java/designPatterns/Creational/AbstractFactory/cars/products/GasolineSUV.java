package designPatterns.Creational.AbstractFactory.cars.products;

import designPatterns.Creational.AbstractFactory.cars.Car;

public class GasolineSUV implements Car {
    @Override
    public void drive() {
        System.out.println("Driving a gasoline SUV.");
    }
}