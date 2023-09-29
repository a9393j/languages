package designPatterns.Creational.Builder;

public class CarBuilderImpl implements CarBuilder {
    private String make;
    private String model;
    private String color;
    private int year;
    private boolean isElectric;

    @Override
    public CarBuilder setMake(String make) {
        this.make = make;
        return this;
    }

    @Override
    public CarBuilder setModel(String model) {
        this.model = model;
        return this;
    }

    @Override
    public CarBuilder setColor(String color) {
        this.color = color;
        return this;
    }

    @Override
    public CarBuilder setYear(int year) {
        this.year = year;
        return this;
    }

    @Override
    public CarBuilder setIsElectric(boolean isElectric) {
        this.isElectric = isElectric;
        return this;
    }

    @Override
    public Car build() {
        return new Car(make, model, color, year, isElectric);
    }
}
