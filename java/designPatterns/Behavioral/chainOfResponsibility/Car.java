package designPatterns.Behavioral.chainOfResponsibility;

public class Car {
    private boolean engineInstalled = false;
    private boolean wheelsInstalled = false;
    private boolean paintApplied = false;

    // Getters and setters for the above attributes
    // ...

    public boolean isEngineInstalled() {
        return engineInstalled;
    }

    public void setEngineInstalled(boolean engineInstalled) {
        this.engineInstalled = engineInstalled;
    }

    public boolean isWheelsInstalled() {
        return wheelsInstalled;
    }

    public void setWheelsInstalled(boolean wheelsInstalled) {
        this.wheelsInstalled = wheelsInstalled;
    }

    public boolean isPaintApplied() {
        return paintApplied;
    }

    public void setPaintApplied(boolean paintApplied) {
        this.paintApplied = paintApplied;
    }
}
