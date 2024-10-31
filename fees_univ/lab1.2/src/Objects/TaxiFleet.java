package Objects;

import Objects.Cars.Hatchback;
import Objects.Cars.SUV;
import Objects.Cars.Sedan;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

public class TaxiFleet {
    private List<Car> cars;
    private List<Car> filteredCars;
    private final AtomicBoolean isSortingCancelled = new AtomicBoolean(false);

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

    public void filterByPrice(double minPrice, double maxPrice) {
        filteredCars = cars.stream()
                .filter(car -> car.getPrice() >= minPrice && car.getPrice() <= maxPrice)
                .collect(Collectors.toList());
    }

    public void filterByType(String carType) {
        filteredCars = cars.stream()
                .filter(car -> car.getClass().getSimpleName().equals(carType))
                .collect(Collectors.toList());
    }

    public void resetFilters() {
        filteredCars = new ArrayList<>(cars);
    }

    public void displayFleet() {
        List<Car> carsToDisplay = getCars();
        if (carsToDisplay.isEmpty()) {
            System.out.println("Нет автомобилей для отображения.");
        } else {
            carsToDisplay.forEach(Car::displayInfo);
        }
    }

    public double calculateTotalCost() {
        return getCars().stream().mapToDouble(Car::getPrice).sum();
    }

    public void sortAscending() {
        new Thread(() -> {
            isSortingCancelled.set(false);
            List<Car> sortedCars = new ArrayList<>(getCars());
            sortedCars.sort(Comparator.comparingDouble(Car::getPrice));

            if (!isSortingCancelled.get()) {
                filteredCars = sortedCars;
                System.out.println("Сортировка по возрастанию выполнена.");
            } else {
                System.out.println("Сортировка по возрастанию отменена.");
            }
        }).start();
    }

    public void sortDescending() {
        Thread descendingThread = new Thread(() -> {
            isSortingCancelled.set(false);
            List<Car> sortedCars = new ArrayList<>(getCars());
            sortedCars.sort(Comparator.comparingDouble(Car::getPrice).reversed());

            if (!isSortingCancelled.get()) {
                filteredCars = sortedCars;
                System.out.println("Сортировка по убыванию выполнена.");
            } else {
                System.out.println("Сортировка по убыванию отменена.");
            }
        });
        descendingThread.start();
    }

    public void cancelSorting() {
        isSortingCancelled.set(true);
    }
}
