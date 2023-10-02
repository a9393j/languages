package designPatterns.structural.facade;

public class FuelInjector {
    public void on() {
        System.out.println("Fuel Injector on");
    }

    public void injectFuel() {
        System.out.println("Fuel injected");
    }

    public void off() {
        System.out.println("Fuel Injector off");
    }
}
