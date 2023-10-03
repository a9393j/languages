package designPatterns.Behavioral.chainOfResponsibility;

public class PaintApplication extends Handler {
    @Override
    public void handle(Car car) {
        if (car.isEngineInstalled() && car.isWheelsInstalled() && !car.isPaintApplied()) {
            System.out.println("Applying paint.");
            car.setPaintApplied(true);
        }
        if (next != null) {
            next.handle(car);
        }
    }
}
