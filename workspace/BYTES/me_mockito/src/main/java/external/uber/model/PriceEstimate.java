package external.uber.model;

// This class is used to deserialize the JSON response from UberAPI.
// The format of PriceEstimate from Uber API response looks like the following.
// Note the snake case in the key-value pairs.
// {
//     "localized_display_name": "UberGo",
//     "distance": 6.17,
//     "display_name": "UberGo",
//     "product_id": "26546650-e557-4a7b-86e7-6a3942445247",
//     "high_estimate": 140,
//     "low_estimate": 110,
//     "duration": 1080,
//     "estimate": "₹110-140",
//     "currency_code": "INR"
// }
public class PriceEstimate {
  private String localized_display_name;
  private Double distance;
  private String display_name;
  private String product_id;
  private Integer high_estimate;
  private Integer low_estimate;
  private Integer duration;
  private String estimate;
  private String currency_code;

  // Getters/Setters for the variables. Safely ignore.
  public String getLocalized_display_name() {
    return localized_display_name;
  }

  public void setLocalized_display_name(String localized_display_name) {
    this.localized_display_name = localized_display_name;
  }

  public Double getDistance() {
    return distance;
  }

  public void setDistance(Double distance) {
    this.distance = distance;
  }

  public String getDisplay_name() {
    return display_name;
  }

  public void setDisplay_name(String display_name) {
    this.display_name = display_name;
  }

  public String getProduct_id() {
    return product_id;
  }

  public void setProduct_id(String product_id) {
    this.product_id = product_id;
  }

  public Integer getHigh_estimate() {
    return high_estimate;
  }

  public void setHigh_estimate(Integer high_estimate) {
    this.high_estimate = high_estimate;
  }

  public Integer getLow_estimate() {
    return low_estimate;
  }

  public void setLow_estimate(Integer low_estimate) {
    this.low_estimate = low_estimate;
  }

  public Integer getDuration() {
    return duration;
  }

  public void setDuration(Integer duration) {
    this.duration = duration;
  }

  public String getEstimate() {
    return estimate;
  }

  public void setEstimate(String estimate) {
    this.estimate = estimate;
  }

  public String getCurrency_code() {
    return currency_code;
  }

  public void setCurrency_code(String currency_code) {
    this.currency_code = currency_code;
  }

}