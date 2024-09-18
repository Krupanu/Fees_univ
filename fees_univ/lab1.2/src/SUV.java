public class SUV extends Car {
    private static final long serialVersionUID = 1L;

    private double groundClearance;

    public SUV(String model, double price, double groundClearance) {
        super(model, price);
        this.groundClearance = groundClearance;
    }

    public double getGroundClearance() {
        return groundClearance;
    }

    @Override
    public void displayInfo() {
        System.out.println("Внедорожник: " + getModel() + ", Цена: " + getPrice() + ", Клиренс: " + groundClearance + " см");
    }
}
