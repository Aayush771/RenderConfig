/*
 *
 * * Copyright (c) Crio.Do 2019. All rights reserved
 *
 */

package com.crio.qeats.services;

import com.crio.qeats.dto.Restaurant;
import com.crio.qeats.exchanges.GetRestaurantsRequest;
import com.crio.qeats.exchanges.GetRestaurantsResponse;
import com.crio.qeats.repositoryservices.RestaurantRepositoryService;
import java.time.LocalTime;
import java.util.List;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class RestaurantServiceImpl implements RestaurantService {

    private final Double peakHoursServingRadiusInKms = 3.0;
    private final Double normalHoursServingRadiusInKms = 5.0;
    @Autowired
    private RestaurantRepositoryService restaurantRepositoryService;


    // TODO: CRIO_TASK_MODULE_RESTAURANTSAPI - Implement findAllRestaurantsCloseby.
    // Check RestaurantService.java file for the interface contract.
    @Override
    public GetRestaurantsResponse findAllRestaurantsCloseBy(
            GetRestaurantsRequest getRestaurantsRequest, LocalTime currentTime) {
        Double servingRadiusInKms;
    

        if (isPeakHour(currentTime)) {
            servingRadiusInKms = peakHoursServingRadiusInKms;
        } else {
            servingRadiusInKms = normalHoursServingRadiusInKms;
        }
        List<Restaurant> restaurants = restaurantRepositoryService.findAllRestaurantsCloseBy(
                getRestaurantsRequest.getLatitude(), getRestaurantsRequest.getLongitude(),
                currentTime, servingRadiusInKms);
        return new GetRestaurantsResponse(restaurants);
    }

    private boolean isTimeWithInRange(LocalTime timeNow, LocalTime startTime, LocalTime endTime) {
        return timeNow.isAfter(startTime) && timeNow.isBefore(endTime);
    }

    public boolean isPeakHour(LocalTime timeNow) {
        return isTimeWithInRange(timeNow, LocalTime.of(7, 59, 59), LocalTime.of(10, 00, 01))
                || isTimeWithInRange(timeNow, LocalTime.of(12, 59, 59), LocalTime.of(14, 00, 01))
                || isTimeWithInRange(timeNow, LocalTime.of(18, 59, 59), LocalTime.of(21, 00, 01));
    }



    // TODO: CRIO_TASK_MODULE_RESTAURANTSEARCH
    // Implement findRestaurantsBySearchQuery. The request object has the search string.
    // We have to combine results from multiple sources:
    // 1. Restaurants by name (exact and inexact)
    // 2. Restaurants by cuisines (also called attributes)
    // 3. Restaurants by food items it serves
    // 4. Restaurants by food item attributes (spicy, sweet, etc)
    // Remember, a restaurant must be present only once in the resulting list.
    // Check RestaurantService.java file for the interface contract.
    

}

