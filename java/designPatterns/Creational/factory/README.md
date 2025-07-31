# Factory Pattern

## Overview
The Factory pattern provides an interface for creating objects without specifying their exact classes. It encapsulates object creation logic and allows the client to use a common interface to create different types of objects.

## Problem
When you need to create objects but want to avoid tight coupling between the client code and the concrete classes being instantiated.

## Solution
Create a factory class that handles the instantiation of objects based on some criteria (like type, parameters, etc.) and returns objects through a common interface.

## Structure

### Core Components

1. **Car (Product Interface)**
   - Common interface for all car types
   - Defines the contract that all cars must implement

2. **Sedan & SUV (Concrete Products)**
   - Concrete implementations of the Car interface
   - Each represents a specific type of car

3. **CarFactory (Factory)**
   - Contains the logic for creating different types of cars
   - Uses an enum `CarType` to determine which car to create
   - Provides a static method `getCar()` that returns the appropriate car instance

## Implementation Details

### Factory Method
```java
public Car getCar(CarType carType) {
    switch (carType) {
        case SEDAN:
            return new Sedan();
        case SUV:
            return new SUV();
        default:
            throw new IllegalArgumentException("Unknown car type");
    }
}
```

### Client Usage
```java
CarFactory carFactory = new CarFactory();
Car sedan = carFactory.getCar(CarFactory.CarType.SEDAN);
Car suv = carFactory.getCar(CarFactory.CarType.SUV);
```

## Benefits
- **Loose Coupling**: Client code doesn't need to know about concrete classes
- **Single Responsibility**: Object creation logic is centralized
- **Open/Closed Principle**: Easy to add new car types without modifying existing code
- **Reusability**: Factory can be reused across different parts of the application

## Use Cases
- **Car Manufacturing**: Creating different types of vehicles
- **Database Connections**: Creating different database adapters
- **UI Components**: Creating different types of buttons, forms, etc.
- **File Formats**: Creating different document readers/writers

## Example Output
```
Driving a sedan car.
Driving an SUV car.
```

## Related Patterns
- **Abstract Factory**: Creates families of related objects
- **Builder**: For complex object construction
- **Singleton**: Often used with factories to ensure single instance
- **Template Method**: Can be used in factory methods for complex creation logic 