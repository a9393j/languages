package designPatterns.Creational.AbstractFactory;

import designPatterns.Creational.AbstractFactory.cars.Car;
import designPatterns.Creational.AbstractFactory.cars.factories.CarFactory;
import designPatterns.Creational.AbstractFactory.cars.factories.ElectricCarFactory;
import designPatterns.Creational.AbstractFactory.cars.factories.GasolineCarFactory;

public class DemoAbstractFactory {

    public static void main(String[] args) {
        CarFactory electricCarFactory = new ElectricCarFactory();
        Car electricSedan = electricCarFactory.createSedan();
        electricSedan.drive(); // Output: Driving an electric sedan.

        CarFactory gasolineCarFactory = new GasolineCarFactory();
        Car gasolineSUV = gasolineCarFactory.createSUV();
        gasolineSUV.drive(); // Output: Driving a gasoline SUV.
    }

}
