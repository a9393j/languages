package designPatterns.Behavioral.chainOfResponsibility;

/**
 * Car: A simple class that has the status of various components (engine,
 * wheels, and paint).
 * 
 * Handler: An abstract class that serves as the base for different stages of
 * car manufacturing.
 * It has a method setNext() to set the next handler in the chain and an
 * abstract method handle()
 * to be overridden by concrete handlers.
 * 
 * EngineInstallation, WheelsInstallation, PaintApplication: These are concrete
 * handlers that
 * process the request (car manufacturing in this case) and pass it to the next
 * handler
 * in the chain if necessary.
 * 
 * Main: This class creates a car and handlers, sets the chain of handlers, and
 * initiates the
 * request handling. The request (car) goes through the chain, getting processed
 * by each
 * handler in turn.
 * 
 * The Chain of Responsibility pattern is useful in scenarios where a request
 * needs to be
 * processed by multiple processors, and those processors are in a sequence
 * where each processor
 * does its part and forwards the request to the next one in the chain.
 * 
 */
public class Main {
    public static void main(String[] args) {
        Car car = new Car();

        Handler engineInstallation = new EngineInstallation();
        Handler wheelsInstallation = new WheelsInstallation();
        Handler paintApplication = new PaintApplication();

        engineInstallation.setNext(wheelsInstallation).setNext(paintApplication);

        engineInstallation.handle(car);
        System.out.println("Car manufacturing completed!");
    }
}
