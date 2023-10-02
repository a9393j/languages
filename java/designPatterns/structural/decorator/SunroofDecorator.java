package designPatterns.structural.decorator;

public class SunroofDecorator extends CarFeatureDecorator {

    public SunroofDecorator(Car car) {
        super(car);
    }

    @Override
    public String getDescription() {
        return super.getDescription() + ", Sunroof";
    }

    @Override
    public double getCost() {
        return super.getCost() + 1500;
    }
}
