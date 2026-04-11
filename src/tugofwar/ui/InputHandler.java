package tugofwar.ui;

import java.util.InputMismatchException;
import java.util.Scanner;

public class InputHandler {

    private final Scanner scanner = new Scanner(System.in);

    public int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                int value = Integer.parseInt(scanner.nextLine().trim());
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Input harus berupa angka.");
            }
        }
    }

    public String readLine(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    public char readAnswerOption(String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = scanner.nextLine().trim().toUpperCase();
            if (line.length() == 1) {
                char c = line.charAt(0);
                if (c == 'A' || c == 'B' || c == 'C' || c == 'D') {
                    return c;
                }
            }
            System.out.println("Input hanya A, B, C, atau D.");
        }
    }
}
