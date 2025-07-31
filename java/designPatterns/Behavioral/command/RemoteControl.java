package designPatterns.Behavioral.command;

import java.util.Stack;

/**
 * RemoteControl - This is the Invoker in the Command pattern.
 * It asks the command to carry out the request.
 * It doesn't know how the command is implemented or what receiver it uses.
 */
public class RemoteControl {
    private CarCommand startCommand;
    private CarCommand stopCommand;
    private CarCommand accelerateCommand;
    private CarCommand brakeCommand;
    
    // Command history for undo functionality
    private Stack<CarCommand> commandHistory;
    
    /**
     * Constructor for RemoteControl
     */
    public RemoteControl() {
        this.commandHistory = new Stack<>();
    }
    
    /**
     * Sets the start command
     * @param command The start command to set
     */
    public void setStartCommand(CarCommand command) {
        this.startCommand = command;
    }
    
    /**
     * Sets the stop command
     * @param command The stop command to set
     */
    public void setStopCommand(CarCommand command) {
        this.stopCommand = command;
    }
    
    /**
     * Sets the accelerate command
     * @param command The accelerate command to set
     */
    public void setAccelerateCommand(CarCommand command) {
        this.accelerateCommand = command;
    }
    
    /**
     * Sets the brake command
     * @param command The brake command to set
     */
    public void setBrakeCommand(CarCommand command) {
        this.brakeCommand = command;
    }
    
    /**
     * Presses the start button
     */
    public void pressStartButton() {
        if (startCommand != null) {
            startCommand.execute();
            commandHistory.push(startCommand);
        }
    }
    
    /**
     * Presses the stop button
     */
    public void pressStopButton() {
        if (stopCommand != null) {
            stopCommand.execute();
            commandHistory.push(stopCommand);
        }
    }
    
    /**
     * Presses the accelerate button
     */
    public void pressAccelerateButton() {
        if (accelerateCommand != null) {
            accelerateCommand.execute();
            commandHistory.push(accelerateCommand);
        }
    }
    
    /**
     * Presses the brake button
     */
    public void pressBrakeButton() {
        if (brakeCommand != null) {
            brakeCommand.execute();
            commandHistory.push(brakeCommand);
        }
    }
    
    /**
     * Undoes the last command
     */
    public void undoLastCommand() {
        if (!commandHistory.isEmpty()) {
            CarCommand lastCommand = commandHistory.pop();
            lastCommand.undo();
        } else {
            System.out.println("No commands to undo");
        }
    }
    
    /**
     * Gets the number of commands in history
     * @return Number of commands in history
     */
    public int getCommandHistorySize() {
        return commandHistory.size();
    }
} 