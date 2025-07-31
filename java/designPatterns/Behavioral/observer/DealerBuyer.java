package designPatterns.Behavioral.observer;

/**
 * DealerBuyer - Concrete Observer in the Observer pattern.
 * This represents a car dealer interested in buying cars for their inventory.
 * They receive notifications about new cars and price changes.
 */
public class DealerBuyer implements CarBuyer {
    private String dealerName;
    private String preferredMake;
    private double maxPurchasePrice;
    private int inventoryCapacity;
    
    /**
     * Constructor for DealerBuyer
     * @param dealerName The dealer's name
     * @param preferredMake The preferred car make
     * @param maxPurchasePrice The maximum purchase price
     * @param inventoryCapacity The inventory capacity
     */
    public DealerBuyer(String dealerName, String preferredMake, double maxPurchasePrice, int inventoryCapacity) {
        this.dealerName = dealerName;
        this.preferredMake = preferredMake;
        this.maxPurchasePrice = maxPurchasePrice;
        this.inventoryCapacity = inventoryCapacity;
    }
    
    @Override
    public void update(Car car, String eventType) {
        // Only show notifications for preferred make or if no preference
        if (preferredMake == null || car.getMake().equalsIgnoreCase(preferredMake)) {
            switch (eventType) {
                case "NEW_CAR":
                    if (car.getPrice() <= maxPurchasePrice) {
                        System.out.println("[" + dealerName + "] New inventory opportunity! " + car);
                        System.out.println("    Price is within our purchase budget of $" + maxPurchasePrice);
                        System.out.println("    We have capacity for " + inventoryCapacity + " more cars.");
                    } else {
                        System.out.println("[" + dealerName + "] New car alert! " + car);
                        System.out.println("    Price exceeds our purchase budget of $" + maxPurchasePrice);
                    }
                    break;
                    
                case "PRICE_CHANGE":
                    if (car.getPrice() <= maxPurchasePrice) {
                        System.out.println("[" + dealerName + "] Price drop alert! " + car);
                        System.out.println("    Price is now within our purchase budget!");
                    } else {
                        System.out.println("[" + dealerName + "] Price change alert! " + car);
                        System.out.println("    Still above our purchase budget of $" + maxPurchasePrice);
                    }
                    break;
                    
                case "CAR_SOLD":
                    System.out.println("[" + dealerName + "] Car sold alert! " + car);
                    System.out.println("    That car is no longer available for purchase.");
                    break;
            }
        }
    }
    
    @Override
    public String getName() {
        return dealerName;
    }
    
    @Override
    public String getPreferredMake() {
        return preferredMake;
    }
    
    /**
     * Gets the maximum purchase price
     * @return The maximum purchase price
     */
    public double getMaxPurchasePrice() {
        return maxPurchasePrice;
    }
    
    /**
     * Gets the inventory capacity
     * @return The inventory capacity
     */
    public int getInventoryCapacity() {
        return inventoryCapacity;
    }
} 