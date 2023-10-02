package designPatterns.structural.adapter;

public class Main {
    public static void main(String[] args) {
        LegacyCar oldCar = new LegacyCar();

        // Wrapping the LegacyCar object into a CarAdapter so it can be treated as a
        // ModernCar
        ModernCar adaptedCar = new CarAdapter(oldCar);

        adaptedCar.turnOn(); // Output: Starting the car's engine.
    }
}
