package designPatterns.structural.proxy;

public class Car {
    private final String model;

    public Car(String model) {
        this.model = model;
        System.out.println("Car " + model + " is created.");
    }

    public void drive() {
        System.out.println("Driving " + model + " car.");
    }
}
