package UTIL;

import java.util.Scanner;

public class MyValidation {

    private static Scanner sc = new Scanner(System.in);

    public static boolean isName(String name) {
        char[] chars = name.toCharArray();

        for (char c : chars) {
            if (!Character.isLetter(c)) {
                return false;
            }
        }

        return true;
    }

    public static boolean isEmptyString(String string) {
        return string == null || string.isEmpty();
    }

    //Utility for checking returning yes or no with Question dialog
    public static boolean chooseYN(String questionDialog, String ifYes, String ifNo) {
        String userChoice = null;
        boolean choice = false;
        do {
            System.out.print(questionDialog + " (Y/N): ");
            userChoice = sc.nextLine().toUpperCase();
            if (userChoice.equals("N")) {
                System.out.println(ifNo);
                choice = false;
            } else if (userChoice.equals("Y")) {
                System.out.println(ifYes);
                choice = true;
            } else {
                System.out.println("Must be Y or N");
            }
        } while (!"N".equals(userChoice) && !"Y".equals(userChoice));

        return choice;
    }

    public static boolean isNumberNatural(Integer number) {
        return (number > 0) ? true : false;
    }

    public static boolean isPriceValid(Double price) {
        return (price > 0) ? true : false;
    }

}
