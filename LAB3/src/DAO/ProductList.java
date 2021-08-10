package DAO;

import java.util.ArrayList;

import DTO.Product;
import UTIL.InputUtils;
import UTIL.MyValidation;
import UTIL.Menu;
import UTIL.PrintUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;

public class ProductList extends ArrayList<Product> {
    CategoryList catList;
    private static final String filename2 = "D:\\InteliJ Data\\LAB3\\product.txt";

    public ProductList loadFromFile() {
        String s;
        FileReader f = null;
        BufferedReader rf = null;
        ProductList proList = new ProductList();
        File f1 = new File(filename2);
        if (!f1.exists()) {
            try {
                f1.createNewFile();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        try {
            f = new FileReader(filename2);
            rf = new BufferedReader(f);

            while ((s = rf.readLine()) != null) {

                String[] arr = s.split("[;]");

                Product temp = new Product(arr[0], arr[1], arr[2], Double.parseDouble(arr[3]), Integer.parseInt(arr[4]));
                proList.add(temp);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("The file is not existed! Please save the data to file or create \"product.txt\" in project folder!");
        } finally {
            try {
                if (f != null) {
                    f.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return proList;
    }

    public static void writeProduct(ArrayList<Product> proList) {
        // check null list
        if (proList == null || proList.isEmpty()) return;

        PrintWriter w = null;

        try {
            w = new PrintWriter(filename2);
            for (Product Product : proList) {
                w.println(Product);
                w.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("The file is not existed! Please save the data to file or create \"product.txt\" in project folder!");
        } finally {
            try {
                if (w != null)
                    w.close();
            } catch (Exception e) {
                e.printStackTrace();
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

    public ProductList(CategoryList catList) {
        this.catList = catList;
        loadProduct();
    }

    public void add() {

        if (catList.isEmpty()) {
            System.out.println("Category List is Empty!");
            return;
        }
        while (true) {
            String id;
            String name;
            Double price;
            String categoryID;
            int quantity;

            while (true) {
                id = InputUtils.inputId("Input product ID", false);
                if (searchID(id) != -1) {
                    System.out.println("ID Existed! Try again");
                } else {
                    break;
                }
            }

            name = InputUtils.inputName(false);
            price = InputUtils.inputPrice(false);
            quantity = InputUtils.inputQuantity(false);
            while (true) {
                categoryID = InputUtils.inputId("Input category ID", false);
                if (catList.searchID(categoryID) == -1) {
                    System.out.println("Category ID does not exist!");
                } else {
                    break;
                }
            }

            Product newProduct = new Product(categoryID, id, name, price, quantity);
            PrintUtils.printProductInfo(newProduct);
            if (MyValidation.chooseYN("Do you want to add this product?", "Added Successful!", "Operation Aborted!")) {
                this.add(newProduct);
            }

            if (!MyValidation.chooseYN("Do you want to Continue Adding?", "", "Returning to main menu")) {
                return;
            }

        }
    }

    public void UDMenu() {
        Integer searchIndex;

        if (this.isEmpty()) {
            System.out.println("This List is Empty!");
            return;
        }

        String productId = InputUtils.inputId("Input product ID:", false);
        if ((searchIndex = searchID(productId)) == -1) {
            System.out.println("ID Doesnt Exist!");
            return;
        }

        Menu UDMenu = new Menu("Update And Delete Product ID");

        UDMenu.addItems("Update");
        UDMenu.addItems("Remove");

        while (true) {
            UDMenu.printMenu("Back to main menu");
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
                    System.out.println("Exiting Menu...");
                    return;

            }
        }
    }

    private void update(Integer searchIndex) {
        Menu updateMenu = new Menu("Update Menu");

        updateMenu.addItems("Update Name");
        updateMenu.addItems("Update Price");
        updateMenu.addItems("Update Category ID");
        updateMenu.addItems("Update Quantity");

        while (true) {
            updateMenu.printMenu("Return");
            Integer choice = updateMenu.getChoice();
            switch (choice) {
                case 1:
                    String name = InputUtils.inputName(true);
                    if (MyValidation.isEmptyString(name)) {
                        System.out.println("Name remain unchanged!");
                    } else {
                        this.get(searchIndex).setName(name);
                        System.out.println("Name Changed Completed!");
                    }
                    break;
                case 2:
                    Double price = InputUtils.inputPrice(true);
                    if (price <= 0) {
                        System.out.println("Price Remain Unchaged!");
                    } else {
                        this.get(searchIndex).setPrice(price);
                        System.out.println("Price Changed!");
                    }
                    break;
                case 3:
                    String categoryId = InputUtils.inputId("Input category ID:", true);
                    if (MyValidation.isEmptyString(categoryId)) {
                        System.out.println("Name remain unchanged!");
                    } else {
                        if (catList.searchID(categoryId) == -1) {
                            System.out.println("Category ID does not Exist! No change were made");
                        } else {
                            this.get(searchIndex).setCategoryID(categoryId);
                            System.out.println("Category Changed!");
                        }
                    }
                    break;
                case 4:
                    Integer quantity = InputUtils.inputQuantity(true);
                    if (quantity <= 0) {
                        System.out.println("Quantity Remain Unchaged!");
                    } else {
                        this.get(searchIndex).setQuantity(quantity);
                        System.out.println("Quantity Changed!");
                    }
                    break;
                default:
                    System.out.println("Exiting update menu...");
                    return;
            }
        }
    }

    private boolean delete(int searchIndex) {

        if (MyValidation.chooseYN("Are you you want to delete this Product?", "Product Deleted Successfully!", "Operation Aborted!")) {
            this.remove(searchIndex);
            return true;
        } else {
            return false;
        }
    }

    public void loadProduct() {
        ProductList fromFile = this.loadFromFile();
        for (Product P : fromFile) {
            this.add(P);
        }
    }

    public void saveFile() {

        if (this.isEmpty()) {
            System.out.println("Nothing to save!");
            return;
        }
        writeProduct(this);
        System.out.println("Saved");
    }

    public void print() {
        ProductList fromFile = this.loadFromFile();
        PrintUtils.printProductList(fromFile);
    }

    public ProductList(CategoryList catList, boolean isLoadFromFileNeeded) {
        this.catList = catList;
        loadProduct();
    }

    public ProductList() {
    }


}
