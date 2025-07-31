package designPatterns.structural.bridge;

/**
 * Main class demonstrating the Bridge pattern.
 * This example shows how the Bridge pattern allows us to combine
 * different car types with different engine types without creating
 * separate classes for each combination.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("=== Bridge Pattern Demo ===\n");
        
        // Create different engine implementations
        Engine gasolineEngine = new GasolineEngine(200);
        Engine electricEngine = new ElectricEngine(300, 75);
        
        // Create different car types with different engines
        Car gasolineSedan = new Sedan(gasolineEngine);
        Car electricSedan = new Sedan(electricEngine);
        Car gasolineSUV = new SUV(gasolineEngine);
        Car electricSUV = new SUV(electricEngine);
        
        // Demonstrate the flexibility of the Bridge pattern
        System.out.println("1. Gasoline Sedan:");
        gasolineSedan.getInfo();
        gasolineSedan.start();
        gasolineSedan.drive();
        gasolineSedan.comfortableRide();
        gasolineSedan.stop();
        
        System.out.println("\n2. Electric Sedan:");
        electricSedan.getInfo();
        electricSedan.start();
        electricSedan.drive();
        electricSedan.comfortableRide();
        electricSedan.stop();
        
        System.out.println("\n3. Gasoline SUV:");
        gasolineSUV.getInfo();
        gasolineSUV.start();
        gasolineSUV.drive();
        gasolineSUV.offRoadCapability();
        gasolineSUV.loadCargo();
        gasolineSUV.stop();
        
        System.out.println("\n4. Electric SUV:");
        electricSUV.getInfo();
        electricSUV.start();
        electricSUV.drive();
        electricSUV.offRoadCapability();
        electricSUV.loadCargo();
        electricSUV.stop();
        
        System.out.println("\n=== Bridge Pattern Benefits ===");
        System.out.println("✓ We can combine any car type with any engine type");
        System.out.println("✓ No need for classes like GasolineSedan, ElectricSUV, etc.");
        System.out.println("✓ Car types and engine types can be developed independently");
        System.out.println("✓ Easy to add new car types or engine types");
    }
} 