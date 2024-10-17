package Objects;

import Objects.Cars.Hatchback;
import Objects.Cars.SUV;
import Objects.Cars.Sedan;

import java.util.Scanner;

public class TaxiFleetApp {
    private static final String DATA_FILE = "taxiFleet.dat";
    private TaxiFleet taxiFleet;
    private Scanner scanner;

    public TaxiFleetApp() {
        taxiFleet = new TaxiFleet();
        scanner = new Scanner(System.in);
    }

    public static void main(String[] args) {
        TaxiFleetApp app = new TaxiFleetApp();
        app.showMenu();
    }

    private void showMenu() {
        while (true) {
            System.out.println("\nМеню:");
            System.out.println("1. Добавить автомобиль");
            System.out.println("2. Отобразить автопарк");
            System.out.println("3. Фильтровать по цене");
            System.out.println("4. Фильтровать по типу кузова");
            System.out.println("5. Сбросить фильтры");
            System.out.println("6. Рассчитать общую стоимость");
            System.out.println("7. Выйти");
            System.out.print("Выберите пункт: ");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Пропустить перевод строки

            switch (choice) {
                case 1:
                    addCarToFleet();
                    break;
                case 2:
                    taxiFleet.displayFleet();
                    break;
                case 3:
                    applyPriceFilter();
                    break;
                case 4:
                    applyTypeFilter();
                    break;
                case 5:
                    taxiFleet.resetFilters();
                    System.out.println("Фильтры сброшены.");
                    break;
                case 6:
                    System.out.printf("Общая стоимость: %.3f%n", taxiFleet.calculateTotalCost());
                    break;
                case 7:
                    System.out.println("Выход...");
                    return;
                default:
                    System.out.println("Неверный пункт меню.");
            }
        }
    }

    private void addCarToFleet() {
        System.out.print("Введите модель автомобиля: ");
        String model = scanner.nextLine();
        System.out.print("Введите цену автомобиля: ");
        double price = scanner.nextDouble();
        scanner.nextLine(); // Пропустить перевод строки

        System.out.println("Выберите тип автомобиля:");
        System.out.println("1. Sedan");
        System.out.println("2. Hatchback");
        System.out.println("3. SUV");
        int carTypeChoice = scanner.nextInt();
        scanner.nextLine(); // Пропустить перевод строки

        Car car;
        switch (carTypeChoice) {
            case 1:
                System.out.print("Введите количество мест: ");
                int numberOfSeats = scanner.nextInt();
                car = new Sedan(model, price, numberOfSeats);
                break;
            case 2:
                System.out.print("Есть ли багажное отделение (true/false): ");
                boolean hasCargoSpace = scanner.nextBoolean();
                car = new Hatchback(model, price, hasCargoSpace);
                break;
            case 3:
                System.out.print("Введите клиренс автомобиля: ");
                double groundClearance = scanner.nextDouble();
                car = new SUV(model, price, groundClearance);
                break;
            default:
                System.out.println("Неверный выбор типа автомобиля.");
                return;
        }
        taxiFleet.addCar(car);
        System.out.println("Автомобиль добавлен в автопарк.");
    }

    private void applyPriceFilter() {
        System.out.print("Введите минимальную цену: ");
        double minPrice = scanner.nextDouble();
        System.out.print("Введите максимальную цену: ");
        double maxPrice = scanner.nextDouble();
        taxiFleet.filterByPrice(minPrice, maxPrice);
        System.out.println("Применен фильтр по цене.");
    }

    private void applyTypeFilter() {
        System.out.println("Выберите тип автомобиля для фильтрации: ");
        System.out.println("1. Sedan");
        System.out.println("2. Hatchback");
        System.out.println("3. SUV");
        int carTypeChoice = scanner.nextInt();
        scanner.nextLine(); // Пропустить перевод строки

        String carType;
        switch (carTypeChoice) {
            case 1:
                carType = "Sedan";
                break;
            case 2:
                carType = "Hatchback";
                break;
            case 3:
                carType = "SUV";
                break;
            default:
                System.out.println("Неверный выбор типа автомобиля.");
                return;
        }
        taxiFleet.filterByType(carType);
        System.out.println("Применен фильтр по типу кузова.");
    }
}
