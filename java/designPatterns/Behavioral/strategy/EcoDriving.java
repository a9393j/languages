package designPatterns.Behavioral.strategy;

/**
 * EcoDriving - Concrete Strategy in the Strategy pattern.
 * This implements an eco-friendly driving strategy with high fuel efficiency
 * but lower performance.
 */
public class EcoDriving implements DrivingStrategy {
    
    @Override
    public void drive(Car car) {
        System.out.println("Driving " + car.getModel() + " in Eco mode");
        System.out.println("  - Gentle acceleration and braking");
        System.out.println("  - Optimized speed for fuel efficiency");
        System.out.println("  - Minimal fuel consumption");
        System.out.println("  - Regenerative braking enabled");
        System.out.println("  - Eco-friendly route optimization");
        System.out.println("  - Reduced emissions");
    }
    
    @Override
    public String getStrategyName() {
        return "Eco Driving";
    }
    
    @Override
    public int getFuelEfficiency() {
        return 10; // Maximum fuel efficiency
    }
    
    @Override
    public int getPerformanceRating() {
        return 4; // Lower performance for efficiency
    }
} 