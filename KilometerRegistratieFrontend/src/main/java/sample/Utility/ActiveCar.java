package sample.Utility;

import sample.Models.Car;

public class ActiveCar {
    private static Car auto;

    public static Car getAuto() {
        return auto;
    }

    public static void setAuto(Car auto) {
        ActiveCar.auto = auto;
    }
}
