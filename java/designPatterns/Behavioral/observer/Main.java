package designPatterns.Behavioral.observer;

/**
 * Main class demonstrating the Observer pattern.
 * This example shows how the Observer pattern allows us to:
 * - Notify multiple objects about state changes
 * - Maintain loose coupling between subject and observers
 * - Support dynamic subscription/unsubscription
 * - Implement event-driven communication
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("=== Observer Pattern Demo ===\n");
        
        // Create the subject (car dealer)
        CarDealer dealer = new CarDealer("Premium Auto Sales");
        
        // Create observers (buyers)
        CarBuyer john = new IndividualBuyer("John", "Toyota", 25000);
        CarBuyer sarah = new IndividualBuyer("Sarah", "Honda", 30000);
        CarBuyer mike = new IndividualBuyer("Mike", null, 20000); // No brand preference
        CarBuyer cityDealer = new DealerBuyer("City Auto Group", "BMW", 40000, 10);
        CarBuyer luxuryDealer = new DealerBuyer("Luxury Motors", "Tesla", 60000, 5);
        
        // Subscribe observers to the dealer
        System.out.println("1. Setting up subscriptions:");
        dealer.addObserver(john);
        dealer.addObserver(sarah);
        dealer.addObserver(mike);
        dealer.addObserver(cityDealer);
        dealer.addObserver(luxuryDealer);
        
        System.out.println("\n2. Adding new cars to inventory:");
        Car toyota = new Car("Toyota", "Camry", 2022, 28000, "Silver");
        Car honda = new Car("Honda", "Civic", 2023, 25000, "Blue");
        Car bmw = new Car("BMW", "X3", 2022, 45000, "Black");
        Car tesla = new Car("Tesla", "Model 3", 2023, 55000, "White");
        
        dealer.addCar(toyota);
        dealer.addCar(honda);
        dealer.addCar(bmw);
        dealer.addCar(tesla);
        
        System.out.println("\n3. Changing prices:");
        dealer.changePrice(toyota, 26000); // Price drop
        dealer.changePrice(honda, 27000); // Price increase
        
        System.out.println("\n4. Selling a car:");
        dealer.sellCar(bmw);
        
        System.out.println("\n5. Demonstrating unsubscription:");
        dealer.removeObserver(mike);
        
        System.out.println("\n6. Adding another car (Mike won't be notified):");
        Car ford = new Car("Ford", "Mustang", 2022, 35000, "Red");
        dealer.addCar(ford);
        
        System.out.println("\n7. Current inventory:");
        for (Car car : dealer.getInventory()) {
            System.out.println("  - " + car);
        }
        
        System.out.println("\n=== Observer Pattern Benefits ===");
        System.out.println("✓ Loose coupling between dealer and buyers");
        System.out.println("✓ Dynamic subscription/unsubscription");
        System.out.println("✓ Automatic notifications to all interested parties");
        System.out.println("✓ Easy to add new observer types");
        System.out.println("✓ Observers can filter notifications based on preferences");
        System.out.println("✓ One-to-many relationship (one dealer, many buyers)");
    }
} 