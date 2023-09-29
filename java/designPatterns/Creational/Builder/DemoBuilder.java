package designPatterns.Creational.Builder;

public class DemoBuilder {

    public static void main(String[] args) {

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
