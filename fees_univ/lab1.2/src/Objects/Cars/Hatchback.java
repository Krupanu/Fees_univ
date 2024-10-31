package Objects.Cars;

import Objects.Car;

public class Hatchback extends Car {
    private static final long serialVersionUID = 1L;

    private int hasCargoSpace;

    public Hatchback(String model, double price, int hasCargoSpace) {
        super(model, price);
        this.hasCargoSpace = hasCargoSpace;
    }

    public boolean isHasCargoSpace() {
        return hasCargoSpace == 1;
    }

    @Override
    public void displayInfo() {
        System.out.println("Хэтчбек: " + getModel() + ", Цена: " + getPrice() + ", Багажник: " + (isHasCargoSpace() ? "Да" : "Нет"));
    }
}