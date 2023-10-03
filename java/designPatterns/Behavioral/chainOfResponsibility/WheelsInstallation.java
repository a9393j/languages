package designPatterns.Behavioral.chainOfResponsibility;

public class WheelsInstallation extends Handler {
    @Override
    public void handle(Car car) {
        if (car.isEngineInstalled() && !car.isWheelsInstalled()) {
            System.out.println("Installing wheels.");
            car.setWheelsInstalled(true);
        }
        if (next != null) {
            next.handle(car);
        }
    }
}
