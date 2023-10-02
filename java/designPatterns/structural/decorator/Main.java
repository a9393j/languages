package designPatterns.structural.decorator;

public class Main {
    public static void main(String[] args) {
        Car baseCar = new BaseCar();
        System.out.println(baseCar.getDescription() + " costs $" + baseCar.getCost());

        Car carWithNav = new NavigationSystemDecorator(new BaseCar());
        System.out.println(carWithNav.getDescription() + " costs $" + carWithNav.getCost());

        Car carWithNavAndSunroof = new SunroofDecorator(new NavigationSystemDecorator(new BaseCar()));
        System.out.println(carWithNavAndSunroof.getDescription() + " costs $" + carWithNavAndSunroof.getCost());
    }
}
