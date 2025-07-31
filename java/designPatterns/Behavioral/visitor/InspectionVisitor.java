package designPatterns.Behavioral.visitor;

/**
 * InspectionVisitor - Concrete Visitor in the Visitor pattern.
 * This visitor performs inspection operations on car components.
 * It implements different inspection procedures for each component type.
 */
public class InspectionVisitor implements CarVisitor {
    
    @Override
    public void visitEngine(Engine engine) {
        System.out.println("[" + getVisitorName() + "] Inspecting engine: " + engine.getName());
        System.out.println("  - Engine Type: " + engine.getEngineType());
        System.out.println("  - Mileage: " + engine.getMileage() + " km");
        System.out.println("  - Condition: " + engine.getCondition() + "/10");
        
        if (engine.getCondition() >= 8) {
            System.out.println("  - STATUS: Excellent condition");
            System.out.println("  - RECOMMENDATION: No action needed");
        } else if (engine.getCondition() >= 6) {
            System.out.println("  - STATUS: Good condition");
            System.out.println("  - RECOMMENDATION: Schedule maintenance soon");
        } else if (engine.getCondition() >= 4) {
            System.out.println("  - STATUS: Fair condition");
            System.out.println("  - RECOMMENDATION: Maintenance required");
        } else {
            System.out.println("  - STATUS: Poor condition");
            System.out.println("  - RECOMMENDATION: Immediate maintenance needed");
        }
        
        System.out.println("  - INSPECTION: Engine inspection completed");
    }
    
    @Override
    public void visitWheels(Wheels wheels) {
        System.out.println("[" + getVisitorName() + "] Inspecting wheels: " + wheels.getName());
        System.out.println("  - Tire Type: " + wheels.getTireType());
        System.out.println("  - Tire Pressure: " + wheels.getTirePressure() + " PSI");
        System.out.println("  - Condition: " + wheels.getCondition() + "/10");
        
        if (wheels.getCondition() >= 8) {
            System.out.println("  - STATUS: Excellent condition");
            System.out.println("  - RECOMMENDATION: No action needed");
        } else if (wheels.getCondition() >= 6) {
            System.out.println("  - STATUS: Good condition");
            System.out.println("  - RECOMMENDATION: Check pressure regularly");
        } else if (wheels.getCondition() >= 4) {
            System.out.println("  - STATUS: Fair condition");
            System.out.println("  - RECOMMENDATION: Inflate tires or consider replacement");
        } else {
            System.out.println("  - STATUS: Poor condition");
            System.out.println("  - RECOMMENDATION: Tire replacement required");
        }
        
        System.out.println("  - INSPECTION: Wheel inspection completed");
    }
    
    @Override
    public void visitBrakes(Brakes brakes) {
        System.out.println("[" + getVisitorName() + "] Inspecting brakes: " + brakes.getName());
        System.out.println("  - Brake Type: " + brakes.getBrakeType());
        System.out.println("  - Brake Pad Thickness: " + brakes.getBrakePadThickness() + " mm");
        System.out.println("  - Condition: " + brakes.getCondition() + "/10");
        
        if (brakes.getCondition() >= 8) {
            System.out.println("  - STATUS: Excellent condition");
            System.out.println("  - RECOMMENDATION: No action needed");
        } else if (brakes.getCondition() >= 6) {
            System.out.println("  - STATUS: Good condition");
            System.out.println("  - RECOMMENDATION: Monitor brake pad wear");
        } else if (brakes.getCondition() >= 4) {
            System.out.println("  - STATUS: Fair condition");
            System.out.println("  - RECOMMENDATION: Brake pad replacement soon");
        } else {
            System.out.println("  - STATUS: Poor condition");
            System.out.println("  - RECOMMENDATION: Immediate brake pad replacement");
        }
        
        System.out.println("  - INSPECTION: Brake inspection completed");
    }
    
    @Override
    public String getVisitorName() {
        return "Safety Inspector";
    }
} 