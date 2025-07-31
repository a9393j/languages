# Singleton Pattern

## Overview
The Singleton pattern ensures that a class has only one instance and provides a global point of access to that instance. It's useful when you need to ensure that only one instance of a class exists throughout the application.

## Problem
When you need to ensure that a class has only one instance, such as a database connection, configuration manager, or logging service.

## Solution
Create a class with a private constructor and a static method that returns the single instance of the class.

## Structure

### Core Components

1. **Singleton Class**
   - Private constructor to prevent direct instantiation
   - Private static volatile instance field
   - Public static `getInstance()` method with thread-safe implementation

2. **Thread Safety**
   - Uses double-checked locking (DCL) pattern
   - Volatile keyword ensures visibility across threads
   - Synchronized block prevents race conditions

## Implementation Details

### Thread-Safe Singleton
```java
public final class Singleton {
    private static volatile Singleton instance;
    public String value;

    private Singleton(String value) {
        this.value = value;
    }

    public static Singleton getInstance(String value) {
        Singleton result = instance;
        if (result != null) {
            return result;
        }
        synchronized (Singleton.class) {
            if (instance == null) {
                instance = new Singleton(value);
            }
            return instance;
        }
    }
}
```

### Usage
```java
Singleton singleton1 = Singleton.getInstance("First");
Singleton singleton2 = Singleton.getInstance("Second");
// singleton1 and singleton2 are the same instance
```

## Benefits
- **Single Instance**: Guarantees only one instance exists
- **Global Access**: Provides a global point of access
- **Lazy Initialization**: Instance is created only when first accessed
- **Thread Safety**: Safe for use in multi-threaded environments

## Use Cases
- **Database Connections**: Managing a single database connection
- **Configuration Managers**: Centralized application configuration
- **Logging Services**: Single logging instance across the application
- **Cache Managers**: Centralized caching mechanism

## Example Output
```
First instance: designPatterns.Creational.Singleton.Singleton@7852e922
Second instance: designPatterns.Creational.Singleton.Singleton@7852e922
Same instance: true
```

## Related Patterns
- **Factory Method**: Can be used to create singleton instances
- **Abstract Factory**: Can return singleton instances
- **Builder**: Can be used to configure singleton instances
- **Prototype**: Alternative to singleton for object reuse

## Best Practices
- Use volatile keyword for thread safety
- Implement double-checked locking for performance
- Consider using enum for simpler singleton implementation
- Be careful with serialization and reflection
- Consider if singleton is really needed (can lead to tight coupling) 