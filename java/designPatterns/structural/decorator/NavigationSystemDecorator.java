package designPatterns.structural.decorator;

public class NavigationSystemDecorator extends CarFeatureDecorator {

    public NavigationSystemDecorator(Car car) {
        super(car);
    }

    @Override
    public String getDescription() {
        return super.getDescription() + ", Navigation System";
    }

    @Override
    public double getCost() {
        return super.getCost() + 2000;
    }
}
