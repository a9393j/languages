package designPatterns.structural.facade;

public class Main {
    public static void main(String[] args) {
        CarFacade carFacade = new CarFacade();

        carFacade.startCar();
        System.out.println("-------------------");
        carFacade.stopCar();
    }
}
