# Design Patterns in Java

## Overview
This project demonstrates the implementation of all 23 Gang of Four (GoF) design patterns in Java, using car-related examples to make the concepts more relatable and easier to understand.

## Project Structure

```
java/designPatterns/
├── Behavioral/           # Behavioral Design Patterns
│   ├── chainOfResponsibility/
│   ├── command/
│   ├── interpreter/
│   ├── iterator/
│   ├── mediator/
│   ├── memento/
│   ├── observer/
│   ├── state/
│   ├── strategy/
│   ├── templateMethod/
│   └── visitor/
├── Creational/           # Creational Design Patterns
│   ├── AbstractFactory/
│   ├── Builder/
│   ├── factory/
│   ├── prototype/
│   └── Singleton/
└── structural/           # Structural Design Patterns
    ├── adapter/
    ├── bridge/
    ├── composite/
    ├── decorator/
    ├── facade/
    ├── flyweight/
    └── proxy/
```

## Design Pattern Categories

### 1. **Creational Patterns** (5 patterns)
Focus on object creation mechanisms, making systems independent of how objects are created.

- **Abstract Factory**: Create families of related objects
- **Builder**: Construct complex objects step by step
- **Factory Method**: Create objects without specifying exact classes
- **Prototype**: Create objects by cloning existing ones
- **Singleton**: Ensure only one instance exists

### 2. **Structural Patterns** (7 patterns)
Focus on object composition and relationships between entities.

- **Adapter**: Make incompatible interfaces work together
- **Bridge**: Decouple abstraction from implementation
- **Composite**: Compose objects into tree structures
- **Decorator**: Add functionality dynamically
- **Facade**: Provide simplified interface to complex subsystem
- **Flyweight**: Share common state to reduce memory usage
- **Proxy**: Control access to objects

### 3. **Behavioral Patterns** (11 patterns)
Focus on communication between objects and how they distribute responsibility.

- **Chain of Responsibility**: Pass requests along handler chain
- **Command**: Encapsulate requests as objects
- **Interpreter**: Define grammar and interpret sentences
- **Iterator**: Access collection elements without exposing structure
- **Mediator**: Encapsulate how objects interact
- **Memento**: Capture and restore object state
- **Observer**: Define one-to-many dependency between objects
- **State**: Alter behavior when internal state changes
- **Strategy**: Define family of interchangeable algorithms
- **Template Method**: Define algorithm skeleton in method
- **Visitor**: Separate algorithm from object structure

## Getting Started

### Prerequisites
- Java 8 or higher
- Any Java IDE (IntelliJ IDEA, Eclipse, VS Code)

### Running the Examples
1. Navigate to any pattern directory
2. Compile the Java files: `javac *.java`
3. Run the main class: `java Main` or `java Demo*`

### Example Usage
```bash
# Run Chain of Responsibility pattern
cd java/designPatterns/Behavioral/chainOfResponsibility
javac *.java
java Main

# Run Factory pattern
cd java/designPatterns/Creational/factory
javac *.java
java DemoFactory
```

## Learning Path

### For Beginners
1. Start with **Creational Patterns**:
   - Factory Method (simplest)
   - Singleton
   - Builder
   - Abstract Factory
   - Prototype

2. Move to **Structural Patterns**:
   - Adapter
   - Decorator
   - Facade
   - Composite
   - Bridge
   - Proxy
   - Flyweight

3. Finally, **Behavioral Patterns**:
   - Observer
   - Strategy
   - Command
   - State
   - Template Method
   - Iterator
   - Mediator
   - Chain of Responsibility
   - Memento
   - Interpreter
   - Visitor

### For Intermediate Developers
- Focus on understanding when to use each pattern
- Study the relationships between patterns
- Practice implementing patterns in real scenarios
- Learn about pattern combinations

### For Advanced Developers
- Study pattern variations and extensions
- Understand performance implications
- Learn about anti-patterns
- Explore modern alternatives to traditional patterns

## Key Concepts Demonstrated

### Car Manufacturing Theme
All patterns use car-related examples to demonstrate:
- **Car Creation**: Factory, Builder, Prototype patterns
- **Car Features**: Decorator, Adapter patterns
- **Car Operations**: Command, Strategy, State patterns
- **Car Systems**: Facade, Mediator, Observer patterns
- **Car Components**: Composite, Flyweight patterns

### Real-World Applications
Each pattern includes:
- **Problem Statement**: What the pattern solves
- **Solution**: How the pattern addresses the problem
- **Implementation**: Complete working code
- **Benefits**: Advantages of using the pattern
- **Use Cases**: Real-world scenarios
- **Related Patterns**: Connections to other patterns

## Best Practices

### When to Use Design Patterns
- **Don't over-engineer**: Use patterns only when they solve real problems
- **Understand the problem first**: Choose patterns based on requirements
- **Keep it simple**: Prefer simple solutions over complex patterns
- **Document your intent**: Make pattern usage clear in code

### Common Pitfalls
- **Pattern overuse**: Don't force patterns where they're not needed
- **Premature optimization**: Don't optimize before measuring
- **Complexity**: Some patterns can add unnecessary complexity
- **Learning curve**: Patterns require time to understand and implement correctly

## Contributing

### Adding New Patterns
1. Create a new directory for the pattern
2. Implement the pattern with car-related examples
3. Add comprehensive documentation
4. Include working code examples
5. Update this README if needed

### Improving Existing Patterns
1. Enhance documentation
2. Add more examples
3. Improve code quality
4. Add unit tests
5. Provide better explanations

## Resources

### Books
- **Design Patterns: Elements of Reusable Object-Oriented Software** (Gang of Four)
- **Head First Design Patterns** by Freeman and Robson
- **Refactoring to Patterns** by Joshua Kerievsky

### Online Resources
- [Refactoring Guru](https://refactoring.guru/design-patterns)
- [SourceMaking](https://sourcemaking.com/design_patterns)
- [Java Design Patterns](https://java-design-patterns.com/)

### Tools
- **IntelliJ IDEA**: Excellent support for design patterns
- **Eclipse**: Good refactoring tools
- **VS Code**: Lightweight option with Java extensions

## License
This project is for educational purposes. Feel free to use and modify the code for learning and practice.

## Acknowledgments
- Gang of Four for the original design patterns
- The Java community for continuous improvements
- All contributors who help improve this resource 