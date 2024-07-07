  package project;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.io.FileNotFoundException;
import java.time.LocalTime;
import java.util.List;
import org.junit.jupiter.api.Test;

public class AdHandlerTest {

    private static AdHandler adHandler;

    // TODO - Remove/comment method post Milestone 1

    // @Test
    // public void testAddImageToAd() throws FileNotFoundException {

    //     String actualOutput = AdHandler.addImageToAd("https://my.image.url/image1");

    //     assertNotNull(actualOutput);
    // }

    @Test
    public void testNewAdHandlerCreatesEmptyAdList() throws FileNotFoundException {
        adHandler = new AdHandler();

        assertEquals(0, adHandler.getAllAdsList().size());
    }

    @Test
    public void testAddingSingleAdCreatesOneAd() throws FileNotFoundException {
        // Given
        adHandler = new AdHandler();

        // When
        adHandler.addNewAd("Toys Set - 40pcs", "toy", 0);

        // Then
        assertEquals(1, adHandler.getAllAdsList().size());
    }

    @Test
    public void testAddingMultipleAdsCreatesMultipleAd() throws FileNotFoundException {
        // Given
        adHandler = new AdHandler();

        // When
        adHandler.addNewAd("Toys Set - 40pcs", "toy", 0);
        adHandler.addNewAd("Pulsar 200", "vehicle", 1);
        adHandler.addNewAd("Unbreakable Toys", "toy", 1);

        // Then
        assertEquals(3, adHandler.getAllAdsList().size());
    }

    // TODO - Milestone 2 - Uncomment below method

    @Test
    public void test5PMIsValidMeetingTime() {
        // * - For meetings hours: 10AM - 12PM, 2PM-3PM, 6PM-8PM
        // Given
        adHandler = new AdHandler();
        LocalTime datetime = LocalTime.of(17, 0);

        // When
        boolean actual = adHandler.isValidMeetingTime(datetime);

        // Then
        assertFalse(actual, "FALSE");

    }


    // TODO - Milestone 3 - Uncomment below method
     @Test
    public void testNewAdHandlerInitialisesMeetingList() {
        // Given
        adHandler = new AdHandler();

        // When
        List<Meeting> meetingsList = adHandler.getAllMeetings();

        // Then
        // TODO: Use assertNotNull to check the output of adHandler.getAllMeetings()
        assertNotNull(meetingsList);
    }

    // TODO - Milestone 3 - Uncomment below method
    @Test
    public void testInvalidAdTypeReturnsException() throws FileNotFoundException {
        adHandler = new AdHandler();

        String description = "Bullet Proof Contact Lenses";
        String type = "someInvalidType";
        int sellerID = 0;
 
        // TODO - Milestone 3
       RuntimeException re =  assertThrows(RuntimeException.class,() -> {
        adHandler.addNewAd(description,type,sellerID);});
        //  use assertThrows to check RuntimeException is thrown 
        // when type entered to adHandler.addNewAd() is not supported
        

        // TODO - Milestone 3 
        // use assertNotEquals to check length of exception message is not zero
        assertNotEquals(0, re.getMessage().length());
        
    }

    
    
}