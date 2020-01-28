package sample.Utility;

import sample.Models.Car;

public class ActiveCar {
    private static Car activeCar;

    public static Car getActiveCar() {
        return activeCar;
    }

    public static void setActiveCar(Car activeCar) {
        ActiveCar.activeCar = activeCar;
    }
}
