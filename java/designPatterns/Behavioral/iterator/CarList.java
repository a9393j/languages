package designPatterns.Behavioral.iterator;

import java.util.ArrayList;
import java.util.List;

/**
 * CarList - This is the ConcreteAggregate in the Iterator pattern.
 * It implements the CarCollection interface and stores cars in an ArrayList.
 * This class provides the concrete implementation of the collection.
 */
public class CarList implements CarCollection {
    private List<Car> cars;
    
    /**
     * Constructor for CarList
     */
    public CarList() {
        this.cars = new ArrayList<>();
    }
    
    @Override
    public CarIterator createIterator() {
        return new CarListIterator(this);
    }
    
    @Override
    public void addCar(Car car) {
        cars.add(car);
    }
    
    @Override
    public void removeCar(Car car) {
        cars.remove(car);
    }
    
    @Override
    public int getCount() {
        return cars.size();
    }
    
    /**
     * Gets a car at a specific index
     * @param index The index of the car
     * @return The car at the specified index
     */
    public Car getCar(int index) {
        if (index >= 0 && index < cars.size()) {
            return cars.get(index);
        }
        return null;
    }
    
    /**
     * Gets all cars as a list
     * @return List of all cars
     */
    public List<Car> getAllCars() {
        return new ArrayList<>(cars); // Return a copy to prevent external modification
    }
} 