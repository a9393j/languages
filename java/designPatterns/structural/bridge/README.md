# Bridge Pattern

## Overview
The Bridge pattern is a structural design pattern that lets you split a large class or a set of closely related classes into abstraction and implementation, which can be developed independently of each other.

## Problem
When you have a class hierarchy that grows in two dimensions (abstraction and implementation), you end up with a cartesian product of classes. For example, if you have:
- Different types of cars (Sedan, SUV, Sports)
- Different types of engines (Gasoline, Electric, Hybrid)

You would need classes like: GasolineSedan, ElectricSedan, HybridSedan, GasolineSUV, ElectricSUV, etc.

## Solution
The Bridge pattern suggests splitting the hierarchy into two parts:
1. **Abstraction**: The interface that clients work with
2. **Implementation**: The interface that defines how the abstraction is implemented

## Structure
```
Abstraction (Car)
    |
    +-- RefinedAbstraction (Sedan, SUV)
            |
            +-- Implementation (Engine)
                    |
                    +-- ConcreteImplementation (GasolineEngine, ElectricEngine)
```

## Benefits
- **Decoupling**: Abstraction and implementation can be developed independently
- **Single Responsibility**: Each class has a single reason to change
- **Open/Closed**: Easy to extend with new abstractions or implementations
- **Hide Implementation**: Clients work only with the abstraction

## When to Use
- When you want to avoid a permanent binding between abstraction and implementation
- When both abstraction and implementation should be extensible by subclassing
- When changes in implementation should not affect client code
- When you want to hide implementation details from clients

## Example
In our car example:
- **Abstraction**: Car types (Sedan, SUV)
- **Implementation**: Engine types (Gasoline, Electric)
- **Bridge**: The relationship between Car and Engine

This allows us to create any combination of car type and engine type without creating a separate class for each combination. 