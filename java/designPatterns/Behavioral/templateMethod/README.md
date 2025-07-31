# Template Method Pattern

## Overview
The Template Method pattern is a behavioral design pattern that defines the skeleton of an algorithm in a method, deferring some steps to subclasses. Template Method lets subclasses redefine certain steps of an algorithm without changing the algorithm's structure.

## Problem
When you need to:
- Define the overall structure of an algorithm
- Allow subclasses to override specific steps
- Avoid code duplication in similar algorithms
- Ensure consistent algorithm structure across subclasses
- Control the order of algorithm steps

## Solution
The Template Method pattern suggests breaking down an algorithm into a series of steps, turning these steps into methods, and putting a series of calls to these methods inside a single "template method." The steps may either be abstract or have some default implementation.

## Structure
```
AbstractClass (CarManufacturingProcess)
    |
    +-- ConcreteClass (SedanManufacturing, SUVManufacturing)
```

## Components
- **AbstractClass**: Defines abstract primitive operations that concrete subclasses must implement
- **Template Method**: Defines the skeleton of an algorithm, deferring some steps to subclasses
- **ConcreteClass**: Implements the primitive operations to carry out subclass-specific steps

## Benefits
- **Code Reuse**: Common algorithm structure is defined once in the abstract class
- **Consistency**: Ensures consistent algorithm structure across subclasses
- **Flexibility**: Subclasses can override specific steps without changing the overall structure
- **Control**: The abstract class controls the algorithm flow
- **Extensibility**: Easy to add new concrete classes

## When to Use
- When you have an algorithm with a fixed structure but variable steps
- When you want to avoid code duplication in similar algorithms
- When you want to control the order of algorithm steps
- When you want to allow subclasses to override specific steps

## Example
In our car manufacturing example:
- **AbstractClass**: CarManufacturingProcess
- **Template Method**: manufactureCar()
- **ConcreteClass**: SedanManufacturing, SUVManufacturing

This allows different car types to follow the same manufacturing process but customize specific steps like body assembly, interior installation, etc. 