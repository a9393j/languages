package designPatterns.Behavioral.strategy;

/**
 * NormalDriving - Concrete Strategy in the Strategy pattern.
 * This implements a normal driving strategy with balanced performance
 * and fuel efficiency.
 */
public class NormalDriving implements DrivingStrategy {
    
    @Override
    public void drive(Car car) {
        System.out.println("Driving " + car.getModel() + " in Normal mode");
        System.out.println("  - Balanced acceleration and braking");
        System.out.println("  - Moderate speed limits");
        System.out.println("  - Standard fuel consumption");
        System.out.println("  - Comfortable ride for daily use");
    }
    
    @Override
    public String getStrategyName() {
        return "Normal Driving";
    }
    
    @Override
    public int getFuelEfficiency() {
        return 7; // Good fuel efficiency
    }
    
    @Override
    public int getPerformanceRating() {
        return 6; // Moderate performance
    }
} 