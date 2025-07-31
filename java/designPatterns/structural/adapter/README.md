# Adapter Pattern

## Overview
The Adapter pattern allows incompatible interfaces to work together. It acts as a bridge between two incompatible interfaces by wrapping an existing class with a new interface.

## Problem
When you have an existing class (LegacyCar) with an interface that doesn't match the interface expected by your client code (ModernCar), and you can't modify the existing class.

## Solution
Create an adapter class that implements the target interface and delegates calls to the existing class, translating between the two interfaces.

## Structure

### Core Components

1. **ModernCar (Target Interface)**
   - The interface that the client expects
   - Defines the `turnOn()` method

2. **LegacyCar (Adaptee)**
   - The existing class with an incompatible interface
   - Has a `startEngine()` method instead of `turnOn()`

3. **CarAdapter (Adapter)**
   - Implements the ModernCar interface
   - Contains a reference to LegacyCar
   - Translates calls from ModernCar interface to LegacyCar interface

## Implementation Details

### Target Interface
```java
public interface ModernCar {
    void turnOn();
}
```

### Adaptee Class
```java
public class LegacyCar {
    public void startEngine() {
        System.out.println("Starting the car's engine.");
    }
}
```

### Adapter Implementation
```java
public class CarAdapter implements ModernCar {
    private LegacyCar legacyCar;
    
    public CarAdapter(LegacyCar legacyCar) {
        this.legacyCar = legacyCar;
    }
    
    @Override
    public void turnOn() {
        legacyCar.startEngine();
    }
}
```

### Client Usage
```java
LegacyCar oldCar = new LegacyCar();
ModernCar adaptedCar = new CarAdapter(oldCar);
adaptedCar.turnOn();
```

## Benefits
- **Compatibility**: Allows incompatible interfaces to work together
- **Reusability**: Can reuse existing classes without modification
- **Single Responsibility**: Adapter has a single purpose
- **Open/Closed Principle**: Can add new adapters without modifying existing code

## Use Cases
- **Legacy System Integration**: Integrating old systems with new interfaces
- **Third-Party Libraries**: Adapting third-party library interfaces
- **API Compatibility**: Making different APIs work together
- **Database Adapters**: Adapting different database interfaces

## Example Output
```
Starting the car's engine.
```

## Related Patterns
- **Bridge**: Similar structure but focuses on abstraction and implementation
- **Decorator**: Can be used to add functionality while adapting
- **Facade**: Provides a simplified interface to a complex subsystem
- **Proxy**: Can be used together with adapter for additional functionality

## Best Practices
- Keep adapters simple and focused
- Consider using composition over inheritance
- Document the translation between interfaces
- Test adapters thoroughly to ensure correct translation 