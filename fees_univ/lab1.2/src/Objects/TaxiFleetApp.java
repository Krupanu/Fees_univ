package Objects;

import Objects.Cars.Hatchback;
import Objects.Cars.SUV;
import Objects.Cars.Sedan;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TaxiFleetApp {
    private static final String DATA_FILE = "taxifleet.dat";
    private static List<User> users = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    private static TaxiFleet taxiFleet = null;

    public static void main(String[] args) {

        boolean exit = false;
        while (!exit) {
            showMenu();
            System.out.print("Выберите пункт меню: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    readFromFile();
                    break;
                case "2":
                    writeToFile();
                    break;
                case "3":
                    if (taxiFleet == null) {
                        System.out.println("Сначала необходимо загрузить данные из файла или создать новый автопарк.");
                    } else {
                        addCarToFleet();
                    }
                    break;
                case "4":
                    if (taxiFleet == null) {
                        System.out.println("Сначала необходимо загрузить данные из файла или создать новый автопарк.");
                    } else {
                        taxiFleet.displayFleet();
                    }
                    break;
                case "5":
                    if (taxiFleet == null) {
                        System.out.println("Сначала необходимо загрузить данные из файла или создать новый автопарк.");
                    } else {
                        double totalCost = taxiFleet.calculateTotalCost();
                        System.out.println("Общая стоимость автопарка: " + String.format("%.3f", totalCost));
                    }
                    break;
                case "0":
                    exit = true;
                    System.out.println("Выход из программы.");
                    break;
                default:
                    System.out.println("Неверный выбор. Попробуйте снова.");
            }
        }
    }

    private static void initializeUsers() {
        users.add(new User("admin", "admin123", "admin"));
        users.add(new User("user", "user123", "user"));
    }


    private static void showMenu() {
        System.out.println("\n=== Меню ===");
        System.out.println("1. Чтение из файла");
        System.out.println("2. Запись в файл");
        System.out.println("3. Добавить автомобиль в автопарк");
        System.out.println("4. Показать автопарк");
        System.out.println("5. Рассчитать стоимость автопарка");
        System.out.println("0. Выход");
    }

    private static void readFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(DATA_FILE))) {
            taxiFleet = (TaxiFleet) ois.readObject();
            System.out.println("Данные успешно загружены из файла.");
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден. Создается новый автопарк.");
            taxiFleet = new TaxiFleet();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Ошибка при чтении файла: " + e.getMessage());
            taxiFleet = new TaxiFleet();
        }
    }

    private static void writeToFile() {
        if (taxiFleet == null) {
            System.out.println("Нет данных для записи. Сначала создайте автопарк или загрузите данные.");
            return;
        }
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(DATA_FILE))) {
            oos.writeObject(taxiFleet);
            System.out.println("Данные успешно сохранены в файл.");
        } catch (IOException e) {
            System.out.println("Ошибка при записи файла: " + e.getMessage());
        }
    }

    private static void addCarToFleet() {
        System.out.println("\n=== Добавить автомобиль ===");
        System.out.println("Выберите тип автомобиля:");
        System.out.println("1. Седан");
        System.out.println("2. Хэтчбек");
        System.out.println("3. Внедорожник");
        System.out.print("Ваш выбор: ");
        String typeChoice = scanner.nextLine();

        System.out.print("Модель автомобиля: ");
        String model = scanner.nextLine();

        double price = 0;
        while (true) {
            System.out.print("Цена автомобиля: ");
            String priceInput = scanner.nextLine();
            try {
                price = Double.parseDouble(priceInput);
                if (price < 0) {
                    throw new NumberFormatException("Цена не может быть отрицательной.");
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Неверный ввод цены. Попробуйте снова.");
            }
        }

        switch (typeChoice) {
            case "1":
                int seats = 0;
                while (true) {
                    System.out.print("Количество мест: ");
                    String seatsInput = scanner.nextLine();
                    try {
                        seats = Integer.parseInt(seatsInput);
                        if (seats <= 0) {
                            throw new NumberFormatException("Количество мест должно быть положительным.");
                        }
                        break;
                    } catch (NumberFormatException e) {
                        System.out.println("Неверный ввод. Попробуйте снова.");
                    }
                }
                taxiFleet.addCar(new Sedan(model, price, seats));
                break;
            case "2":
                boolean hasCargoSpace = false;
                while (true) {
                    System.out.print("Есть багажник? (да/нет): ");
                    String cargoInput = scanner.nextLine().toLowerCase();
                    if (cargoInput.equals("да")) {
                        hasCargoSpace = true;
                        break;
                    } else if (cargoInput.equals("нет")) {
                        hasCargoSpace = false;
                        break;
                    } else {
                        System.out.println("Неверный ввод. Введите 'да' или 'нет'.");
                    }
                }
                taxiFleet.addCar(new Hatchback(model, price, hasCargoSpace));
                break;
            case "3":
                double clearance = 0;
                while (true) {
                    System.out.print("Клиренс (см): ");
                    String clearanceInput = scanner.nextLine();
                    try {
                        clearance = Double.parseDouble(clearanceInput);
                        if (clearance < 0) {
                            throw new NumberFormatException("Клиренс не может быть отрицательным.");
                        }
                        break;
                    } catch (NumberFormatException e) {
                        System.out.println("Неверный ввод. Попробуйте снова.");
                    }
                }
                taxiFleet.addCar(new SUV(model, price, clearance));
                break;
            default:
                System.out.println("Неверный тип автомобиля.");
                return;
        }
        System.out.println("Автомобиль успешно добавлен в автопарк.");
    }
}
