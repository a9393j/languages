package designPatterns.Creational.AbstractFactory.cars.products;

import designPatterns.Creational.AbstractFactory.cars.Car;

public class GasolineSedan implements Car {
    @Override
    public void drive() {
        System.out.println("Driving a gasoline sedan.");
    }
}
