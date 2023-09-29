package designPatterns.Creational.Builder;

public class Car {
    private String make;
    private String model;
    private String color;
    private int year;
    private boolean isElectric;

    public Car(String make, String model, String color, int year, boolean isElectric) {
        this.make = make;
        this.model = model;
        this.color = color;
        this.year = year;
        this.isElectric = isElectric;
    }

    @Override
    public String toString() {
        return "Car [make=" + make + ", model=" + model + ", color=" + color + ", year=" + year + ", isElectric="
                + isElectric + "]";
    }
}
