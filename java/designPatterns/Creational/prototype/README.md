# Prototype Pattern

## Overview
The Prototype pattern allows you to create new objects by cloning an existing object, known as the prototype, instead of creating new instances from scratch. This is useful when object creation is expensive or when you want to avoid subclassing.

## Problem
When creating objects is expensive (e.g., requires database queries, network calls, or complex initialization) or when you want to avoid the overhead of creating objects through constructors.

## Solution
Create a prototype interface that defines a `clone()` method, and implement this method in concrete classes to create copies of existing objects.

## Structure

### Core Components

1. **Car (Prototype Interface)**
   - Defines the `clone()` method
   - Common interface for all car types

2. **Concrete Prototypes**
   - `Sedan`: Concrete implementation with sedan-specific properties
   - `SUV`: Concrete implementation with SUV-specific properties

3. **Client**
   - Uses prototypes to create new objects by cloning

## Implementation Details

### Prototype Interface
```java
public abstract class Car implements Cloneable {
    protected String type;
    
    public abstract Car clone();
    public abstract void drive();
}
```

### Concrete Prototype Implementation
```java
public class Sedan extends Car {
    public Sedan(String type) {
        this.type = type;
    }
    
    @Override
    public Car clone() {
        return new Sedan(this.type);
    }
    
    @Override
    public void drive() {
        System.out.println("Driving a " + type + " sedan.");
    }
}
```

### Client Usage
```java
Car sedanPrototype = new Sedan("Luxury");
Car sedanClone = sedanPrototype.clone();
sedanClone.drive();
```

## Benefits
- **Performance**: Avoids expensive object creation
- **Flexibility**: Can create objects without knowing their exact classes
- **Reduced Subclassing**: No need to create subclasses for different object configurations
- **Dynamic Object Creation**: Can create objects at runtime based on prototypes

## Use Cases
- **Car Manufacturing**: Creating cars with similar configurations
- **Game Development**: Creating game objects with similar properties
- **Database Operations**: Creating objects from database records
- **Configuration Objects**: Creating objects with similar settings

## Example Output
```
Driving a Luxury sedan.
Driving a Luxury sedan.
Driving a Sport SUV.
Driving a Sport SUV.
```

## Related Patterns
- **Factory Method**: Alternative for object creation
- **Abstract Factory**: For creating families of related objects
- **Builder**: For complex object construction
- **Singleton**: Can be used with prototypes for object reuse

## Best Practices
- Implement proper cloning logic (shallow vs deep copy)
- Consider using copy constructors as an alternative
- Be careful with mutable objects in prototypes
- Use prototypes when object creation is expensive 