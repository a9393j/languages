package designPatterns;

import java.util.*;

import designPatterns.Creational.Builder.Car;
import designPatterns.Creational.Builder.CarBuilder;
import designPatterns.Creational.Builder.CarBuilderImpl;

public class DesignPatterns {

  public static void main(String[] args) {

    System.out.println("==========================================");
    System.out.println("Various Design Patterns in action");

    System.out.println("==========================================");
    System.out.println("Creational Design Patterns");
    System.out.println("------------------------------------------");
    System.out.println("Singleton");

    System.out.println("Builder");
    builder();

    System.out.println("Factory");
    System.out.println("Abstract Factory");
    System.out.println("Prototype");

    System.out.println("------------------------------------------");
    System.out.println("Structural Design Patterns");
    System.out.println("------------------------------------------");
    System.out.println("Adapter");
    System.out.println("Bridge");
    System.out.println("Composite");
    System.out.println("Decorator");
    System.out.println("Facade");
    System.out.println("Flyweight");
    System.out.println("Proxy");

    System.out.println("------------------------------------------");
    System.out.println("Behavioral Design Patterns");
    System.out.println("------------------------------------------");
    System.out.println("Chain of Responsibility");
    System.out.println("Command");
    System.out.println("Iterator");
    System.out.println("Mediator");
    System.out.println("Observer");
    System.out.println("State");
    System.out.println("Strategy");
    System.out.println("Template method");
    System.out.println("Visitor");

  }

  private static void builder() {
    System.out.println("Builder");
    CarBuilder builder = new CarBuilderImpl();
    Car car = builder.setMake("Toyota")
        .setModel("Camry")
        .setColor("Black")
        .setYear(2022)
        .setIsElectric(false)
        .build();

    System.out.println(car);
  }
}
