package designPatterns.Creational.AbstractFactory.cars.factories;

import designPatterns.Creational.AbstractFactory.GasolineSUV;
import designPatterns.Creational.AbstractFactory.GasolineSedan;
import designPatterns.Creational.AbstractFactory.cars.Car;

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