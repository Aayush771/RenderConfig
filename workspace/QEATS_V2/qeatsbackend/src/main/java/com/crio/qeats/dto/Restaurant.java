
/*
 *
 * * Copyright (c) Crio.Do 2019. All rights reserved
 *
 */

package com.crio.qeats.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;


// TODO: CRIO_TASK_MODULE_SERIALIZATION
// Implement Restaurant class.
// Complete the class such that it produces the following JSON during serialization.
// {
// "restaurantId": "10",
// "name": "A2B",
// "city": "Hsr Layout",
// "imageUrl": "www.google.com",
// "latitude": 20.027,
// "longitude": 30.0,
// "opensAt": "18:00",
// "closesAt": "23:00",
// "attributes": [
// "Tamil",
// "South Indian"
// ]
// }

public class Restaurant {
    @JsonIgnore
    private String id;
    private String restaurantId;
    private String name;
    private String city;
    private String imageUrl;
    @NotNull
    @Max(90)
    @Min(-90)
    private Double latitude;
    @NotNull
    @Max(180)
    @Min(-180)
    private Double longitude;
    private String opensAt;
    private String closesAt;
    private List<String> attributes;

    public Restaurant() {}

    public Restaurant(String id, String restaurantId, String name, String city, String imageUrl,
            Double latitude, Double longitude, String opensAt, String closesAt,
            List<String> attributes) {
        this.id = id;
        this.restaurantId = restaurantId;
        this.name = name;
        this.city = city;
        this.imageUrl = imageUrl;
        this.latitude = latitude;
        this.longitude = longitude;
        this.opensAt = opensAt;
        this.closesAt = closesAt;
        this.attributes = attributes;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getOpensAt() {
        return opensAt;
    }

    public void setOpensAt(String opensAt) {
        this.opensAt = opensAt;
    }

    public String getClosesAt() {
        return closesAt;
    }

    public void setClosesAt(String closesAt) {
        this.closesAt = closesAt;
    }

    public List<String> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<String> attributes) {
        this.attributes = attributes;
    }

    @Override
    public String toString() {
        return "Restaurant [attributes=" + attributes + ", city=" + city + ", closesAt=" + closesAt
                + ", id=" + id + ", imageUrl=" + imageUrl + ", latitude=" + latitude
                + ", longitude=" + longitude + ", name=" + name + ", opensAt=" + opensAt
                + ", restaurantId=" + restaurantId + "]";
    }


}

