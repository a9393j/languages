package designPatterns.structural.proxy;

public class CarProxy {
    private Car car;
    private final String model;

    public CarProxy(String model) {
        this.model = model;
    }

    public void drive() {
        if (car == null) {
            car = new Car(model);  // Lazy initialization
        }
        car.drive();
    }
}
