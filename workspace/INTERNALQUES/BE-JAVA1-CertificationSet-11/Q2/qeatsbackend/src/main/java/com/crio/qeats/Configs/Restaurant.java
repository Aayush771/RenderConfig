
/*
 *
 * * Copyright (c) Crio.Do 2019. All rights reserved
 *
 */

package com.crio.qeats.Configs;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
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
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Restaurant {

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


}

