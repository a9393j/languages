package designPatterns.Creational.factory;

public class CarFactory {
    public enum CarType {
        SEDAN, SUV
    }

    public Car getCar(CarType carType) {
        Car car = null;
        switch (carType) {
            case SEDAN:
                car = new Sedan();
                break;
            case SUV:
                car = new SUV();
                break;
            default:
                // throw an IllegalArgumentException or return null, as you prefer
                throw new IllegalArgumentException("Unknown car type.");
        }
        return car;
    }
}
