# Decorator Pattern

## Overview
The Decorator pattern allows you to dynamically add new functionality to objects without altering their structure. It provides a flexible alternative to subclassing for extending functionality.

## Problem
When you need to add new functionality to objects at runtime, and you want to avoid creating a large number of subclasses for every possible combination of features.

## Solution
Create a decorator class that implements the same interface as the component and contains a reference to a component object. The decorator adds behavior before and/or after delegating to the component.

## Structure

### Core Components

1. **Car (Component Interface)**
   - Defines the interface for objects that can have responsibilities added
   - Contains `getDescription()` and `getCost()` methods

2. **BaseCar (Concrete Component)**
   - Basic implementation of the Car interface
   - Represents the base car without any additional features

3. **CarFeatureDecorator (Abstract Decorator)**
   - Abstract class that implements the Car interface
   - Contains a reference to a Car object
   - Delegates calls to the wrapped car

4. **Concrete Decorators**
   - `NavigationSystemDecorator`: Adds navigation system functionality
   - `SunroofDecorator`: Adds sunroof functionality

## Implementation Details

### Component Interface
```java
public interface Car {
    String getDescription();
    double getCost();
}
```

### Abstract Decorator
```java
public abstract class CarFeatureDecorator implements Car {
    protected Car car;
    
    public CarFeatureDecorator(Car car) {
        this.car = car;
    }
    
    @Override
    public String getDescription() {
        return car.getDescription();
    }
    
    @Override
    public double getCost() {
        return car.getCost();
    }
}
```

### Concrete Decorator
```java
public class NavigationSystemDecorator extends CarFeatureDecorator {
    public NavigationSystemDecorator(Car car) {
        super(car);
    }
    
    @Override
    public String getDescription() {
        return car.getDescription() + ", Navigation System";
    }
    
    @Override
    public double getCost() {
        return car.getCost() + 1000.0;
    }
}
```

### Client Usage
```java
Car baseCar = new BaseCar();
Car carWithNav = new NavigationSystemDecorator(new BaseCar());
Car carWithNavAndSunroof = new SunroofDecorator(new NavigationSystemDecorator(new BaseCar()));
```

## Benefits
- **Flexibility**: Can add functionality at runtime
- **Single Responsibility**: Each decorator has a single responsibility
- **Open/Closed Principle**: Can add new decorators without modifying existing code
- **Composition over Inheritance**: Uses composition instead of inheritance

## Use Cases
- **Car Customization**: Adding features to cars dynamically
- **Coffee Shop**: Adding toppings to coffee orders
- **Text Processing**: Adding formatting to text
- **GUI Components**: Adding borders, scrollbars, etc. to components

## Example Output
```
Basic Car costs $20000.0
Basic Car, Navigation System costs $21000.0
Basic Car, Navigation System, Sunroof costs $21500.0
```

## Related Patterns
- **Adapter**: Can be used together to adapt and decorate
- **Bridge**: Similar structure but focuses on abstraction and implementation
- **Composite**: Can be used together for complex object structures
- **Proxy**: Similar structure but focuses on controlling access

## Best Practices
- Keep decorators focused on a single responsibility
- Use composition over inheritance
- Consider the order of decorators (can affect behavior)
- Be careful with method signatures in decorators 