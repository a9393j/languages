# Visitor Pattern

## Overview
The Visitor pattern is a behavioral design pattern that lets you separate algorithms from the objects on which they operate. It allows you to define a new operation without changing the classes of the elements on which it operates.

## Problem
When you need to:
- Add new operations to existing object structures without modifying them
- Separate algorithms from the objects they operate on
- Perform operations on a collection of different object types
- Avoid polluting classes with operations that don't belong to them
- Support double dispatch (operation depends on both visitor and element types)

## Solution
The Visitor pattern suggests placing the new operation into a separate class called a visitor, instead of trying to integrate it into existing classes. The original object that had the data is passed to one of the visitor's methods as an argument.

## Structure
```
Client
    |
    +-- Visitor (CarVisitor)
            |
            +-- ConcreteVisitor (MaintenanceVisitor, InspectionVisitor)
                    |
                    +-- Element (CarComponent)
                            |
                            +-- ConcreteElement (Engine, Wheels, Brakes)
```

## Components
- **Visitor**: Declares a visit operation for each concrete element class
- **ConcreteVisitor**: Implements each operation declared by Visitor
- **Element**: Defines an accept operation that takes a visitor as an argument
- **ConcreteElement**: Implements the accept operation

## Benefits
- **Open/Closed**: Easy to add new operations without changing element classes
- **Single Responsibility**: Operations are separated from element classes
- **Double Dispatch**: Operation depends on both visitor and element types
- **Extensibility**: New visitors can be added without changing existing code
- **Algorithm Separation**: Algorithms are separated from data structures

## When to Use
- When you need to perform operations on a collection of different object types
- When you want to add new operations without modifying existing classes
- When you need to separate algorithms from the objects they operate on
- When you want to support double dispatch

## Example
In our car maintenance example:
- **Visitor**: CarVisitor interface
- **ConcreteVisitor**: MaintenanceVisitor, InspectionVisitor
- **Element**: CarComponent interface
- **ConcreteElement**: Engine, Wheels, Brakes

This allows different visitors (maintenance, inspection) to perform operations on car components without modifying the component classes. 