package Objects;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TaxiFleet {
    private List<Car> cars;
    private List<Car> filteredCars;

    public TaxiFleet() {
        cars = new ArrayList<>();
        filteredCars = new ArrayList<>(cars);
    }

    public void addCar(Car car) {
        cars.add(car);
        resetFilters();
    }

    public List<Car> getCars() {
        return filteredCars.isEmpty() ? cars : filteredCars;
    }

    // Фильтрация по цене
    public void filterByPrice(double minPrice, double maxPrice) {
        filteredCars = cars.stream()
                .filter(car -> car.getPrice() >= minPrice && car.getPrice() <= maxPrice)
                .collect(Collectors.toList());
    }

    // Фильтрация по типу кузова
    public void filterByType(String carType) {
        filteredCars = cars.stream()
                .filter(car -> car.getClass().getSimpleName().equals(carType))
                .collect(Collectors.toList());
    }

    // Сброс всех фильтров
    public void resetFilters() {
        filteredCars = new ArrayList<>(cars);
    }

    // Отображение автопарка
    public void displayFleet() {
        List<Car> carsToDisplay = getCars();
        if (carsToDisplay.isEmpty()) {
            System.out.println("Нет автомобилей для отображения.");
        } else {
            carsToDisplay.forEach(Car::displayInfo);
        }
    }

    // Расчёт общей стоимости отфильтрованных автомобилей
    public double calculateTotalCost() {
        return getCars().stream().mapToDouble(Car::getPrice).sum();
    }
}
