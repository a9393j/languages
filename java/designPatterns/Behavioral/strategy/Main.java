package designPatterns.Behavioral.strategy;

/**
 * Main class demonstrating the Strategy pattern.
 * This example shows how the Strategy pattern allows us to:
 * - Define a family of algorithms
 * - Make algorithms interchangeable at runtime
 * - Avoid multiple conditional statements
 * - Encapsulate algorithm implementation details
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("=== Strategy Pattern Demo ===\n");
        
        // Create a car
        Car car = new Car("Tesla Model S");
        
        System.out.println("1. Initial driving strategy:");
        car.getStrategyInfo();
        car.drive();
        
        System.out.println("\n2. Switching to Sport mode:");
        car.setDrivingStrategy(new SportDriving());
        car.getStrategyInfo();
        car.drive();
        
        System.out.println("\n3. Switching to Eco mode:");
        car.setDrivingStrategy(new EcoDriving());
        car.getStrategyInfo();
        car.drive();
        
        System.out.println("\n4. Switching back to Normal mode:");
        car.setDrivingStrategy(new NormalDriving());
        car.getStrategyInfo();
        car.drive();
        
        System.out.println("\n5. Demonstrating strategy comparison:");
        System.out.println("=== Strategy Comparison ===");
        
        DrivingStrategy[] strategies = {
            new NormalDriving(),
            new SportDriving(),
            new EcoDriving()
        };
        
        for (DrivingStrategy strategy : strategies) {
            System.out.println("Strategy: " + strategy.getStrategyName());
            System.out.println("  Fuel Efficiency: " + strategy.getFuelEfficiency() + "/10");
            System.out.println("  Performance: " + strategy.getPerformanceRating() + "/10");
            System.out.println();
        }
        
        System.out.println("6. Dynamic strategy selection based on conditions:");
        System.out.println("=== Dynamic Strategy Selection ===");
        
        // Simulate different driving conditions
        String[] conditions = {"Highway", "City Traffic", "Mountain Road"};
        
        for (String condition : conditions) {
            System.out.println("Condition: " + condition);
            
            DrivingStrategy selectedStrategy;
            switch (condition) {
                case "Highway":
                    selectedStrategy = new SportDriving();
                    break;
                case "City Traffic":
                    selectedStrategy = new EcoDriving();
                    break;
                case "Mountain Road":
                    selectedStrategy = new NormalDriving();
                    break;
                default:
                    selectedStrategy = new NormalDriving();
            }
            
            car.setDrivingStrategy(selectedStrategy);
            car.drive();
            System.out.println();
        }
        
        System.out.println("=== Strategy Pattern Benefits ===");
        System.out.println("✓ Algorithms are encapsulated in separate classes");
        System.out.println("✓ Strategies can be switched at runtime");
        System.out.println("✓ No conditional statements for algorithm selection");
        System.out.println("✓ Easy to add new strategies without changing existing code");
        System.out.println("✓ Algorithm implementation details are hidden from clients");
        System.out.println("✓ Strategies can be tested independently");
    }
} 