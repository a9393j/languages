package designPatterns.Behavioral.chainOfResponsibility;

public class EngineInstallation extends Handler {
    @Override
    public void handle(Car car) {
        if (!car.isEngineInstalled()) {
            System.out.println("Installing engine.");
            car.setEngineInstalled(true);
        }
        if (next != null) {
            next.handle(car);
        }
    }
}
