package external.uber;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.mockito.stubbing.Answer;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import external.uber.model.PriceEstimate;
import internal.helper.GoogleMapsHelper;


@ExtendWith(MockitoExtension.class)
// @MockitoSettings(strictness = Strictness.STRICT_STUBS)
public class ExternalUberServiceTest {

  @Mock
  private GoogleMapsHelper googleMapsHelperMock;
  @Mock
  private RestTemplate restTemplateMock;
  // @InjectMocks
  private ExternalUberService externalUberService;

  @Test
  public void getPriceEstimateBaseUrlTest() {
    // Pure Junit test
    /*
      Code Under Test: ExternalUberService.buildPriceEstimateBaseUrl() 
      Test case: Check if ExternalUberService.buildPriceEstimateBaseUrl() constructs the API
          url correctly from input coordinates 
      Dependency Layer/Function: None 
      Mock Setup: Not Applicable 
      Test Input: getUber.getPriceEstimates(some source and location coordinates) 
      Expected Test Output: API url constructed from input coordinates
    */

    Double startLatitude = 37.775231;
    Double startLongitude = -122.418075;
    Double endLatitude = 37.775241;
    Double endLongitude = -122.518075;

    ExternalUberService externalUberService = new ExternalUberService();
    String expectedUrl = "https://api.uber.com/v1.2/estimates/price"
        + "?start_latitude=37.775231&start_longitude=-122.418075" + "&end_latitude=37.775241&end_longitude=-122.518075";

    // Call the method under test
    String actualUrl = externalUberService.buildPriceEstimateBaseUrl(startLatitude, startLongitude, endLatitude,
        endLongitude);

    // Check if the method output matches expected value
    assertEquals(expectedUrl, actualUrl);
  }

  // @Test
  // public void getPriceEstimatesWithoutMockTest() {
  //   /*
  //     This test will fail and is written to demo need of Mockito
  //     Comment this out after checking it
  //   */

  //   // Setup
  //   Double startLatitude = 137.775231;
  //   Double startLongitude = -122.418075;
  //   Double endLatitude = 37.775241;
  //   Double endLongitude = -122.518075;
    
  //   ExternalUberService externalUberService = new ExternalUberService();

  //   // Get output of the method under test
  //   PriceEstimate[] actualPriceEstimates = externalUberService.getPriceEstimates(
  //     startLatitude, startLongitude, endLatitude, endLongitude);

  //   // Check if the returned value matches expected value
  //   int expectedLength = 0;
  //   assertEquals(expectedLength, actualPriceEstimates.length);
  // }

  @Test
  public void getPriceEstimatesReturnsEmptyArrayOnInvalidStartLatitudeTest() {
    /*
    Code Under Test: ExternalUberService.getPriceEstimates()
    Test case: Check if ExternalUberService.getPriceEstimates() returns empty
    array on invalid latitude for start location
    Dependency Layer/Function:
      1. googleMapsHelper.isValidLocation(String startLatitude, String startLongitude,
        String endLatitude, String endLongitude);
    Mock Setup:
      - Function to mock: googleMapsHelper.isValidLocation()
        - Input to mock: (some invalid start latitude, any start longitude, any end
            latitude, any end longitude)
        - Pre-set output from mock: false
    Test Input: externalUberService.getPriceEstimates()(parameters should be
    inline with your mock setup)
    Expected Test Output: Empty price estimate array
    */

    // Setup
    Double startLatitude = 137.775231;
    Double startLongitude = -122.418075;
    Double endLatitude = 37.775241;
    Double endLongitude = -122.518075;

    // Setup mock to return preset value of false
    // when startLatitude parameter is 137.775231
    ExternalUberService externalUberService = new ExternalUberService();
    externalUberService.setGoogleMapsHelper(googleMapsHelperMock);

    when(googleMapsHelperMock.isValidLocations(eq(137.775231), anyDouble(),
    anyDouble(), anyDouble()))
    .thenReturn(false);

    // get output of the method under test
    PriceEstimate[] actualPriceEstimates = externalUberService.getPriceEstimates(
    startLatitude, startLongitude, endLatitude, endLongitude);

    // check if the returned value matches expected value
    int expectedLength = 0;
    assertEquals(expectedLength, actualPriceEstimates.length);
  }

