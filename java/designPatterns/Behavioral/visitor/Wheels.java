package designPatterns.Behavioral.visitor;

/**
 * Wheels - Concrete Element in the Visitor pattern.
 * This represents wheels component that can be visited by different visitors.
 */
public class Wheels implements CarComponent {
    private String name;
    private int condition;
    private int tirePressure;
    private String tireType;
    
    /**
     * Constructor for Wheels
     * @param name The wheels name
     * @param tireType The type of tires
     * @param tirePressure The tire pressure
     */
    public Wheels(String name, String tireType, int tirePressure) {
        this.name = name;
        this.tireType = tireType;
        this.tirePressure = tirePressure;
        // Calculate condition based on tire pressure
        this.condition = Math.max(1, Math.min(10, tirePressure / 10));
    }
    
    @Override
    public void accept(CarVisitor visitor) {
        visitor.visitWheels(this);
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
        return "Wheels";
    }
    
    /**
     * Gets the tire pressure
     * @return The tire pressure
     */
    public int getTirePressure() {
        return tirePressure;
    }
    
    /**
     * Gets the tire type
     * @return The tire type
     */
    public String getTireType() {
        return tireType;
    }
    
    /**
     * Sets the tire pressure
     * @param pressure The new tire pressure
     */
    public void setTirePressure(int pressure) {
        this.tirePressure = pressure;
        this.condition = Math.max(1, Math.min(10, pressure / 10));
    }
    
    /**
     * Sets the wheels condition
     * @param condition The new condition
     */
    public void setCondition(int condition) {
        this.condition = Math.max(1, Math.min(10, condition));
    }
} 