# Observer Pattern

## Overview
The Observer pattern is a behavioral design pattern that lets you define a subscription mechanism to notify multiple objects about any events that happen to the object they're observing. It establishes a one-to-many dependency between objects so that when one object changes state, all its dependents are notified and updated automatically.

## Problem
When you need to:
- Notify multiple objects about state changes in another object
- Avoid tight coupling between the subject and its observers
- Support dynamic subscription and unsubscription
- Implement event handling systems
- Create a publish-subscribe mechanism

## Solution
The Observer pattern suggests creating a subject that maintains a list of observers and notifies them of any state changes. Observers can subscribe to or unsubscribe from the subject.

## Structure
```
Subject (CarDealer)
    |
    +-- Observer (CarBuyer)
            |
            +-- ConcreteObserver (IndividualBuyer, DealerBuyer)
```

## Components
- **Subject**: Maintains a list of observers and notifies them of state changes
- **Observer**: Defines an interface for objects that should be notified of changes
- **ConcreteSubject**: Implements the Subject interface and manages observer list
- **ConcreteObserver**: Implements the Observer interface and responds to notifications

## Benefits
- **Loose Coupling**: Subject and observers are loosely coupled
- **Extensibility**: Easy to add new observers without changing the subject
- **Dynamic Relationships**: Observers can subscribe/unsubscribe at runtime
- **Event Handling**: Natural fit for event-driven architectures
- **One-to-Many**: One subject can notify many observers

## When to Use
- When you need to notify multiple objects about state changes
- When you want to avoid tight coupling between objects
- When you need to implement event handling systems
- When you want to support dynamic subscription/unsubscription
- When you need a publish-subscribe mechanism

## Example
In our car dealer example:
- **Subject**: CarDealer that manages car inventory
- **Observer**: CarBuyer interface
- **ConcreteObserver**: IndividualBuyer, DealerBuyer
- **Event**: New car arrival, price change, car sold

This allows the car dealer to notify all interested buyers when new cars arrive or prices change. 