# Strategy Pattern

## Overview
The Strategy pattern is a behavioral design pattern that lets you define a family of algorithms, encapsulate each one, and make them interchangeable. Strategy lets the algorithm vary independently from clients that use it.

## Problem
When you need to:
- Define a family of similar algorithms
- Make algorithms interchangeable at runtime
- Avoid multiple conditional statements
- Encapsulate algorithm implementation details
- Support different variations of the same behavior

## Solution
The Strategy pattern suggests creating a family of algorithm classes, each implementing a common interface. The context class uses a strategy object to call the algorithm, and the strategy can be changed at runtime.

## Structure
```
Context (Car)
    |
    +-- Strategy (DrivingStrategy)
            |
            +-- ConcreteStrategy (NormalDriving, SportDriving, EcoDriving)
```

## Components
- **Context**: Maintains a reference to a strategy object and delegates work to it
- **Strategy**: Defines an interface common to all supported algorithms
- **ConcreteStrategy**: Implements the algorithm using the Strategy interface

## Benefits
- **Algorithm Family**: Defines a family of related algorithms
- **Runtime Selection**: Algorithms can be selected at runtime
- **Eliminates Conditionals**: Replaces conditional statements with strategy objects
- **Open/Closed**: Easy to add new strategies without changing existing code
- **Encapsulation**: Algorithm implementation details are hidden from clients

## When to Use
- When you have a family of similar algorithms that differ only in their behavior
- When you want to avoid multiple conditional statements for algorithm selection
- When you want to make algorithms interchangeable at runtime
- When you want to encapsulate algorithm implementation details

## Example
In our car example:
- **Context**: Car class that can drive using different strategies
- **Strategy**: DrivingStrategy interface
- **ConcreteStrategy**: NormalDriving, SportDriving, EcoDriving

This allows the car to switch between different driving modes (normal, sport, eco) at runtime without changing the car's implementation. 