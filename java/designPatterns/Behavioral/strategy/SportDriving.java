package designPatterns.Behavioral.strategy;

/**
 * SportDriving - Concrete Strategy in the Strategy pattern.
 * This implements a sport driving strategy with high performance
 * but lower fuel efficiency.
 */
public class SportDriving implements DrivingStrategy {
    
    @Override
    public void drive(Car car) {
        System.out.println("Driving " + car.getModel() + " in Sport mode");
        System.out.println("  - Aggressive acceleration and braking");
        System.out.println("  - Higher speed limits");
        System.out.println("  - Increased fuel consumption");
        System.out.println("  - Enhanced handling and responsiveness");
        System.out.println("  - Sport suspension settings");
    }
    
    @Override
    public String getStrategyName() {
        return "Sport Driving";
    }
    
    @Override
    public int getFuelEfficiency() {
        return 3; // Lower fuel efficiency due to aggressive driving
    }
    
    @Override
    public int getPerformanceRating() {
        return 9; // High performance
    }
} 