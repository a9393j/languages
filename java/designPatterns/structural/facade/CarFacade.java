package designPatterns.structural.facade;

public class CarFacade {
    private Engine engine;
    private FuelInjector fuelInjector;
    private AirConditioner airConditioner;

    public CarFacade() {
        this.engine = new Engine();
        this.fuelInjector = new FuelInjector();
        this.airConditioner = new AirConditioner();
    }

    public void startCar() {
        System.out.println("Starting the car:");
        engine.start();
        fuelInjector.on();
        fuelInjector.injectFuel();
        airConditioner.on();
        System.out.println("Car started.\n");
    }

    public void stopCar() {
        System.out.println("Stopping the car:");
        engine.stop();
        fuelInjector.off();
        airConditioner.off();
        System.out.println("Car stopped.");
    }
}
