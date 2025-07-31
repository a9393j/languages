# Flyweight Pattern

## Overview
The Flyweight pattern reduces memory usage by sharing common parts of state between multiple objects instead of keeping all of the data in each object. It's useful when you have a large number of objects that share similar state.

## Problem
When you have a large number of objects that share common state, and creating all these objects consumes too much memory.

## Solution
Extract the shared state into a separate flyweight object and share it among multiple objects. Each object maintains only its unique state.

## Structure

### Core Components

1. **CarColor (Flyweight)**
   - Contains the shared state (color information)
   - Immutable object that can be shared

2. **CarColorFactory (Flyweight Factory)**
   - Manages the creation and sharing of CarColor objects
   - Ensures that only one instance of each color exists

3. **Car (Context)**
   - Contains the unique state (car-specific information)
   - References a shared CarColor object

## Implementation Details

### Flyweight Class
```java
public class CarColor {
    private String color;
    
    public CarColor(String color) {
        this.color = color;
    }
    
    public String getColor() {
        return color;
    }
}
```

### Flyweight Factory
```java
public class CarColorFactory {
    private Map<String, CarColor> colorCache = new HashMap<>();
    
    public CarColor getCarColor(String color) {
        if (!colorCache.containsKey(color)) {
            colorCache.put(color, new CarColor(color));
        }
        return colorCache.get(color);
    }
}
```

### Context Class
```java
public class Car {
    public CarColor carColor;
    
    public Car(CarColor carColor) {
        this.carColor = carColor;
    }
    
    public void drive() {
        System.out.println("Driving a " + carColor.getColor() + " car.");
    }
}
```

### Client Usage
```java
CarColorFactory factory = new CarColorFactory();
Car car1 = new Car(factory.getCarColor("Red"));
Car car2 = new Car(factory.getCarColor("Blue"));
Car car3 = new Car(factory.getCarColor("Red")); // Shares the same color object
```

## Benefits
- **Memory Efficiency**: Reduces memory usage by sharing common state
- **Performance**: Faster object creation due to sharing
- **Scalability**: Can handle large numbers of objects efficiently
- **Immutability**: Flyweight objects are typically immutable

## Use Cases
- **Car Manufacturing**: Sharing color information across multiple cars
- **Text Editors**: Sharing font and formatting information
- **Game Development**: Sharing texture and material information
- **GUI Applications**: Sharing common UI element properties

## Example Output
```
Driving a Red car.
Driving a Blue car.
Driving a Red car.
true
```

## Related Patterns
- **Factory Method**: Often used to create flyweight objects
- **Singleton**: Can be used to ensure single flyweight instances
- **Composite**: Can be used together for complex object structures
- **Object Pool**: Similar concept but for object reuse

## Best Practices
- Make flyweight objects immutable
- Use a factory to manage flyweight creation
- Separate intrinsic (shared) and extrinsic (unique) state
- Consider thread safety for shared flyweights 