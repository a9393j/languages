package designPatterns.Behavioral.observer;

/**
 * IndividualBuyer - Concrete Observer in the Observer pattern.
 * This represents an individual person interested in buying cars.
 * They receive notifications about new cars, price changes, and car sales.
 */
public class IndividualBuyer implements CarBuyer {
    private String name;
    private String preferredMake;
    private double maxBudget;
    
    /**
     * Constructor for IndividualBuyer
     * @param name The buyer's name
     * @param preferredMake The preferred car make
     * @param maxBudget The maximum budget
     */
    public IndividualBuyer(String name, String preferredMake, double maxBudget) {
        this.name = name;
        this.preferredMake = preferredMake;
        this.maxBudget = maxBudget;
    }
    
    @Override
    public void update(Car car, String eventType) {
        // Only show notifications for preferred make or if no preference
        if (preferredMake == null || car.getMake().equalsIgnoreCase(preferredMake)) {
            switch (eventType) {
                case "NEW_CAR":
                    if (car.getPrice() <= maxBudget) {
                        System.out.println("[" + name + "] New car alert! " + car);
                        System.out.println("    This fits my budget! I should check it out.");
                    } else {
                        System.out.println("[" + name + "] New car alert! " + car);
                        System.out.println("    Too expensive for my budget of $" + maxBudget);
                    }
                    break;
                    
                case "PRICE_CHANGE":
                    if (car.getPrice() <= maxBudget) {
                        System.out.println("[" + name + "] Price change alert! " + car);
                        System.out.println("    The price is now within my budget!");
                    } else {
                        System.out.println("[" + name + "] Price change alert! " + car);
                        System.out.println("    Still too expensive for my budget of $" + maxBudget);
                    }
                    break;
                    
                case "CAR_SOLD":
                    System.out.println("[" + name + "] Car sold alert! " + car);
                    System.out.println("    That car is no longer available.");
                    break;
            }
        }
    }
    
    @Override
    public String getName() {
        return name;
    }
    
    @Override
    public String getPreferredMake() {
        return preferredMake;
    }
    
    /**
     * Gets the maximum budget
     * @return The maximum budget
     */
    public double getMaxBudget() {
        return maxBudget;
    }
} 