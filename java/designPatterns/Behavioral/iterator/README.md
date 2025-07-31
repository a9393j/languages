# Iterator Pattern

## Overview
The Iterator pattern is a behavioral design pattern that lets you access elements of a collection without exposing its underlying representation (list, stack, tree, etc.). It provides a way to access the elements of an aggregate object sequentially without exposing its underlying representation.

## Problem
When you need to:
- Access the contents of a collection without knowing its internal structure
- Support multiple traversal methods for a collection
- Provide a uniform interface for traversing different collection types
- Allow multiple iterations over the same collection simultaneously

## Solution
The Iterator pattern suggests extracting the traversal behavior of a collection into a separate object called an iterator. The iterator encapsulates the details of accessing and traversing the collection.

## Structure
```
Client
    |
    +-- Aggregate (CarCollection)
            |
            +-- Iterator (CarIterator)
                    |
                    +-- ConcreteIterator (CarListIterator)
                            |
                            +-- ConcreteAggregate (CarList)
```

## Components
- **Iterator**: Defines an interface for accessing and traversing elements
- **ConcreteIterator**: Implements the Iterator interface and keeps track of the current position
- **Aggregate**: Defines an interface for creating an Iterator object
- **ConcreteAggregate**: Implements the Aggregate interface and returns an instance of ConcreteIterator

## Benefits
- **Single Responsibility**: Separates traversal logic from collection logic
- **Open/Closed**: Easy to add new traversal methods without changing collection code
- **Multiple Iterations**: Supports multiple simultaneous iterations over the same collection
- **Uniform Interface**: Provides a consistent way to traverse different collection types
- **Encapsulation**: Hides the internal structure of the collection

## When to Use
- When you want to access a collection's contents without exposing its internal structure
- When you want to support multiple traversal methods for a collection
- When you want to provide a uniform interface for traversing different collection types
- When you want to allow multiple iterations over the same collection simultaneously

## Example
In our car example:
- **Aggregate**: CarCollection interface
- **ConcreteAggregate**: CarList class
- **Iterator**: CarIterator interface
- **ConcreteIterator**: CarListIterator class

This allows us to iterate over a collection of cars without knowing how the cars are stored internally. 