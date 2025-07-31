package designPatterns.Behavioral.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * CarDealer - This is the Subject in the Observer pattern.
 * It maintains a list of observers and notifies them of any state changes.
 * The dealer manages car inventory and notifies interested buyers of changes.
 */
public class CarDealer {
    private String dealerName;
    private List<Car> inventory;
    private List<CarBuyer> observers;
    
    /**
     * Constructor for CarDealer
     * @param dealerName The dealer's name
     */
    public CarDealer(String dealerName) {
        this.dealerName = dealerName;
        this.inventory = new ArrayList<>();
        this.observers = new ArrayList<>();
    }
    
    /**
     * Adds an observer to the notification list
     * @param observer The observer to add
     */
    public void addObserver(CarBuyer observer) {
        if (!observers.contains(observer)) {
            observers.add(observer);
            System.out.println("[" + dealerName + "] " + observer.getName() + " subscribed to notifications");
        }
    }
    
    /**
     * Removes an observer from the notification list
     * @param observer The observer to remove
     */
    public void removeObserver(CarBuyer observer) {
        if (observers.remove(observer)) {
            System.out.println("[" + dealerName + "] " + observer.getName() + " unsubscribed from notifications");
        }
    }
    
    /**
     * Notifies all observers of a change
     * @param car The car that triggered the notification
     * @param eventType The type of event
     */
    private void notifyObservers(Car car, String eventType) {
        for (CarBuyer observer : observers) {
            observer.update(car, eventType);
        }
    }
    
    /**
     * Adds a new car to inventory and notifies observers
     * @param car The car to add
     */
    public void addCar(Car car) {
        inventory.add(car);
        System.out.println("[" + dealerName + "] Added new car to inventory: " + car);
        notifyObservers(car, "NEW_CAR");
    }
    
    /**
     * Changes the price of a car and notifies observers
     * @param car The car to change price for
     * @param newPrice The new price
     */
    public void changePrice(Car car, double newPrice) {
        double oldPrice = car.getPrice();
        car.setPrice(newPrice);
        System.out.println("[" + dealerName + "] Changed price for " + car.getMake() + " " + car.getModel() + 
                          " from $" + oldPrice + " to $" + newPrice);
        notifyObservers(car, "PRICE_CHANGE");
    }
    
    /**
     * Sells a car and notifies observers
     * @param car The car to sell
     */
    public void sellCar(Car car) {
        if (inventory.remove(car)) {
            car.setAvailable(false);
            System.out.println("[" + dealerName + "] Sold car: " + car);
            notifyObservers(car, "CAR_SOLD");
        } else {
            System.out.println("[" + dealerName + "] Car not found in inventory: " + car);
        }
    }
    
    /**
     * Gets the current inventory
     * @return List of available cars
     */
    public List<Car> getInventory() {
        return new ArrayList<>(inventory);
    }
    
    /**
     * Gets the dealer name
     * @return The dealer name
     */
    public String getDealerName() {
        return dealerName;
    }
    
    /**
     * Gets the number of observers
     * @return The number of observers
     */
    public int getObserverCount() {
        return observers.size();
    }
} 