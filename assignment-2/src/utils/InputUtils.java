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
        System.out.print(msg);
        return Integer.parseInt(scanner.nextLine());
    }

    public static double nextDouble(String msg) {
        System.out.print(msg);
        return Double.parseDouble(scanner.nextLine());
    }
}
