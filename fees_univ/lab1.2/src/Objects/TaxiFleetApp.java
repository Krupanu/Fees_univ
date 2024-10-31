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
            System.out.println("7. Сортировать по возрастанию");
            System.out.println("8. Сортировать по убыванию");
            System.out.println("9. Отменить сортировку");
            System.out.println("10. Выйти");
            System.out.print("Выберите пункт: ");

            try {
                int choice = Integer.parseInt(scanner.nextLine());

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
                        taxiFleet.sortAscending();
                        break;
                    case 8:
                        taxiFleet.sortDescending();
                        break;
                    case 9:
                        taxiFleet.cancelSorting();
                        break;
                    case 10:
                        System.out.println("Выход...");
                        return;
                    default:
                        System.out.println("Неверный пункт меню.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Ошибка ввода: введите целое число.");
            } catch (Exception e) {
                System.out.println("Произошла ошибка: " + e.getMessage());
            }
        }
    }

    private void addCarToFleet() {
        try {
            System.out.print("Введите модель автомобиля: ");
            String model = scanner.nextLine();
            System.out.print("Введите цену автомобиля: ");
            double price = Double.parseDouble(scanner.nextLine());

            System.out.println("Выберите тип автомобиля:");
            System.out.println("1. Sedan");
            System.out.println("2. Hatchback");
            System.out.println("3. SUV");
            int carTypeChoice = Integer.parseInt(scanner.nextLine());

            Car car;
            switch (carTypeChoice) {
                case 1:
                    System.out.print("Введите количество мест: ");
                    int numberOfSeats = Integer.parseInt(scanner.nextLine());
                    car = new Sedan(model, price, numberOfSeats);
                    break;
                case 2:
                    System.out.print("Есть ли багажное отделение (1 - да/2 - нет): ");
                    int hasCargoSpace = Integer.parseInt(scanner.nextLine());
                    car = new Hatchback(model, price, hasCargoSpace);
                    break;
                case 3:
                    System.out.print("Введите клиренс автомобиля: ");
                    double groundClearance = Double.parseDouble(scanner.nextLine());
                    car = new SUV(model, price, groundClearance);
                    break;
                default:
                    System.out.println("Неверный выбор типа автомобиля.");
                    return;
            }
            taxiFleet.addCar(car);
            System.out.println("Автомобиль добавлен в автопарк.");
        } catch (NumberFormatException e) {
            System.out.println("Ошибка ввода: неверный формат числа.");
        }
    }

    private void applyPriceFilter() {
        try {
            System.out.print("Введите минимальную цену: ");
            double minPrice = Double.parseDouble(scanner.nextLine());
            System.out.print("Введите максимальную цену: ");
            double maxPrice = Double.parseDouble(scanner.nextLine());
            taxiFleet.filterByPrice(minPrice, maxPrice);
            System.out.println("Применен фильтр по цене.");
        } catch (NumberFormatException e) {
            System.out.println("Ошибка ввода: неверный формат числа.");
        }
    }

    private void applyTypeFilter() {
        System.out.println("Выберите тип автомобиля для фильтрации: ");
        System.out.println("1. Sedan");
        System.out.println("2. Hatchback");
        System.out.println("3. SUV");

        try {
            int carTypeChoice = Integer.parseInt(scanner.nextLine());

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
        } catch (NumberFormatException e) {
            System.out.println("Ошибка ввода: введите целое число.");
        }
    }
}