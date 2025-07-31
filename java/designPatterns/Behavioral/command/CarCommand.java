package designPatterns.Behavioral.command;

/**
 * CarCommand - This is the Command interface in the Command pattern.
 * It declares an interface for executing an operation.
 * All concrete commands will implement this interface.
 */
public interface CarCommand {
    /**
     * Executes the command
     */
    void execute();
    
    /**
     * Undoes the command (optional)
     */
    void undo();
} 