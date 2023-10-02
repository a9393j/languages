package designPatterns.structural.decorator;

public class BaseCar implements Car {

    @Override
    public String getDescription() {
        return "Base Car";
    }

    @Override
    public double getCost() {
        return 20000;
    }
}
