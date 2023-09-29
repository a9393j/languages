package designPatterns.Creational.Builder;

// Using car Builder Interface to inforce certain building patterns
public interface CarBuilder {
    CarBuilder setMake(String make);

    CarBuilder setModel(String model);

    CarBuilder setColor(String color);

    CarBuilder setYear(int year);

    CarBuilder setIsElectric(boolean isElectric);

    Car build();
}
