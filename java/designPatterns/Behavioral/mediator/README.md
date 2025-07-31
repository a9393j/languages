# Mediator Pattern

## Overview
The Mediator pattern is a behavioral design pattern that lets you reduce chaotic dependencies between objects. The pattern restricts direct communications between the objects and forces them to collaborate only via a mediator object.

## Problem
When you need to:
- Reduce coupling between components
- Centralize complex communications between objects
- Make the system easier to understand and maintain
- Avoid direct dependencies between components
- Simplify object interactions

## Solution
The Mediator pattern suggests creating a mediator object that encapsulates how a set of objects interact. Instead of objects communicating directly with each other, they communicate through the mediator.

## Structure
```
Colleague (CarComponent)
    |
    +-- ConcreteColleague (Engine, Transmission, Brakes)
            |
            +-- Mediator (CarMediator)
                    |
                    +-- ConcreteMediator (CarControlSystem)
```

## Components
- **Mediator**: Defines an interface for communicating with Colleague objects
- **ConcreteMediator**: Implements cooperative behavior by coordinating Colleague objects
- **Colleague**: Defines an interface for communicating with the Mediator
- **ConcreteColleague**: Implements the Colleague interface and communicates with the Mediator

## Benefits
- **Loose Coupling**: Components don't need to know about each other
- **Centralized Control**: All interactions go through the mediator
- **Simplified Communication**: Complex interactions are simplified
- **Easier Maintenance**: Changes to communication logic are centralized
- **Reusability**: Components can be reused in different contexts

## When to Use
- When you have complex interactions between objects
- When you want to reduce coupling between components
- When you need to centralize communication logic
- When you want to make the system easier to understand and maintain

## Example
In our car control system example:
- **Mediator**: CarMediator interface
- **ConcreteMediator**: CarControlSystem
- **Colleague**: CarComponent interface
- **ConcreteColleague**: Engine, Transmission, Brakes

This allows car components to communicate through a central control system without direct dependencies. 