package UTIL;

import DAO.CategoryList;
import DAO.ProductList;
import DTO.Category;
import DTO.Product;

public interface PrintUtils {

    public static void printCategoryInfo(Category newCat) {
        System.out.println("=================================================");
        System.out.println("Category Info: ");
        System.out.println("ID  : " + newCat.getId());
        System.out.println("Name: " + newCat.getName());
        System.out.println("=================================================");
    }

    public static void printProductInfo(Product prod) {
        System.out.println("=================================================");
        System.out.println("Product Info: ");
        System.out.println("ID   : " + prod.getId());
        System.out.println("Name : " + prod.getName());
        System.out.println("Price: " + prod.getPrice());
        System.out.println("Quantity: " + prod.getQuantity());
        System.out.println("Category Id: " + prod.getCategoryID());
        System.out.println("=================================================");
    }

    public static void printProductList(ProductList prodList) {
        Integer i = 0;
        System.out.println(String.format("| %5s | %10s | %5s", "No", "Name", "Price"));
        for (Product prod : prodList) {
            i++;
            System.out.println(String.format("| %5d | %10s | %5.2f", i, prod.getName(), prod.getPrice()));
        }
    }

    public static void printCategoryList(CategoryList catList) {
        Integer i = 0;
        System.out.println(String.format("| %5s | %10s |", "No", "Name"));
        for (Category cat : catList) {
            System.out.println(String.format("| %5d | %10s |", cat.getId(), cat.getName()));
        }
    }


}