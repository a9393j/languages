package designPatterns.Behavioral.visitor;

/**
 * Brakes - Concrete Element in the Visitor pattern.
 * This represents brakes component that can be visited by different visitors.
 */
public class Brakes implements CarComponent {
    private String name;
    private int condition;
    private int brakePadThickness;
    private String brakeType;
    
    /**
     * Constructor for Brakes
     * @param name The brakes name
     * @param brakeType The type of brakes
     * @param brakePadThickness The brake pad thickness in mm
     */
    public Brakes(String name, String brakeType, int brakePadThickness) {
        this.name = name;
        this.brakeType = brakeType;
        this.brakePadThickness = brakePadThickness;
        // Calculate condition based on brake pad thickness
        this.condition = Math.max(1, Math.min(10, brakePadThickness / 2));
    }
    
    @Override
    public void accept(CarVisitor visitor) {
        visitor.visitBrakes(this);
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
        return "Brakes";
    }
    
    /**
     * Gets the brake pad thickness
     * @return The brake pad thickness in mm
     */
    public int getBrakePadThickness() {
        return brakePadThickness;
    }
    
    /**
     * Gets the brake type
     * @return The brake type
     */
    public String getBrakeType() {
        return brakeType;
    }
    
    /**
     * Sets the brake pad thickness
     * @param thickness The new brake pad thickness
     */
    public void setBrakePadThickness(int thickness) {
        this.brakePadThickness = thickness;
        this.condition = Math.max(1, Math.min(10, thickness / 2));
    }
    
    /**
     * Sets the brakes condition
     * @param condition The new condition
     */
    public void setCondition(int condition) {
        this.condition = Math.max(1, Math.min(10, condition));
    }
} 