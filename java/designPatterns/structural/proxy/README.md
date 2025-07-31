# Proxy Pattern

## Overview
The Proxy pattern provides a surrogate or placeholder for another object to control access to it. It can be used for lazy initialization, logging, access control, caching, and other cross-cutting concerns.

## Problem
When you need to control access to an object, add functionality before or after accessing the object, or defer the creation of expensive objects until they are actually needed.

## Solution
Create a proxy class that implements the same interface as the real object and controls access to it. The proxy can add behavior before and/or after delegating to the real object.

## Structure

### Core Components

1. **Car (Real Subject)**
   - The real object that the proxy represents
   - Contains the actual business logic
   - Has a `drive()` method

2. **CarProxy (Proxy)**
   - Implements the same interface as Car
   - Controls access to the real Car object
   - Implements lazy initialization

3. **Client**
   - Interacts with the proxy instead of the real object
   - Doesn't know it's working with a proxy

## Implementation Details

### Real Subject
```java
public class Car {
    private String type;
    
    public Car(String type) {
        this.type = type;
        System.out.println("Creating a " + type + " car...");
    }
    
    public void drive() {
        System.out.println("Driving the " + type + " car!");
    }
}
```

### Proxy Implementation
```java
public class CarProxy {
    private Car realCar;
    private String carType;
    
    public CarProxy(String carType) {
        this.carType = carType;
    }
    
    public void drive() {
        if (realCar == null) {
            realCar = new Car(carType);
        }
        realCar.drive();
    }
}
```

### Client Usage
```java
CarProxy carProxy = new CarProxy("Sedan");
System.out.println("Car has not actually been created yet...");
carProxy.drive(); // This will create the real car
```

## Benefits
- **Lazy Initialization**: Defer expensive object creation until needed
- **Access Control**: Control who can access the real object
- **Logging**: Add logging before/after method calls
- **Caching**: Cache results of expensive operations
- **Remote Access**: Access objects in different address spaces

## Use Cases
- **Lazy Loading**: Defer loading of large objects
- **Access Control**: Control access to sensitive objects
- **Caching**: Cache expensive operations
- **Remote Objects**: Access objects in different processes
- **Logging**: Add logging to method calls

## Example Output
```
Car has not actually been created yet...

Trying to drive the car...
Creating a Sedan car...
Driving the Sedan car!
```

## Related Patterns
- **Adapter**: Similar structure but focuses on interface compatibility
- **Decorator**: Similar structure but focuses on adding functionality
- **Facade**: Can be used together to provide simplified access
- **Chain of Responsibility**: Can be used with proxy for access control

## Best Practices
- Keep proxy focused on access control or cross-cutting concerns
- Don't add business logic to proxies
- Consider using dynamic proxies for simple cases
- Be careful with method signatures in proxies 