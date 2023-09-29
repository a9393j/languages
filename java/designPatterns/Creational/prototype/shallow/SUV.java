package designPatterns.Creational.prototype.shallow;

public class SUV implements Car {
    private String model;

    public SUV(String model) {
        this.model = model;
    }

    @Override
    public Car clone() {
        return new SUV(model); // Create a new SUV object with the same model
    }

    @Override
    public void drive() {
        System.out.println("Driving a " + model + " SUV.");
    }
}
