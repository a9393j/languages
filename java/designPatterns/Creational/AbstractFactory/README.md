# Abstract Factory Pattern

## Overview
The Abstract Factory pattern provides an interface for creating families of related objects without specifying their concrete classes. It ensures that the created objects are compatible with each other.

## Problem
When you need to create families of related objects (e.g., cars with different engine types and body styles) and want to ensure that the objects work together properly.

## Solution
Create an abstract factory interface that defines methods for creating related objects, and concrete factories that implement these methods to create specific families of objects.

## Structure

### Core Components

1. **CarFactory (Abstract Factory)**
   - Abstract interface defining methods to create cars
   - `createSedan()` and `createSUV()` methods

2. **Concrete Factories**
   - `ElectricCarFactory`: Creates electric cars (sedan and SUV)
   - `GasolineCarFactory`: Creates gasoline cars (sedan and SUV)

3. **Car (Abstract Product)**
   - Common interface for all car types

4. **Concrete Products**
   - `ElectricSedan`, `ElectricSUV`: Electric car variants
   - `GasolineSedan`, `GasolineSUV`: Gasoline car variants

## Implementation Details

### Abstract Factory Interface
```java
public abstract class CarFactory {
    public abstract Car createSedan();
    public abstract Car createSUV();
}
```

### Concrete Factory Implementation
```java
public class ElectricCarFactory extends CarFactory {
    @Override
    public Car createSedan() {
        return new ElectricSedan();
    }
    
    @Override
    public Car createSUV() {
        return new ElectricSUV();
    }
}
```

### Client Usage
```java
CarFactory electricFactory = new ElectricCarFactory();
Car electricSedan = electricFactory.createSedan();

CarFactory gasolineFactory = new GasolineCarFactory();
Car gasolineSUV = gasolineFactory.createSUV();
```

## Benefits
- **Family Consistency**: Ensures that related objects work together
- **Loose Coupling**: Client code doesn't depend on concrete classes
- **Easy Extension**: Adding new families is straightforward
- **Product Compatibility**: Guarantees that products from the same factory are compatible

## Use Cases
- **Car Manufacturing**: Creating families of cars with different engine types
- **UI Frameworks**: Creating families of UI components for different themes
- **Database Systems**: Creating families of database objects (tables, queries, etc.)
- **Cross-Platform Development**: Creating families of platform-specific objects

## Example Output
```
Driving an electric sedan.
Driving a gasoline SUV.
```

## Related Patterns
- **Factory Method**: Simpler version for creating single objects
- **Builder**: For complex object construction
- **Prototype**: For cloning existing objects
- **Singleton**: Often used with factories to ensure single instance 