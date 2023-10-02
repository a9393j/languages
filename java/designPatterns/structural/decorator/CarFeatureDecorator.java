package designPatterns.structural.decorator;

public abstract class CarFeatureDecorator implements Car {
    protected Car car;

    public CarFeatureDecorator(Car car) {
        this.car = car;
    }

    @Override
    public String getDescription() {
        return car.getDescription();
    }

    @Override
    public double getCost() {
        return car.getCost();
    }
}
