package designPatterns.Creational.AbstractFactory.cars.factories;

import designPatterns.Creational.AbstractFactory.ElectricSUV;
import designPatterns.Creational.AbstractFactory.ElectricSedan;
import designPatterns.Creational.AbstractFactory.cars.Car;

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