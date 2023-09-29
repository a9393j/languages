package designPatterns.Creational.factory;

public class Sedan implements Car {
    @Override
    public void drive() {
        System.out.println("Driving a sedan car.");
    }
}
