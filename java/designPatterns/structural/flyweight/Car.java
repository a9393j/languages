package designPatterns.structural.flyweight;

public class Car {
    public final CarColor carColor;

    public Car(CarColor carColor) {
        this.carColor = carColor;
    }

    public void drive() {
        System.out.println("Driving a " + carColor.getColor() + " car.");
    }
}
