package designPatterns.Behavioral.visitor;

/**
 * MaintenanceVisitor - Concrete Visitor in the Visitor pattern.
 * This visitor performs maintenance operations on car components.
 * It implements different maintenance procedures for each component type.
 */
public class MaintenanceVisitor implements CarVisitor {
    
    @Override
    public void visitEngine(Engine engine) {
        System.out.println("[" + getVisitorName() + "] Performing engine maintenance on " + engine.getName());
        System.out.println("  - Engine Type: " + engine.getEngineType());
        System.out.println("  - Current Mileage: " + engine.getMileage() + " km");
        System.out.println("  - Current Condition: " + engine.getCondition() + "/10");
        
        if (engine.getCondition() < 5) {
            System.out.println("  - ACTION: Engine needs major maintenance!");
            System.out.println("  - Changing oil and filters");
            System.out.println("  - Checking spark plugs");
            engine.setCondition(engine.getCondition() + 3);
        } else if (engine.getCondition() < 7) {
            System.out.println("  - ACTION: Engine needs minor maintenance");
            System.out.println("  - Topping up oil");
            System.out.println("  - Checking fluid levels");
            engine.setCondition(engine.getCondition() + 2);
        } else {
            System.out.println("  - ACTION: Engine is in good condition");
            System.out.println("  - Basic inspection completed");
            engine.setCondition(engine.getCondition() + 1);
        }
        
        System.out.println("  - New Condition: " + engine.getCondition() + "/10");
    }
    
    @Override
    public void visitWheels(Wheels wheels) {
        System.out.println("[" + getVisitorName() + "] Performing wheel maintenance on " + wheels.getName());
        System.out.println("  - Tire Type: " + wheels.getTireType());
        System.out.println("  - Current Pressure: " + wheels.getTirePressure() + " PSI");
        System.out.println("  - Current Condition: " + wheels.getCondition() + "/10");
        
        if (wheels.getCondition() < 4) {
            System.out.println("  - ACTION: Tires need replacement!");
            System.out.println("  - Installing new tires");
            wheels.setTirePressure(35);
            wheels.setCondition(9);
        } else if (wheels.getCondition() < 6) {
            System.out.println("  - ACTION: Tires need inflation");
            System.out.println("  - Inflating tires to proper pressure");
            wheels.setTirePressure(35);
            wheels.setCondition(wheels.getCondition() + 2);
        } else {
            System.out.println("  - ACTION: Tires are in good condition");
            System.out.println("  - Checking tire pressure and tread");
            wheels.setCondition(wheels.getCondition() + 1);
        }
        
        System.out.println("  - New Pressure: " + wheels.getTirePressure() + " PSI");
        System.out.println("  - New Condition: " + wheels.getCondition() + "/10");
    }
    
    @Override
    public void visitBrakes(Brakes brakes) {
        System.out.println("[" + getVisitorName() + "] Performing brake maintenance on " + brakes.getName());
        System.out.println("  - Brake Type: " + brakes.getBrakeType());
        System.out.println("  - Brake Pad Thickness: " + brakes.getBrakePadThickness() + " mm");
        System.out.println("  - Current Condition: " + brakes.getCondition() + "/10");
        
        if (brakes.getCondition() < 3) {
            System.out.println("  - ACTION: Brake pads need immediate replacement!");
            System.out.println("  - Replacing brake pads");
            System.out.println("  - Bleeding brake system");
            brakes.setBrakePadThickness(20);
            brakes.setCondition(9);
        } else if (brakes.getCondition() < 6) {
            System.out.println("  - ACTION: Brake pads need replacement soon");
            System.out.println("  - Checking brake fluid");
            System.out.println("  - Adjusting brake calipers");
            brakes.setCondition(brakes.getCondition() + 2);
        } else {
            System.out.println("  - ACTION: Brakes are in good condition");
            System.out.println("  - Checking brake fluid levels");
            System.out.println("  - Basic brake inspection");
            brakes.setCondition(brakes.getCondition() + 1);
        }
        
        System.out.println("  - New Brake Pad Thickness: " + brakes.getBrakePadThickness() + " mm");
        System.out.println("  - New Condition: " + brakes.getCondition() + "/10");
    }
    
    @Override
    public String getVisitorName() {
        return "Maintenance Technician";
    }
} 