package Objects.Cars;

import Objects.Car;

public class Hatchback extends Car {
    private static final long serialVersionUID = 1L;

    private boolean hasCargoSpace;

    public Hatchback(String model, double price, boolean hasCargoSpace) {
        super(model, price);
        this.hasCargoSpace = hasCargoSpace;
    }

    public boolean isHasCargoSpace() {
        return hasCargoSpace;
    }

    @Override
    public void displayInfo() {
        System.out.println("Хэтчбек: " + getModel() + ", Цена: " + getPrice() + ", Багажник: " + (hasCargoSpace ? "Да" : "Нет"));
    }
}
