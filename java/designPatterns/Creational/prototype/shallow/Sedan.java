package designPatterns.Creational.prototype.shallow;

public class Sedan implements Car {
    private String model;

    public Sedan(String model) {
        this.model = model;
    }

    @Override
    public Car clone() {
        return new Sedan(model); // Create a new Sedan object with the same model
    }

    @Override
    public void drive() {
        System.out.println("Driving a " + model + " sedan.");
    }
}
