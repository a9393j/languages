package designPatterns.structural.adapter;

public class CarAdapter implements ModernCar {
    private LegacyCar legacyCar;

    public CarAdapter(LegacyCar legacyCar) {
        this.legacyCar = legacyCar;
    }

    @Override
    public void turnOn() {
        legacyCar.startEngine();
    }
}
