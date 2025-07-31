package designPatterns.Behavioral.iterator;

/**
 * Main class demonstrating the Iterator pattern.
 * This example shows how the Iterator pattern allows us to:
 * - Access collection elements without knowing the internal structure
 * - Support multiple traversal methods
 * - Provide a uniform interface for different collection types
 * - Allow multiple simultaneous iterations
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("=== Iterator Pattern Demo ===\n");
        
        // Create a car collection
        CarCollection carCollection = new CarList();
        
        // Add some cars to the collection
        carCollection.addCar(new Car("Toyota", "Camry", 2020, "Silver"));
        carCollection.addCar(new Car("Honda", "Civic", 2021, "Blue"));
        carCollection.addCar(new Car("Ford", "Mustang", 2019, "Red"));
        carCollection.addCar(new Car("Tesla", "Model 3", 2022, "White"));
        carCollection.addCar(new Car("BMW", "X5", 2021, "Black"));
        
        System.out.println("1. Basic iteration using the iterator:");
        CarIterator iterator = carCollection.createIterator();
        
        while (iterator.hasNext()) {
            Car car = iterator.next();
            System.out.println("  - " + car);
        }
        
        System.out.println("\n2. Demonstrating reset functionality:");
        iterator.reset();
        System.out.println("Iterator reset to beginning");
        
        // Show first few cars
        for (int i = 0; i < 3 && iterator.hasNext(); i++) {
            Car car = iterator.next();
            System.out.println("  - " + car);
        }
        
        System.out.println("\n3. Demonstrating current() method:");
        Car currentCar = iterator.current();
        if (currentCar != null) {
            System.out.println("Current car: " + currentCar);
        }
        
        System.out.println("\n4. Multiple simultaneous iterations:");
        CarIterator iterator1 = carCollection.createIterator();
        CarIterator iterator2 = carCollection.createIterator();
        
        System.out.println("Iterator 1 - First 2 cars:");
        for (int i = 0; i < 2 && iterator1.hasNext(); i++) {
            System.out.println("  - " + iterator1.next());
        }
        
        System.out.println("Iterator 2 - All cars:");
        while (iterator2.hasNext()) {
            System.out.println("  - " + iterator2.next());
        }
        
        System.out.println("\n5. Iterator 1 continues from where it left off:");
        while (iterator1.hasNext()) {
            System.out.println("  - " + iterator1.next());
        }
        
        System.out.println("\n6. Demonstrating collection modification:");
        Car newCar = new Car("Audi", "A4", 2023, "Gray");
        carCollection.addCar(newCar);
        
        System.out.println("Added new car: " + newCar);
        System.out.println("Total cars in collection: " + carCollection.getCount());
        
        // Create a new iterator to see the updated collection
        CarIterator newIterator = carCollection.createIterator();
        System.out.println("Updated collection:");
        while (newIterator.hasNext()) {
            System.out.println("  - " + newIterator.next());
        }
        
        System.out.println("\n=== Iterator Pattern Benefits ===");
        System.out.println("✓ Collection structure is hidden from clients");
        System.out.println("✓ Multiple iterations can run simultaneously");
        System.out.println("✓ Easy to add new traversal methods");
        System.out.println("✓ Uniform interface for different collection types");
        System.out.println("✓ Iterator state is maintained independently");
    }
} 