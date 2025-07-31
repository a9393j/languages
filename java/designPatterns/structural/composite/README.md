# Composite Pattern

## Overview
The Composite pattern allows you to compose objects into tree structures to represent part-whole hierarchies. It lets clients treat individual objects and compositions of objects uniformly.

## Problem
When you need to represent a hierarchy of objects where some objects can contain other objects (composites) and some cannot (leaves), and you want to treat both uniformly.

## Solution
Create a common interface for both individual objects and compositions, allowing clients to work with both uniformly through the same interface.

## Structure

### Core Components

1. **CarComponent (Component Interface)**
   - Common interface for both individual components and compositions
   - Defines the `assemble()` method

2. **Car (Composite)**
   - Can contain other CarComponent objects
   - Implements the CarComponent interface
   - Manages a collection of child components

3. **Leaf Components**
   - `Engine`: Individual car component
   - `Wheels`: Individual car component
   - `Seats`: Individual car component

## Implementation Details

### Component Interface
```java
public interface CarComponent {
    void assemble();
}
```

### Composite Class
```java
public class Car implements CarComponent {
    private List<CarComponent> components = new ArrayList<>();
    
    public void addComponent(CarComponent component) {
        components.add(component);
    }
    
    @Override
    public void assemble() {
        System.out.println("Assembling car...");
        for (CarComponent component : components) {
            component.assemble();
        }
        System.out.println("Car assembly complete!");
    }
}
```

### Leaf Component
```java
public class Engine implements CarComponent {
    @Override
    public void assemble() {
        System.out.println("Assembling engine");
    }
}
```

### Client Usage
```java
CarComponent engine = new Engine();
CarComponent wheels = new Wheels();
CarComponent seats = new Seats();

Car myCar = new Car();
myCar.addComponent(engine);
myCar.addComponent(wheels);
myCar.addComponent(seats);

myCar.assemble();
```

## Benefits
- **Uniform Treatment**: Treats individual objects and compositions uniformly
- **Simplified Client Code**: Clients don't need to distinguish between leaves and composites
- **Easy Extension**: Easy to add new types of components
- **Recursive Structure**: Supports recursive composition

## Use Cases
- **Car Assembly**: Representing car parts and assemblies
- **File Systems**: Representing files and directories
- **GUI Components**: Representing UI elements and containers
- **Organization Charts**: Representing employees and departments

## Example Output
```
Assembling car...
Assembling engine
Assembling wheels
Assembling seats
Car assembly complete!
```

## Related Patterns
- **Decorator**: Can be used together to add functionality to components
- **Visitor**: Can be used to perform operations on composite structures
- **Chain of Responsibility**: Can be used with composite for request handling
- **Iterator**: Can be used to traverse composite structures

## Best Practices
- Keep the component interface simple
- Implement proper child management in composites
- Consider using the Visitor pattern for complex operations
- Be careful with circular references in composites 