package designPatterns.Creational.AbstractFactory.cars.factories;

import designPatterns.Creational.AbstractFactory.cars.Car;
import designPatterns.Creational.AbstractFactory.cars.products.GasolineSUV;
import designPatterns.Creational.AbstractFactory.cars.products.GasolineSedan;

public class GasolineCarFactory implements CarFactory {
    @Override
    public Car createSedan() {
        return new GasolineSedan();
    }

    @Override
    public Car createSUV() {
        return new GasolineSUV();
    }
}