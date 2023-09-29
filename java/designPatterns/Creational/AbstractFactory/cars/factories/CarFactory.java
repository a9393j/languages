package designPatterns.Creational.AbstractFactory.cars.factories;

import designPatterns.Creational.AbstractFactory.cars.Car;

public interface CarFactory {
    Car createSedan();

    Car createSUV();
}