  @Test
  public void getPriceEstimatesValidLocationReturnsPriceEstimatesTest()
      throws JsonParseException, RestClientException, JsonMappingException, IOException {
    /*
    Code Under Test: ExternalUberService.getPriceEstimates()
    Test case: Check if ExternalUberService.getPriceEstimates() 
      returns data on valid location coordinates
    Dependency Layer/Function:
      1. googleMapsHelper.isValidLocation(String startLatitude, String startLongitude,
        String endLatitude, String endLongitude);
      2. restTemplate.getForObject(String url, Class<T> responseType)
    Mock Setup:
      - Function to mock: googleMapsHelper.isValidLocation()
        - Input to mock: (valid location coordinate)
        - Pre-set output from mock: true
      - Function to mock: restTemplate.getForObject()
        - Input to mock: (Url built from isValidLocation() arguments, 
            PriceEstimate[] reference)
        - Pre-set output from mock: Data read from price_estimate.json file
    Test Input: externalUberService.getPriceEstimates()(parameters should be
      inline with your mock setup)
    Expected Test Output: Non-empty array with required data
    */
    Double startLatitude = 37.775231;
    Double startLongitude = -122.418075;
    Double endLatitude = 37.775241;
    Double endLongitude = -122.518075;

    // Read API response from a file
    PriceEstimate[] expectedPriceEstimates = loadPriceEstimateList();
    String url = "https://api.uber.com/v1.2/estimates/price?start_latitude=37.775231&start_longitude=-122.418075&end_latitude=37.775241&end_longitude=-122.518075";

    ExternalUberService externalUberService = new ExternalUberService();
    externalUberService.setGoogleMapsHelper(googleMapsHelperMock);
    externalUberService.setRestTemplate(restTemplateMock);

    Answer<Boolean> validity = new Answer<Boolean>() {
      public Boolean answer(InvocationOnMock invocation)
          throws Throwable {
          
          Double startLatitude = invocation.getArgument(0);

          if (Math.abs(startLatitude) <= 90 ) {
            return true;
          } else {
            return false;
          }
      }
    };

    // Return the value of `validity` when the method of the Mock is invoked
    doAnswer(validity).when(googleMapsHelperMock).isValidLocations(eq(37.775231), anyDouble(), anyDouble(), anyDouble());
    // Return the PriceEstimate array if the method gets invoked with correct arguments
    when(restTemplateMock.getForObject(eq(url), eq(PriceEstimate[].class))).thenReturn(expectedPriceEstimates);

    PriceEstimate[] actualPriceEstimates = externalUberService.getPriceEstimates(startLatitude, startLongitude,
        endLatitude, endLongitude);

    assertEquals(3, actualPriceEstimates.length);
    assertEquals("₹110-140", actualPriceEstimates[0].getEstimate());
    assertEquals("₹110-130", actualPriceEstimates[1].getEstimate());
    assertEquals("₹210", actualPriceEstimates[2].getEstimate());

  }

