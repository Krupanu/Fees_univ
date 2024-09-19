package Objects.Cars;

import Objects.Car;

public class Sedan extends Car {
    private static final long serialVersionUID = 1L;

    private int numberOfSeats;

    public Sedan(String model, double price, int numberOfSeats) {
        super(model, price);
        this.numberOfSeats = numberOfSeats;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    @Override
    public void displayInfo() {
        System.out.println("Седан: " + getModel() + ", Цена: " + getPrice() + ", Мест: " + numberOfSeats);
    }
}
