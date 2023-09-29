package designPatterns.Creational.AbstractFactory.cars.factories;

import designPatterns.Creational.AbstractFactory.cars.Car;
import designPatterns.Creational.AbstractFactory.cars.products.ElectricSUV;
import designPatterns.Creational.AbstractFactory.cars.products.ElectricSedan;

public class ElectricCarFactory implements CarFactory {
    @Override
    public Car createSedan() {
        return new ElectricSedan();
    }

    @Override
    public Car createSUV() {
        return new ElectricSUV();
    }
}