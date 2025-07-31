package designPatterns.Behavioral.iterator;

/**
 * CarCollection - This is the Aggregate interface in the Iterator pattern.
 * It defines an interface for creating an Iterator object.
 * This interface allows clients to create iterators without knowing the collection's implementation.
 */
public interface CarCollection {
    /**
     * Creates an iterator for this collection
     * @return A CarIterator object
     */
    CarIterator createIterator();
    
    /**
     * Adds a car to the collection
     * @param car The car to add
     */
    void addCar(Car car);
    
    /**
     * Removes a car from the collection
     * @param car The car to remove
     */
    void removeCar(Car car);
    
    /**
     * Gets the number of cars in the collection
     * @return The number of cars
     */
    int getCount();
} 