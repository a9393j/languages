package designPatterns.Behavioral.interpreter;

/**
 * Main class demonstrating the Interpreter pattern.
 * This example shows how the Interpreter pattern allows us to:
 * - Interpret a language or expression
 * - Parse and evaluate expressions
 * - Define a grammar for a language
 * - Create a domain-specific language (DSL)
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("=== Interpreter Pattern Demo ===\n");
        
        // Create a car and context
        Car car = new Car("Tesla Model S");
        CarContext context = new CarContext(car);
        
        System.out.println("1. Simple car commands:");
        System.out.println("=== Simple Commands ===");
        
        CarCommand startCommand = new CarCommand("start");
        CarCommand accelerateCommand = new CarCommand("accelerate", 30);
        CarCommand statusCommand = new CarCommand("status");
        
        startCommand.interpret(context);
        accelerateCommand.interpret(context);
        statusCommand.interpret(context);
        
        System.out.println("\n2. Conditional expressions:");
        System.out.println("=== Conditional Expressions ===");
        
        // Create a condition: if speed > 20 then brake
        CarCommand brakeCommand = new CarCommand("brake", 15);
        CarCondition speedCondition = new CarCondition("speed", ">", 20, brakeCommand);
        
        speedCondition.interpret(context);
        
        // Create another condition: if fuel < 50 then refuel
        CarCommand refuelCommand = new CarCommand("refuel", 30);
        CarCondition fuelCondition = new CarCondition("fuel", "<", 50, refuelCommand);
        
        fuelCondition.interpret(context);
        
        System.out.println("\n3. Command sequences:");
        System.out.println("=== Command Sequences ===");
        
        CarSequence sequence = new CarSequence();
        sequence.addExpressions(
            new CarCommand("accelerate", 40),
            new CarCommand("status"),
            new CarCondition("speed", ">", 30, new CarCommand("brake", 20)),
            new CarCommand("status")
        );
        
        sequence.interpret(context);
        
        System.out.println("\n4. Complex nested expressions:");
        System.out.println("=== Complex Expressions ===");
        
        // Create a complex sequence with conditions
        CarSequence complexSequence = new CarSequence();
        
        // Add commands to the sequence
        complexSequence.addExpression(new CarCommand("accelerate", 60));
        complexSequence.addExpression(new CarCommand("status"));
        
        // Add a condition that checks multiple properties
        CarCondition speedCheck = new CarCondition("speed", ">", 50, 
            new CarCommand("brake", 25));
        complexSequence.addExpression(speedCheck);
        
        // Add another condition for fuel
        CarCondition fuelCheck = new CarCondition("fuel", "<", 80, 
            new CarCommand("refuel", 20));
        complexSequence.addExpression(fuelCheck);
        
        complexSequence.addExpression(new CarCommand("status"));
        
        complexSequence.interpret(context);
        
        System.out.println("\n5. Demonstrating grammar structure:");
        System.out.println("=== Grammar Structure ===");
        System.out.println("The interpreter supports the following grammar:");
        System.out.println("Command ::= 'start' | 'stop' | 'accelerate' [value] | 'brake' [value] | 'speed' value | 'refuel' [value] | 'status'");
        System.out.println("Condition ::= 'if' property operator value 'then' action");
        System.out.println("Sequence ::= command ';' command ';' ...");
        System.out.println("Property ::= 'speed' | 'fuel' | 'engine'");
        System.out.println("Operator ::= '>' | '<' | '>=' | '<=' | '==' | '!='");
        System.out.println("Value ::= number");
        System.out.println("Action ::= Command | Condition | Sequence");
        
        System.out.println("\n6. Example expressions:");
        System.out.println("=== Example Expressions ===");
        System.out.println("• start");
        System.out.println("• accelerate 50");
        System.out.println("• if speed > 30 then brake 20");
        System.out.println("• if fuel < 50 then refuel 30");
        System.out.println("• start; accelerate 40; if speed > 30 then brake; status");
        
        System.out.println("\n=== Interpreter Pattern Benefits ===");
        System.out.println("✓ Grammar Definition: Easy to define and modify grammar");
        System.out.println("✓ Extensibility: Easy to extend the language with new expressions");
        System.out.println("✓ Separation of Concerns: Parsing logic is separated from interpretation");
        System.out.println("✓ Recursive Structure: Natural fit for hierarchical expressions");
        System.out.println("✓ Domain-Specific: Can create languages specific to your domain");
        System.out.println("✓ Expression Tree: Complex expressions can be built from simple ones");
    }
} 