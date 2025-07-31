package designPatterns.Behavioral.iterator;

/**
 * CarIterator - This is the Iterator interface in the Iterator pattern.
 * It defines an interface for accessing and traversing elements of a collection.
 * This interface provides a uniform way to iterate over different types of collections.
 */
public interface CarIterator {
    /**
     * Checks if there are more elements to iterate over
     * @return true if there are more elements, false otherwise
     */
    boolean hasNext();
    
    /**
     * Returns the next element in the iteration
     * @return The next Car object
     */
    Car next();
    
    /**
     * Resets the iterator to the beginning
     */
    void reset();
    
    /**
     * Gets the current element without advancing the iterator
     * @return The current Car object
     */
    Car current();
} 