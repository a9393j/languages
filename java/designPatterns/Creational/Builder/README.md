# Builder Pattern

## Overview
The Builder pattern allows you to construct complex objects step by step. It separates the construction of a complex object from its representation, allowing the same construction process to create different representations.

## Problem
When you need to create complex objects with many optional parameters or when the construction process involves multiple steps that need to be performed in a specific order.

## Solution
Create a builder class that provides a fluent interface for setting object properties and a build method that returns the final object.

## Structure

### Core Components

1. **Car (Product)**
   - The complex object being built
   - Contains all the properties that can be set during construction

2. **CarBuilder (Builder Interface)**
   - Defines the contract for building cars
   - Provides methods for setting each property
   - Includes a `build()` method to create the final object

3. **CarBuilderImpl (Concrete Builder)**
   - Implements the CarBuilder interface
   - Maintains the state of the car being built
   - Returns the builder instance for method chaining

## Implementation Details

### Builder Interface
```java
public interface CarBuilder {
    CarBuilder setMake(String make);
    CarBuilder setModel(String model);
    CarBuilder setColor(String color);
    CarBuilder setYear(int year);
    CarBuilder setIsElectric(boolean isElectric);
    Car build();
}
```

### Fluent Interface Usage
```java
CarBuilder builder = new CarBuilderImpl();
Car car = builder.setMake("Toyota")
                .setModel("Camry")
                .setColor("Black")
                .setYear(2022)
                .setIsElectric(false)
                .build();
```

## Benefits
- **Fluent Interface**: Easy to read and write object construction code
- **Immutability**: Can create immutable objects
- **Validation**: Can add validation logic in the build method
- **Flexibility**: Can create different representations of the same object
- **Readability**: Makes complex object construction more readable

## Use Cases
- **Car Configuration**: Building cars with different specifications
- **Database Queries**: Building complex SQL queries
- **HTTP Requests**: Building API requests with headers and parameters
- **Configuration Objects**: Building application configuration objects

## Example Output
```
Builder
Car{make='Toyota', model='Camry', color='Black', year=2022, isElectric=false}
```

## Related Patterns
- **Factory Method**: For simpler object creation
- **Abstract Factory**: For creating families of related objects
- **Prototype**: For cloning existing objects
- **Singleton**: Often used with builders to ensure single instance

## Best Practices
- Use method chaining for fluent interface
- Implement validation in the build method
- Consider making the product immutable
- Use meaningful method names that clearly indicate what they set 