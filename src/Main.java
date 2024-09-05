import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        system.loadFromFile(); // сделать загрузку выгрузку из файла

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("1. Add cars");
            System.out.println("2. Set hierarchy of cars");
            System.out.println("3. Count down the price of cars");
            System.out.println("5. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:

                    break;
                case 2:

                    break;
                case 3:

                    break;
                case 4:

                    break;
                case 5:
                    return;
                default:
                    System.out.println("Wrong choice. Try again.");
            }
        }
    }
}
