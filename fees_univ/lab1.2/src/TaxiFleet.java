import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TaxiFleet implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<Car> cars;

    public TaxiFleet() {
        cars = new ArrayList<>();
    }

    public void addCar(Car car) {
        cars.add(car);
    }

    public List<Car> getCars() {
        return cars;
    }

    public double calculateTotalCost() {
        return cars.stream().mapToDouble(Car::getPrice).sum();
    }

    public void displayFleet() {
        if (cars.isEmpty()) {
            System.out.println("Автопарк пуст.");
            return;
        }
        cars.forEach(Car::displayInfo);
    }
}
