package DAO;

import java.util.ArrayList;

import DTO.Category;
import UTIL.Menu;
import UTIL.MyValidation;
import UTIL.InputUtils;
import UTIL.PrintUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;

public class CategoryList extends ArrayList<Category> {

    private static final String filename1 = "D:\\InteliJ Data\\LAB3\\category.txt";

    public CategoryList loadFromFile() {
        String s;
        FileReader f = null;
        BufferedReader rf = null;
        CategoryList catList = new CategoryList();
        File f1 = new File(filename1);
        if (!f1.exists()) {
            try {
                f1.createNewFile();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        try {
            f = new FileReader(filename1);
            rf = new BufferedReader(f);

            while ((s = rf.readLine()) != null) {

                String[] arr = s.split("[;]");

                Category temp = new Category(arr[0], arr[1]);
                catList.add(temp);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("The file is not existed! Please save the data to file or create \"category.txt\" in project folder!");
        } finally {
            try {
                if (f != null) {
                    f.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return catList;
    }

    public static void writeCategory(ArrayList<Category> catList) {
        // check null list
        if (catList == null || catList.isEmpty()) {
            return;
        }

        PrintWriter w = null;

        try {
            w = new PrintWriter(filename1);
            for (Category Category : catList) {
                w.println(Category);
                w.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("The file is not existed! Please save the data to file or create \"category.txt\" in project folder!");
        } finally {
            try {
                if (w != null) {
                    w.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void add() {
        while (true) {
            String id = InputUtils.inputId("Input category ID:", false);
            String name = InputUtils.inputName(false);

            Integer isIdExisted = searchID(id);
            Integer isNameExisted = searchName(name);

            if (isIdExisted != -1 || isNameExisted != -1) {
                System.out.println("Name or Id Existed!");
            } else {
                Category newCat = new Category(id, name);
                PrintUtils.printCategoryInfo(newCat);
                if (MyValidation.chooseYN("Do you want to add this Category ?", "Added Successfully!",
                        "Operation Aborted!")) {
                    this.add(newCat);
                }
            }

            if (!MyValidation.chooseYN("Continue Adding More Category?", "", "Returning to menu")) {
                return;
            }

        }

    }

    public Integer searchID(String ID) {
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).getId().equals(ID)) {
                return i;
            }
        }
        return -1;
    }

    public Integer searchName(String name) {
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).getName().equals(name)) {
                return i;
            }
        }
        return -1;
    }

    public void updateDeleteMenu() {
        Integer searchIndex;
        if (this.isEmpty()) {
            System.out.println("The List is empty!");
            return;
        }

        String updateId = InputUtils.inputId("Input category ID:", false);
        if ((searchIndex = searchID(updateId)) == -1) {
            System.out.println("ID Does Not Exist! Exiting...");
            return;
        }

        Menu UDMenu = new Menu("Category Update And Delete Menu");

        UDMenu.addItems("Update");
        UDMenu.addItems("Delete");

        while (true) {
            UDMenu.printMenu("Return to menu");
            Integer choice = UDMenu.getChoice();
            switch (choice) {
                case 1:
                    update(searchIndex);
                    break;
                case 2:
                    if (delete(searchIndex)) {
                        return;
                    }
                    break;
                default:
                    System.out.println("Exiting...");
                    return;
            }
        }

    }

    private void update(int searchIndex) {
        String name = InputUtils.inputName(true);
        if (MyValidation.isEmptyString(name)) {
            System.out.println("Update Aborted!");
        } else {
            this.get(searchIndex).setName(name);
            System.out.println("Update Completed!");
        }
    }

    private boolean delete(int searchIndex) {
        if (MyValidation.chooseYN("Do you really want to delete this Category?", "Category Deleted!", "Delete Aborted")) {
            this.remove(searchIndex);
            return true;
        } else {
            return false;
        }
    }

    public void loadCategory() {
        CategoryList fromFile = this.loadFromFile();
        for (Category C : fromFile) {
            this.add(C);
        }
    }

    public void saveFile() {

        if (this.isEmpty()) {
            System.out.println("Nothing to save!");
            return;
        }
        writeCategory(this);
        System.out.println("Saved");
    }

    public void print() {
        CategoryList fromFile = this.loadFromFile();
        PrintUtils.printCategoryList(fromFile);
    }

    public CategoryList(boolean isLoadFromFileNeeded) {
        loadCategory();
    }

    public CategoryList() {

    }

}
