package designPatterns.Creational.factory;

public class DemoFactory {

    public static void main(String[] args) {
        CarFactory carFactory = new CarFactory();

        Car car1 = carFactory.getCar(CarFactory.CarType.SEDAN);
        car1.drive(); // Output: Driving a sedan car.

        Car car2 = carFactory.getCar(CarFactory.CarType.SUV);
        car2.drive(); // Output: Driving an SUV car.
    }

}
