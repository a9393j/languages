# Command Pattern

## Overview
The Command pattern is a behavioral design pattern that turns a request into a stand-alone object containing all information about the request. This transformation lets you parameterize methods with different requests, delay or queue a request's execution, and support undoable operations.

## Problem
When you need to:
- Parameterize objects with operations
- Queue operations
- Support undo operations
- Support logging operations
- Structure a system around high-level operations built on primitive operations

## Solution
The Command pattern suggests creating a separate command object for each request. This object contains all the information about the request and can be executed later.

## Structure
```
Client
    |
    +-- Invoker (RemoteControl)
            |
            +-- Command (CarCommand)
                    |
                    +-- ConcreteCommand (StartCarCommand, StopCarCommand)
                            |
                            +-- Receiver (Car)
```

## Components
- **Command**: Declares an interface for executing an operation
- **ConcreteCommand**: Implements the execute method by calling the corresponding operation(s) on the receiver
- **Client**: Creates a ConcreteCommand object and sets its receiver
- **Invoker**: Asks the command to carry out the request
- **Receiver**: Knows how to perform the operations associated with carrying out a request

## Benefits
- **Decoupling**: Invoker doesn't need to know how the command is implemented
- **Extensibility**: Easy to add new commands without changing existing code
- **Composability**: Commands can be composed into more complex commands
- **Undo/Redo**: Easy to implement undo functionality
- **Logging**: Can log all commands for debugging or auditing

## When to Use
- When you want to parameterize objects with operations
- When you want to queue operations, schedule their execution, or execute them remotely
- When you want to support undo operations
- When you want to support logging of operations
- When you want to structure a system around high-level operations

## Example
In our car example:
- **Command**: CarCommand interface
- **ConcreteCommand**: StartCarCommand, StopCarCommand, etc.
- **Receiver**: Car class that performs the actual operations
- **Invoker**: RemoteControl that triggers the commands

This allows us to create a remote control that can operate any car without knowing the car's specific implementation. 