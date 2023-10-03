package designPatterns.structural.proxy;
/**
 * In the Proxy pattern, a surrogate or placeholder for another object
 * controls access to it. This can be helpful for implementing lazy 
 * initialization, logging, access control, and other concerns. 
 * Let's create an example where we use a proxy to control the access 
 * to the Car object. We’ll create a CarProxy that controls the creation 
 * and access to the real Car object.
 * 
 * 
 * Car (RealSubject): The Car class is the real object that the proxy represents. 
 * It has a drive() method that simulates driving the car. 
 * CarProxy (Proxy): This class acts as a proxy to the real Car object. 
 * It maintains a reference to the real Car that is only initialized when 
 * the drive() method is called. This demonstrates lazy initialization - the 
 * real Car object is only created when it’s actually needed.
 * 
 * Main (Client): The Main class (the client) interacts with the CarProxy 
 * instead of the Car class directly. When the drive() method is called on 
 * the proxy, it initializes the real Car object and calls its drive() method.
 * 
 * This pattern is helpful when you want to add some level of control over the 
 * interaction with an object, for example, to defer the full cost of its 
 * creation and initialization until we actually need to use it. 
 * Also, the proxy can add additional behavior like logging, 
 * access control, etc., when methods on the real object are called.
 */
public class Main {
    public static void main(String[] args) {
        CarProxy carProxy = new CarProxy("Sedan");

        System.out.println("Car has not actually been created yet...\n");
 
        System.out.println("Trying to drive the car...");
        carProxy.drive();  // This will initiate the creation of the real car
    }
}
