package external.uber;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import external.uber.model.PriceEstimate;
import internal.helper.GoogleMapsHelper;

public class ExternalUberService {

  private static final String BASE_URL = "https://api.uber.com";
  private static final String PRICE_ESTIMATE_ENDPOINT = "/v1.2/estimates/price";

  private String startLatitude;
  private String startLongitude;
  private String endLatitude;
  private String endLongitude;

  @Autowired
  private RestTemplate restTemplate;
  
  @Autowired
  private GoogleMapsHelper googleMapsHelper;

  /**
   * Gets the price estimate of various Uber cabs given the start and end coordinates.
   *
   * @param startLatitude of type Double.
   * @param startLongitude of type Double.
   * @param endLatitude of type Double.
   * @param endLongitude of type Double.
   * @return Price estimate of various Uber cab types like UberGo, UberXL.
   * - If the latitude/longitude are invalid, returns an empty array.
   * - Handles any UberAPI related issues gracefully.
   */
  public PriceEstimate[] getPriceEstimates(Double startLatitude, Double startLongitude,
      Double endLatitude, Double endLongitude) {

    if (!googleMapsHelper.isValidLocations(startLatitude, startLongitude,
        endLatitude, endLongitude)) {
      return new PriceEstimate[0];
    }

    String priceEstimateBaseUrl = buildPriceEstimateBaseUrl(startLatitude, startLongitude,
        endLatitude, endLongitude);
  
    // Get data by making Uber API call.
    // RestTemplate in Java helps you call an API.
    PriceEstimate[] priceEstimates = restTemplate.getForObject(
        priceEstimateBaseUrl, PriceEstimate[].class);
    
    return priceEstimates;
  }

  public String buildPriceEstimateBaseUrl(Double startLatitude, Double startLongitude,
      Double endLatitude, Double endLongitude) {
    String urlParameters =
        String.format("?start_latitude=%f&start_longitude=%f&end_latitude=%f&end_longitude=%f",
            startLatitude, startLongitude, endLatitude, endLongitude);
    String apiUrl = new StringBuilder().append(BASE_URL)
        .append(PRICE_ESTIMATE_ENDPOINT)
        .append(urlParameters)
        .toString();

    return apiUrl;
  }

  // TODO: Uncomment in Milestone 6
  /*
  public PriceEstimate[] getPriceEstimatesWithBug(Double startLatitude, Double startLongitude,
    Double endLatitude, Double endLongitude) {

    if (!googleMapsHelper.isValidLocations(startLatitude, endLatitude, startLongitude,
        endLongitude)) {
      return new PriceEstimate[0];
    }

    // if (!googleMapsHelper.isValidLocations(startLatitude, startLongitude,
    //     endLatitude, endLongitude)) {
    //   return new PriceEstimate[0];
    // }
    
    String priceEstimateBaseUrl = buildPriceEstimateBaseUrl(startLatitude, startLongitude,
        endLatitude, endLongitude);

    // Get data by making Uber API call.
    // RestTemplate in Java helps you call an API.
    PriceEstimate[] priceEstimates = restTemplate.getForObject(
        priceEstimateBaseUrl, PriceEstimate[].class);

    return priceEstimates;
  }
  */

  public String getStartLatitude() {
    return startLatitude;
  }

  public void setStartLatitude(String startLatitude) {
    this.startLatitude = startLatitude;
  }

  public String getStartLongitude() {
    return startLongitude;
  }

  public void setStartLongitude(String startLongitude) {
    this.startLongitude = startLongitude;
  }

  public String getEndLatitude() {
    return endLatitude;
  }

  public void setEndLatitude(String endLatitude) {
    this.endLatitude = endLatitude;
  }

  public String getEndLongitude() {
    return endLongitude;
  }

  public void setEndLongitude(String endLongitude) {
    this.endLongitude = endLongitude;
  }

  public void setRestTemplate(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  public void setGoogleMapsHelper(GoogleMapsHelper googleMapsHelper) {
    this.googleMapsHelper = googleMapsHelper;
  }

  public GoogleMapsHelper getGoogleMapsHelper() {
    return googleMapsHelper;
  }
}