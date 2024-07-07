package project;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

class SellerTest {

    public static Scanner scanner;
    
    // TODO - Milestone 4 - Uncomment these methods
    
    // @Test
    // public void testSellersList() throws FileNotFoundException {
    //     // Given
    //     File typesFile = new File("src/test/resources/sellers.txt");
    //     scanner = new Scanner(typesFile);

    //     // When
    //     List<String> sellersList = Seller.readSellerList(scanner);

    //     // Then
    //     assertEquals(3, sellersList.size());

    //     scanner.close();
    // }

    // @Test
    // public void testSellersLargeList() throws FileNotFoundException {
    //     // Given
    //     File typesFile = new File("src/test/resources/sellersLarge.txt");
    //     scanner = new Scanner(typesFile);

    //     // When
    //     List<String> sellersList = Seller.readSellerList(scanner);

    //     // Then
    //     assertEquals(45, sellersList.size());

    //     scanner.close();
    // }
}
