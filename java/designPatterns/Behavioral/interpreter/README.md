# Interpreter Pattern

## Overview
The Interpreter pattern is a behavioral design pattern that defines a grammatical representation for a language and provides an interpreter to deal with this grammar. It is used to evaluate sentences in a language.

## Problem
When you need to:
- Interpret a language or expression
- Parse and evaluate expressions
- Define a grammar for a language
- Create a domain-specific language (DSL)
- Handle complex parsing logic

## Solution
The Interpreter pattern suggests creating a class hierarchy for expressions, where each class represents a different type of expression. The pattern uses a recursive structure to represent the grammar.

## Structure
```
AbstractExpression (CarExpression)
    |
    +-- TerminalExpression (CarCommand, CarValue)
            |
            +-- NonTerminalExpression (CarOperation, CarCondition)
                    |
                    +-- Context (CarContext)
```

## Components
- **AbstractExpression**: Declares an interpret operation
- **TerminalExpression**: Implements interpret for terminal symbols
- **NonTerminalExpression**: Implements interpret for non-terminal symbols
- **Context**: Contains information that's global to the interpreter

## Benefits
- **Grammar Definition**: Easy to define and modify grammar
- **Extensibility**: Easy to extend the language with new expressions
- **Separation of Concerns**: Parsing logic is separated from interpretation
- **Recursive Structure**: Natural fit for hierarchical expressions
- **Domain-Specific**: Can create languages specific to your domain

## When to Use
- When you need to interpret a language or expression
- When you want to create a domain-specific language
- When you have a grammar that can be represented as a syntax tree
- When you need to parse and evaluate expressions

## Example
In our car command interpreter example:
- **AbstractExpression**: CarExpression interface
- **TerminalExpression**: CarCommand, CarValue
- **NonTerminalExpression**: CarOperation, CarCondition
- **Context**: CarContext

This allows us to interpret car commands like "start engine", "accelerate 50", "if speed > 30 then brake". 