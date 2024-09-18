import java.io.Serializable;

public abstract class Car implements Serializable {
    private static final long serialVersionUID = 1L;

    private String model;
    private double price;

    public Car(String model, double price) {
        this.model = model;
        this.price = price;
    }

    public String getModel() {
        return model;
    }

    public double getPrice() {
        return price;
    }

    public abstract void displayInfo();
}
