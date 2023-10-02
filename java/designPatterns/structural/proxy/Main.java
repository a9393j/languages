package designPatterns.structural.proxy;

public class Main {
    public static void main(String[] args) {
        CarProxy carProxy = new CarProxy("Sedan");

        System.out.println("Car has not actually been created yet...\n");
 
        System.out.println("Trying to drive the car...");
        carProxy.drive();  // This will initiate the creation of the real car
    }
}
