package ra.module02.utils;

import java.math.BigDecimal;
import java.util.Scanner;

public class Input {
    private static Scanner sc = new Scanner(System.in);

    public static int getAnInteger(String inputInt) {
        while (true) {
            try {
                System.out.print(inputInt);
                String input = sc.nextLine().trim();

                if (input.isEmpty()) {
                    System.out.println("Không được để trống!");
                    continue;
                }

                return Integer.parseInt(input);

            } catch (NumberFormatException e) {
                System.out.println("Vui lòng nhập số hợp lệ!");
            }
        }
    }

    public static int getAnIntegerOptional(String inputInt, int oldValue) {
        while (true) {
            System.out.print(inputInt);
            String input = sc.nextLine().trim();

            if (input.isEmpty()) {
                return oldValue;
            }

            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Vui lòng nhập số hợp lệ!");
            }
        }
    }

    public static BigDecimal getBigDecimal(String inputBigDecimal) {
        while (true) {
            try {
                System.out.print(inputBigDecimal);
                String input = sc.nextLine().trim();

                if (input.isEmpty()) {
                    System.out.println("Không được để trống!");
                    continue;
                }

                return new BigDecimal(input);

            } catch (NumberFormatException e) {
                System.out.println("Vui lòng nhập số hợp lệ!");
            }
        }
    }

    public static BigDecimal getBigDecimalOptional(String inputBigDecimal, BigDecimal oldValue) {
        while (true) {
            System.out.print(inputBigDecimal);
            String input = sc.nextLine().trim();

            if (input.isEmpty()) {
                return oldValue;
            }

            try {
                return new BigDecimal(input);
            } catch (NumberFormatException e) {
                System.out.println("Giá không hợp lệ!");
            }
        }
    }

    public static String getPhone(String inputMsg) {
        while (true) {
            System.out.print(inputMsg);
            String phone = sc.nextLine().trim();

            if (phone.isEmpty()) {
                System.out.println("Không được để trống!");
                continue;
            }

            if (!phone.matches("^[0-9]{10}$")) {
                System.out.println("Số điện thoại phải là 10 chữ số!");
                continue;
            }

            return phone;
        }
    }

    public static String getPhoneOptional(String inputMsg, String oldValue) {
        while (true) {
            System.out.print(inputMsg);
            String phone = sc.nextLine().trim();

            if (phone.isEmpty()) {
                return oldValue;
            }

            if (!phone.matches("^[0-9]{10}$")) {
                System.out.println("Số điện thoại phải là 10 chữ số!");
                continue;
            }

            return phone;
        }
    }

    public static String getEmail(String inputMsg) {
        while (true) {
            System.out.print(inputMsg);
            String email = sc.nextLine().trim();

            if (email.isEmpty()) {
                System.out.println("Không được để trống!");
                continue;
            }

            if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
                System.out.println("Email không hợp lệ!");
                continue;
            }

            return email;
        }
    }

    public static String getEmailOptional(String inputMsg, String oldValue) {
        while (true) {
            System.out.print(inputMsg);
            String email = sc.nextLine().trim();

            if (email.isEmpty()) {
                return oldValue;
            }

            if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
                System.out.println("Email không hợp lệ!");
                continue;
            }

            return email;
        }
    }

    public static String getString(String inputString) {
        while (true) {
            System.out.print(inputString);
            String input = sc.nextLine().trim();

            if (input.isEmpty()) {
                System.out.println("Không được để trống!");
                continue;
            }
            return input;
        }
    }

    public static String getStringOptional(String inputString, String oldValue) {
        while (true) {
            System.out.print(inputString);
            String input = sc.nextLine().trim();

            if (input.isEmpty()) {
                return oldValue;
            }

            return input;
        }
    }


    public static boolean getConfirmation(String inputConfirm) {
        while (true) {
            System.out.print(inputConfirm + " (y/n): ");
            String input = sc.nextLine().trim().toLowerCase();
            if (input.equals("y") || input.equals("yes")) {
                return true;
            } else if (input.equals("n") || input.equals("no")) {
                return false;
            } else {
                System.out.println("Vui lòng nhập 'y' hoặc 'n'!");
            }
        }
    }

    public static void pressEnterToContinue() {
        System.out.print("\nNhấn Enter để tiếp tục...");
        sc.nextLine();
    }
}
