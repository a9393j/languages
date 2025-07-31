# Facade Pattern

## Overview
The Facade pattern provides a simplified interface to a complex subsystem of classes, making it easier to use and understand. It acts as a single entry point to a complex system.

## Problem
When you have a complex subsystem with many classes and dependencies, and you want to provide a simple interface for clients to interact with the system.

## Solution
Create a facade class that provides a simplified interface to the complex subsystem, hiding the complexity and coordinating the interactions between subsystem classes.

## Structure

### Core Components

1. **CarFacade (Facade)**
   - Provides a simplified interface to the car system
   - Coordinates the interactions between subsystem classes
   - Contains `startCar()` and `stopCar()` methods

2. **Subsystem Classes**
   - `Engine`: Handles engine operations
   - `FuelInjector`: Manages fuel injection
   - `AirConditioner`: Controls air conditioning

3. **Client**
   - Uses the facade to interact with the car system
   - Doesn't need to know about the complex subsystem

## Implementation Details

### Facade Class
```java
public class CarFacade {
    private Engine engine;
    private FuelInjector fuelInjector;
    private AirConditioner airConditioner;
    
    public CarFacade() {
        this.engine = new Engine();
        this.fuelInjector = new FuelInjector();
        this.airConditioner = new AirConditioner();
    }
    
    public void startCar() {
        fuelInjector.start();
        engine.start();
        airConditioner.start();
    }
    
    public void stopCar() {
        airConditioner.stop();
        engine.stop();
        fuelInjector.stop();
    }
}
```

### Subsystem Classes
```java
public class Engine {
    public void start() {
        System.out.println("Engine started");
    }
    
    public void stop() {
        System.out.println("Engine stopped");
    }
}
```

### Client Usage
```java
CarFacade carFacade = new CarFacade();
carFacade.startCar();
carFacade.stopCar();
```

## Benefits
- **Simplified Interface**: Provides a simple interface to a complex system
- **Loose Coupling**: Reduces dependencies between clients and subsystem
- **Single Responsibility**: Facade has a single responsibility
- **Easier Maintenance**: Changes to subsystem don't affect clients

## Use Cases
- **Car Systems**: Simplifying complex car operations
- **Database Operations**: Providing simple interface to complex database operations
- **API Wrappers**: Simplifying third-party API interactions
- **Legacy System Integration**: Providing modern interface to old systems

## Example Output
```
Fuel injector started
Engine started
Air conditioner started
-------------------
Air conditioner stopped
Engine stopped
Fuel injector stopped
```

## Related Patterns
- **Adapter**: Can be used together to adapt and simplify
- **Mediator**: Similar but focuses on communication between objects
- **Command**: Can be used to encapsulate facade operations
- **Singleton**: Often used with facade to ensure single instance

## Best Practices
- Keep facade focused on simplifying the interface
- Don't expose subsystem classes to clients
- Consider using dependency injection for subsystem classes
- Document the facade interface clearly 