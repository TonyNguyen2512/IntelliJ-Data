package UTIL;

import java.util.InputMismatchException;
import java.util.Scanner;

public class InputUtils {
    private static Scanner sc = new Scanner(System.in);

    public static String inputId(String inputMsg, boolean IsReturnEmptyAllowed) {
        while (true) {
            sc = new Scanner(System.in);
            System.out.print(inputMsg + ": ");
            String id = sc.nextLine().trim();
            boolean isEmpty = MyValidation.isEmptyString(id);
            if (IsReturnEmptyAllowed && isEmpty) {
                return null;
            }
            if (isEmpty) {
                System.out.println("No Empty String Allowed!");
            } else {
                return id;
            }
        }
    }

    public static String inputName(boolean isReturnEmptyAllowed) {
        while (true) {
            sc = new Scanner(System.in);
            System.out.print("Input name: ");
            String name = sc.nextLine();
            boolean isEmpty = MyValidation.isEmptyString(name);
            if (isReturnEmptyAllowed && isEmpty) {
                return null;
            }
            if (isEmpty) {
                System.out.println("No Empty String Allowed!");
            } else if (!MyValidation.isName(name)) {
                System.out.println("String must be all Character");
            } else {
                return name;
            }
        }
    }

    public static Integer inputQuantity(boolean isReturnEmptyAllowed) {
        while (true) {
            Integer number;
            try {
                sc = new Scanner(System.in);
                System.out.print("Input Quantity: ");
                number = sc.nextInt();
                if (isReturnEmptyAllowed && number == 0) {
                    return 0;
                }
                boolean isNatural = MyValidation.isNumberNatural(number);

                if (!isNatural) {
                    System.out.println("Number must be natural");
                } else {
                    return number;
                }
            } catch (InputMismatchException e) {
                System.out.println("Input does not fit! ");
            }
        }
    }

    public static Double inputPrice(boolean isReturnEmptyAllowed) {
        while (true) {
            try {
                sc = new Scanner(System.in);
                System.out.print("Input Price: ");
                Double price = sc.nextDouble();
                if (isReturnEmptyAllowed && price == 0.0) {
                    return 0.0;
                }
                boolean isPriceValid = MyValidation.isPriceValid(price);
                if (isPriceValid) {
                    return price;
                } else {
                    System.out.println("Price Does Not Valid!");
                }

            } catch (InputMismatchException e) {
                System.out.println("Input does not fit! ");
            }
        }
    }

}
