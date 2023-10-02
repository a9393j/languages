package designPatterns.structural.flyweight;

import java.util.HashMap;
import java.util.Map;

public class CarColorFactory {
    private final Map<String, CarColor> colorMap = new HashMap<>();

    public CarColor getCarColor(String colorName) {
        CarColor carColor = colorMap.get(colorName);
        if (carColor == null) {
            carColor = new CarColor(colorName);
            colorMap.put(colorName, carColor);
        }
        return carColor;
    }
}
