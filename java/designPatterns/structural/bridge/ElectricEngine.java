package designPatterns.structural.bridge;

/**
 * ElectricEngine - Concrete implementation of the Engine interface.
 * This represents an electric-powered engine with specific characteristics.
 */
public class ElectricEngine implements Engine {
    private final int power;
    private final int batteryCapacity; // in kWh
    
    /**
     * Constructor for ElectricEngine
     * @param power Engine power in horsepower
     * @param batteryCapacity Battery capacity in kWh
     */
    public ElectricEngine(int power, int batteryCapacity) {
        this.power = power;
        this.batteryCapacity = batteryCapacity;
    }
    
    @Override
    public void start() {
        System.out.println("Starting electric engine with " + power + " HP and " + batteryCapacity + " kWh battery");
    }
    
    @Override
    public void stop() {
        System.out.println("Stopping electric engine");
    }
    
    @Override
    public String getEngineType() {
        return "Electric Engine";
    }
    
    @Override
    public int getPower() {
        return power;
    }
    
    /**
     * Gets the battery capacity
     * @return Battery capacity in kWh
     */
    public int getBatteryCapacity() {
        return batteryCapacity;
    }
} 