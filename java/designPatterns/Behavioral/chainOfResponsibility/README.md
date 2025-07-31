# Chain of Responsibility Pattern

## Overview
The Chain of Responsibility pattern allows you to pass requests along a chain of handlers. Each handler decides either to process the request or to pass it to the next handler in the chain.

## Problem
When you need to process a request through multiple handlers in a specific order, and each handler performs a specific task before passing the request to the next handler.

## Solution
Create a chain of handler objects where each handler has a reference to the next handler. Each handler processes the request and optionally passes it to the next handler in the chain.

## Structure

### Core Components

1. **Handler (Abstract Class)**
   - Base class for all handlers in the chain
   - Contains `next` reference to the next handler
   - Provides `setNext()` method to build the chain
   - Defines abstract `handle()` method

2. **Concrete Handlers**
   - `EngineInstallation`: Installs the engine
   - `WheelsInstallation`: Installs the wheels
   - `PaintApplication`: Applies paint to the car

3. **Car (Request Object)**
   - Represents the car being manufactured
   - Contains status flags for different components

## Implementation Details

### Handler Chain Setup
```java
Handler engineInstallation = new EngineInstallation();
Handler wheelsInstallation = new WheelsInstallation();
Handler paintApplication = new PaintApplication();

engineInstallation.setNext(wheelsInstallation).setNext(paintApplication);
```

### Request Processing
```java
engineInstallation.handle(car);
```

## Benefits
- **Decoupling**: Senders don't need to know which handler will process their request
- **Flexibility**: Easy to add or remove handlers from the chain
- **Single Responsibility**: Each handler has a specific responsibility
- **Order Control**: Requests are processed in a specific sequence

## Use Cases
- **Car Manufacturing**: Sequential assembly line processes
- **Request Processing**: Web request filters and middleware
- **Event Handling**: GUI event propagation
- **Logging**: Different log levels handled by different handlers

## Example Output
```
Installing engine.
Installing wheels.
Applying paint.
Car manufacturing completed!
```

## Related Patterns
- **Command Pattern**: Can be used together where commands are processed through a chain
- **Decorator Pattern**: Similar structure but focuses on adding behavior rather than processing
- **Pipeline Pattern**: Similar concept in data processing 