package designPatterns.Behavioral.iterator;

/**
 * CarListIterator - This is the ConcreteIterator in the Iterator pattern.
 * It implements the CarIterator interface and provides the concrete implementation
 * for iterating over a CarList collection.
 */
public class CarListIterator implements CarIterator {
    private CarList carList;
    private int currentIndex;
    
    /**
     * Constructor for CarListIterator
     * @param carList The car list to iterate over
     */
    public CarListIterator(CarList carList) {
        this.carList = carList;
        this.currentIndex = 0;
    }
    
    @Override
    public boolean hasNext() {
        return currentIndex < carList.getCount();
    }
    
    @Override
    public Car next() {
        if (hasNext()) {
            Car car = carList.getCar(currentIndex);
            currentIndex++;
            return car;
        }
        return null;
    }
    
    @Override
    public void reset() {
        currentIndex = 0;
    }
    
    @Override
    public Car current() {
        if (currentIndex >= 0 && currentIndex < carList.getCount()) {
            return carList.getCar(currentIndex);
        }
        return null;
    }
    
    /**
     * Gets the current index
     * @return The current index
     */
    public int getCurrentIndex() {
        return currentIndex;
    }
} 