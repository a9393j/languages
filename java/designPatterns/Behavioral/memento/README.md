# Memento Pattern

## Overview
The Memento pattern is a behavioral design pattern that lets you save and restore the previous state of an object without revealing the details of its implementation. It provides the ability to restore an object to its previous state (undo via rollback).

## Problem
When you need to:
- Save and restore the state of an object
- Implement undo/redo functionality
- Provide checkpoint and rollback capabilities
- Maintain object state history
- Avoid exposing internal object state

## Solution
The Memento pattern suggests creating a memento object that stores the state of the originator object. The originator can save its state to a memento and restore it later. The caretaker manages the mementos but doesn't modify them.

## Structure
```
Originator (Car)
    |
    +-- Memento (CarMemento)
            |
            +-- Caretaker (CarHistory)
```

## Components
- **Originator**: The object whose state needs to be saved and restored
- **Memento**: Stores the internal state of the Originator
- **Caretaker**: Manages and stores the mementos, but never examines or modifies their contents

## Benefits
- **State Preservation**: Can save and restore object state
- **Encapsulation**: Internal state is not exposed
- **Undo/Redo**: Easy to implement undo and redo functionality
- **Checkpointing**: Can create checkpoints for rollback
- **History Management**: Can maintain a history of states

## When to Use
- When you need to save and restore object state
- When you want to implement undo/redo functionality
- When you need checkpoint and rollback capabilities
- When you want to avoid exposing internal object state

## Example
In our car example:
- **Originator**: Car class that can save and restore its state
- **Memento**: CarMemento that stores car state
- **Caretaker**: CarHistory that manages the mementos

This allows the car to save its state at different points and restore to any previous state. 