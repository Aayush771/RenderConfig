package project;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Seller {
    
    private static int count = 0;

    private String name;
    private int ID;

    public Seller(String name) {
        this.name = name;
        this.ID = count++;
    }

    public static List<String> readSellerList(Scanner scanner) throws FileNotFoundException {
        List<String> sellers = new ArrayList<>();

        while(scanner.hasNext()) {
            String sellerName = scanner.next().trim();
            sellers.add(sellerName);
        }

        return sellers;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getID() {
        return ID;
    }

    @Override
    public String toString() {
        return " [name=" + name + "]";
    }


}