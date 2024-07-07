
/*
 *
 * * Copyright (c) Crio.Do 2019. All rights reserved
 *
 */

package com.crio.qeats.dto;

import lombok.Getter;
import lombok.Setter;
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
@Getter
@Setter
public class Restaurant {
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

    
    @Override
    public String toString() {
        return "Restaurant [attributes=" + attributes + ", city=" + city + ", closesAt=" + closesAt
                + ", id=" + id + ", imageUrl=" + imageUrl + ", latitude=" + latitude
                + ", longitude=" + longitude + ", name=" + name + ", opensAt=" + opensAt
                + ", restaurantId=" + restaurantId + "]";
    }


}

