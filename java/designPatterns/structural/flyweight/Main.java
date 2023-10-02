package designPatterns.structural.flyweight;

public class Main {
    public static void main(String[] args) {
        CarColorFactory carColorFactory = new CarColorFactory();

        Car car1 = new Car(carColorFactory.getCarColor("Red"));
        Car car2 = new Car(carColorFactory.getCarColor("Blue"));
        Car car3 = new Car(carColorFactory.getCarColor("Red"));

        car1.drive(); // Output: Driving a Red car.
        car2.drive(); // Output: Driving a Blue car.
        car3.drive(); // Output: Driving a Red car.

        // Verify that the same color object is shared among cars with the same color
        System.out.println(car1.carColor == car3.carColor);  // Output: true
    }
}
