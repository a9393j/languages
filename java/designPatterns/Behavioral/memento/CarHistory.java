package designPatterns.Behavioral.memento;

import java.util.Stack;

/**
 * CarHistory - This is the Caretaker in the Memento pattern.
 * It manages and stores the mementos but never examines or modifies their contents.
 * It provides undo/redo functionality by managing a stack of mementos.
 */
public class CarHistory {
    private Stack<CarMemento> undoStack;
    private Stack<CarMemento> redoStack;
    private Car car;
    
    /**
     * Constructor for CarHistory
     * @param car The car whose history to manage
     */
    public CarHistory(Car car) {
        this.car = car;
        this.undoStack = new Stack<>();
        this.redoStack = new Stack<>();
    }
    
    /**
     * Saves the current state of the car
     */
    public void saveState() {
        CarMemento memento = car.saveState();
        undoStack.push(memento);
        redoStack.clear(); // Clear redo stack when new state is saved
        System.out.println("[" + car.getModel() + "] State saved at " + memento.getTimestamp());
    }
    
    /**
     * Undoes the last action by restoring the previous state
     * @return true if undo was successful, false if no states to undo
     */
    public boolean undo() {
        if (undoStack.isEmpty()) {
            System.out.println("[" + car.getModel() + "] Nothing to undo");
            return false;
        }
        
        // Save current state to redo stack
        CarMemento currentState = car.saveState();
        redoStack.push(currentState);
        
        // Restore previous state
        CarMemento previousState = undoStack.pop();
        car.restoreState(previousState);
        
        System.out.println("[" + car.getModel() + "] Undone to state from " + previousState.getTimestamp());
        return true;
    }
    
    /**
     * Redoes the last undone action
     * @return true if redo was successful, false if no states to redo
     */
    public boolean redo() {
        if (redoStack.isEmpty()) {
            System.out.println("[" + car.getModel() + "] Nothing to redo");
            return false;
        }
        
        // Save current state to undo stack
        CarMemento currentState = car.saveState();
        undoStack.push(currentState);
        
        // Restore next state
        CarMemento nextState = redoStack.pop();
        car.restoreState(nextState);
        
        System.out.println("[" + car.getModel() + "] Redone to state from " + nextState.getTimestamp());
        return true;
    }
    
    /**
     * Gets the number of states that can be undone
     * @return The number of undoable states
     */
    public int getUndoCount() {
        return undoStack.size();
    }
    
    /**
     * Gets the number of states that can be redone
     * @return The number of redoable states
     */
    public int getRedoCount() {
        return redoStack.size();
    }
    
    /**
     * Clears all history
     */
    public void clearHistory() {
        undoStack.clear();
        redoStack.clear();
        System.out.println("[" + car.getModel() + "] History cleared");
    }
    
    /**
     * Gets information about the history
     */
    public void getHistoryInfo() {
        System.out.println("=== Car History Information ===");
        System.out.println("Undoable states: " + getUndoCount());
        System.out.println("Redoable states: " + getRedoCount());
        
        if (!undoStack.isEmpty()) {
            System.out.println("Last saved state: " + undoStack.peek().getTimestamp());
        }
        
        if (!redoStack.isEmpty()) {
            System.out.println("Next redo state: " + redoStack.peek().getTimestamp());
        }
    }
    
    /**
     * Gets the car being managed
     * @return The car
     */
    public Car getCar() {
        return car;
    }
} 