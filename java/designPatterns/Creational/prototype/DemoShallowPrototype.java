package designPatterns.Creational.prototype;

import designPatterns.Creational.prototype.shallow.Car;
import designPatterns.Creational.prototype.shallow.SUV;
import designPatterns.Creational.prototype.shallow.Sedan;

public class DemoShallowPrototype {

    public static void main(String[] args) {
        Car sedanPrototype = new Sedan("Luxury");
        Car suvPrototype = new SUV("Sport");

        Car sedanClone1 = sedanPrototype.clone();
        Car sedanClone2 = sedanPrototype.clone();

        Car suvClone1 = suvPrototype.clone();
        Car suvClone2 = suvPrototype.clone();

        sedanClone1.drive(); // Output: Driving a Luxury sedan.
        sedanClone2.drive(); // Output: Driving a Luxury sedan.

        suvClone1.drive(); // Output: Driving a Sport SUV.
        suvClone2.drive(); // Output: Driving a Sport SUV.
    }
}
