package internal.helper;

public interface GoogleMapsHelper {
  /**
   * Validates the correctness of the starting and ending location coordinates
   *
   * @param startLatitude of type Double.
   * @param startLongitude of type Double.
   * @param endLatitude of type Double.
   * @param endLongitude of type Double.
   * @return true if coordinates given are all valid,
   * false if any of the coordinate values are invalid
   * Valid Latitude range -> -90 to +90
   * Valid Longitude range -> -180 to +180
   */
  public boolean isValidLocations(Double startLatitude, Double startLongitude,
      Double endLatitude, Double endLongitude);

  /**
   * Makes payment for the Uber cab booked via Google Maps
   *
   * @param tripID of type String
   * @param otp of type String
   * @return true if the payment was success,
   * false if the payment failed
   */
  public boolean makeUberPayment(String tripID, String otp);
}