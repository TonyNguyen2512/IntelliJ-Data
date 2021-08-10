package UTIL;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.InputMismatchException;

public class Menu extends ArrayList<String> {

    Scanner sc = new Scanner(System.in);

    String title;

    public Menu(String title) {
        this.title = title;
    }


    //Add item to the menu List
    public void addItems(String item) {
        add(item);
    }

    //Print the menu
    public void printMenu(String exitDialogue) {
        if (isEmpty()) {
            System.out.println("Menu is Empty!");
        } else {
            int i = 0;
            System.out.println("\n" + title);
            for (String item : this) {
                System.out.println(String.format("[%d] %s", ++i, item));
            }
            System.out.println(String.format("Others: %s", exitDialogue));
        }
    }

    //Get User Choice
    public Integer getChoice() {
        Integer choice = 0;


        try {
            System.out.print("Input your choice here:  ");
            choice = sc.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Input must be an Integer!");
        }


        return choice;
    }
}