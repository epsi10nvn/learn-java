package utils;

import java.util.Scanner;

public class InputUtils {
    private static Scanner scanner = new Scanner(System.in);

    private InputUtils() {

    }

    public static String nextLine(String msg) {
        System.out.print(msg);
        return scanner.nextLine();
    }

    public static int nextInt(String msg) {
        while (true) {
            try {
                System.out.print(msg);
                String input = scanner.nextLine().trim();
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a valid integer number.");
            }
        }
    }

    public static double nextDouble(String msg) {
        while (true) {
            try {
                System.out.print(msg);
                String input = scanner.nextLine().trim();
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a valid decimal number.");
            }
        }
    }
}
