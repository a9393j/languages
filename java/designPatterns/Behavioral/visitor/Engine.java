package designPatterns.Behavioral.visitor;

/**
 * Engine - Concrete Element in the Visitor pattern.
 * This represents an engine component that can be visited by different visitors.
 */
public class Engine implements CarComponent {
    private String name;
    private int condition;
    private String engineType;
    private int mileage;
    
    /**
     * Constructor for Engine
     * @param name The engine name
     * @param engineType The type of engine
     * @param mileage The engine mileage
     */
    public Engine(String name, String engineType, int mileage) {
        this.name = name;
        this.engineType = engineType;
        this.mileage = mileage;
        // Calculate condition based on mileage
        this.condition = Math.max(1, 10 - (mileage / 10000));
    }
    
    @Override
    public void accept(CarVisitor visitor) {
        visitor.visitEngine(this);
    }
    
    @Override
    public String getName() {
        return name;
    }
    
    @Override
    public int getCondition() {
        return condition;
    }
    
    @Override
    public String getType() {
        return "Engine";
    }
    
    /**
     * Gets the engine type
     * @return The engine type
     */
    public String getEngineType() {
        return engineType;
    }
    
    /**
     * Gets the engine mileage
     * @return The engine mileage
     */
    public int getMileage() {
        return mileage;
    }
    
    /**
     * Sets the engine condition
     * @param condition The new condition
     */
    public void setCondition(int condition) {
        this.condition = Math.max(1, Math.min(10, condition));
    }
} 