  // TODO: Uncomment in Milestone 6
  /*
  @Test
  public void getPriceEstimatesWithBugTest()
      throws JsonParseException, RestClientException, JsonMappingException, IOException {
    
    // Code Under Test: externalUberService.getPriceEstimatesWithBug()
    Double startLatitude = 37.775231;
    Double startLongitude = -122.418075;
    Double endLatitude = 37.775241;
    Double endLongitude = -122.518075;

    // Read API response from a file
    PriceEstimate[] expectedPriceEstimates = loadPriceEstimateList();
    String url = "https://api.uber.com/v1.2/estimates/price?start_latitude=37.775231&start_longitude=-122.418075&end_latitude=37.775241&end_longitude=-122.518075";

    Answer<Boolean> validity = new Answer<Boolean>() {
      public Boolean answer(InvocationOnMock invocation)
          throws Throwable {
          
          Double startLatitude = invocation.getArgument(0);

          if (Math.abs(startLatitude) <= 90 ) {
            return true;
          } else {
            return false;
          }
      }
    };

    // Return the value of `validity` when the method of the Mock is invoked
    doAnswer(validity).when(googleMapsHelperMock).isValidLocations(eq(37.775231), anyDouble(), anyDouble(), anyDouble());
    // Return the PriceEstimate array if the method gets invoked with correct arguments
    when(restTemplateMock.getForObject(eq(url), eq(PriceEstimate[].class))).thenReturn(expectedPriceEstimates);

    PriceEstimate[] actualPriceEstimates = externalUberService.getPriceEstimatesWithBug(startLatitude, startLongitude,
        endLatitude, endLongitude);
  
    assertEquals(3, actualPriceEstimates.length);
    assertEquals("₹110-140", actualPriceEstimates[0].getEstimate());
    assertEquals("₹110-130", actualPriceEstimates[1].getEstimate());
    assertEquals("₹210", actualPriceEstimates[2].getEstimate());

    
    // Verify the isValidLocation is getting called with correct argumetns
    // verify(googleMapsHelperMock).isValidLocations(startLatitude, startLongitude,
    //     endLatitude, endLongitude);

    // Create a ArgumentCaptor object to capture and store a String argument
    // ArgumentCaptor<String> quantityCaptor = ArgumentCaptor.forClass(String.class);
    // verify(restTemplateMock).getForObject(quantityCaptor.capture(), eq(PriceEstimate[].class));

    // Compare the captured value (url) to the expected one
    // String actualUrl = quantityCaptor.getValue();
    // assertEquals(url, actualUrl);

  }
  */

  // TODO: Uncomment in Milestone 7
  /*
  @Test
  public void mockitoSetupErrorTest() {

    // Setup
    Double startLatitude = 137.775231;
    Double startLongitude = -122.418075;
    Double endLatitude = 37.775241;
    Double endLongitude = -122.518075;

    // Setup mock to return preset value of false
    // when startLatitude parameter is 137.775231
    ExternalUberService externalUberService = new ExternalUberService();
    externalUberService.setGoogleMapsHelper(googleMapsHelperMock);

    when(googleMapsHelperMock.isValidLocations(137.775231, -122.418075, 37.775241, -122.518075))
    .thenReturn(false);
    // when(googleMapsHelperMock.isValidLocations(any(), any(), any(), any()))
    // .thenReturn(false);
    // when(googleMapsHelperMock.isValidLocations(anyDouble(), anyDouble(), anyDouble(), anyDouble()))
    // .thenReturn(false);
    // when(googleMapsHelperMock.isValidLocations(eq(137.775231), anyDouble(), anyDouble(), anyDouble()))
    // .thenReturn(false);

    // get output of the method under test
    PriceEstimate[] actualPriceEstimates = externalUberService.getPriceEstimates(
    startLatitude, startLongitude, endLatitude, endLongitude);

    // check if the returned value matches expected value
    int expectedLength = 0;
    assertEquals(expectedLength, actualPriceEstimates.length);
  }
  */

  // TODO: Uncomment in Milestone 7
  /*
  @Test
  public void unnecessaryStubbingExceptionTest() throws JsonParseException, JsonMappingException, IOException {
    Double startLatitude = 37.775231;
    Double startLongitude = -122.418075;
    Double endLatitude = 37.775241;
    Double endLongitude = -122.518075;

    ExternalUberService externalUberService = new ExternalUberService();
    String expectedUrl = "https://api.uber.com/v1.2/estimates/price"
        + "?start_latitude=37.775231&start_longitude=-122.418075" 
        + "&end_latitude=37.775241&end_longitude=-122.518075";

    when(googleMapsHelperMock.isValidLocations(37.775231, -122.418075, 37.775241, -122.518075))
        .thenReturn(true);

    // Call the method under test
    String actualUrl = externalUberService.buildPriceEstimateBaseUrl(startLatitude, startLongitude, endLatitude,
        endLongitude);

    // Check if the method output matches expected value
    assertEquals(expectedUrl, actualUrl);
  }
  */


  /*
    Reads data from the price_estimate.json file for stubbing API response
  */
  private PriceEstimate[] loadPriceEstimateList() throws JsonParseException, JsonMappingException, IOException {
    File file = new File("src/test/resources/price_estimate.json");
    ObjectMapper objectMapper = new ObjectMapper();

    PriceEstimate[] priceEstimates = objectMapper.readValue(file, PriceEstimate[].class);
    return priceEstimates;

  }
}