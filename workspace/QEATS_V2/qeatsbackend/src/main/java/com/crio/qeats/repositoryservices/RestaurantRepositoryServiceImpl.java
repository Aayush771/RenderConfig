/*
 *
 * * Copyright (c) Crio.Do 2019. All rights reserved
 *
 */

package com.crio.qeats.repositoryservices;

import ch.hsr.geohash.GeoHash;
import com.crio.qeats.configs.RedisConfiguration;
import com.crio.qeats.dto.Restaurant;
import com.crio.qeats.models.ItemEntity;
import com.crio.qeats.models.MenuEntity;
import com.crio.qeats.models.RestaurantEntity;
import com.crio.qeats.repositories.ItemRepository;
import com.crio.qeats.repositories.MenuRepository;
import com.crio.qeats.repositories.RestaurantRepository;
import com.crio.qeats.utils.GeoLocation;
import com.crio.qeats.utils.GeoUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import javax.inject.Provider;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;


@Service
@Primary
public class RestaurantRepositoryServiceImpl implements RestaurantRepositoryService {



  @Autowired
  private RestaurantRepository restaurantRepository;
  @Autowired
  private RedisConfiguration redisConfiguration;
  @Autowired
  private ItemRepository itemRepository;
  @Autowired
  MenuRepository menuRepository;


  @Autowired
  private Provider<ModelMapper> modelMapperProvider;
  @Autowired
  private ObjectMapper objectMapper;

  private boolean isOpenNow(LocalTime time, RestaurantEntity res) {
    LocalTime openingTime = LocalTime.parse(res.getOpensAt());
    LocalTime closingTime = LocalTime.parse(res.getClosesAt());

    return time.isAfter(openingTime) && time.isBefore(closingTime);
  }

  private Restaurant convertToDto(RestaurantEntity restaurantEntity) {
    ModelMapper modelMapper = modelMapperProvider.get();
    return modelMapper.map(restaurantEntity, Restaurant.class);
  }

  // TODO: CRIO_TASK_MODULE_NOSQL
  // Objectives:
  // 1. Implement findAllRestaurantsCloseby.
  // 2. Remember to keep the precision of GeoHash in mind while using it as a key.
  // Check RestaurantRepositoryService.java file for the interface contract.
  public List<Restaurant> findAllRestaurantsCloseBy(Double latitude, Double longitude,
      LocalTime currentTime, Double servingRadiusInKms) {
    GeoHash geoHash = GeoHash.withCharacterPrecision(latitude, longitude, 7);
    String redisKey = geoHash.toBase32();
    try (Jedis jedis = redisConfiguration.getJedisPool().getResource()) {
      String cachedData = jedis.get(redisKey);
      if (cachedData != null) {
        return parseCachedData(cachedData);
      }


      List<Restaurant> restaurants = new ArrayList<>();


      List<RestaurantEntity> restaurantEntities = restaurantRepository.findAll();
      // for(RestaurantEntity i: restaurantEntities){
      // String name = i.getName().replaceAll("é", "e");
      // i.setName(name);
      // }
      for (RestaurantEntity restaurantEntity : restaurantEntities) {
        if (isRestaurantCloseByAndOpen(restaurantEntity, currentTime, latitude, longitude,
            servingRadiusInKms)) {
          restaurants.add(convertToDto(restaurantEntity));
        }
      }
      jedis.setex(redisKey, RedisConfiguration.REDIS_ENTRY_EXPIRY_IN_SECONDS,
          objectMapper.writeValueAsString(restaurants));


      // TODO: CRIO_TASK_MODULE_REDIS
      // We want to use cache to speed things up. Write methods that perform the same functionality,
      // but using the cache if it is present and reachable.
      // Remember, you must ensure that if cache is not present, the queries are directed at the
      // database instead.


      // CHECKSTYLE:OFF
      // CHECKSTYLE:ON


      return restaurants;
    } catch (JsonProcessingException e) {
      // Handle exception, e.g., log and return an empty JSON array
      return Collections.emptyList();
    }
  }

  private List<Restaurant> parseCachedData(String cachedData) {
    try {
      return objectMapper.readValue(cachedData, new TypeReference<List<Restaurant>>() {});
    } catch (IOException e) {
      // Handle exception, e.g., log and return an empty list
      return Collections.emptyList();
    }
  }



  // TODO: CRIO_TASK_MODULE_NOSQL
  // Objective:
  // 1. Check if a restaurant is nearby and open. If so, it is a candidate to be returned.
  // NOTE: How far exactly is "nearby"?


  // TODO: CRIO_TASK_MODULE_RESTAURANTSEARCH
  // Objective:
  // Find restaurants whose names have an exact or partial match with the search query.
  @Override
  public List<Restaurant> findRestaurantsByName(Double latitude, Double longitude,
      String searchString, LocalTime currentTime, Double servingRadiusInKms) {
    Set<String> restaurantSet = new HashSet<>();
    List<Restaurant> restaurantList = new ArrayList<>();


    Optional<List<RestaurantEntity>> optionalExactRestaurantEntityList =
        restaurantRepository.findRestaurantsByNameExact(searchString);

    if (optionalExactRestaurantEntityList.isPresent()) {
      List<RestaurantEntity> restaurantEntityList = optionalExactRestaurantEntityList.get();
      for (RestaurantEntity restaurantEntity : restaurantEntityList) {
        if (isRestaurantCloseByAndOpen(restaurantEntity, currentTime, latitude, longitude,
            servingRadiusInKms) && !restaurantSet.contains(restaurantEntity.getRestaurantId())) {
          restaurantList.add(convertToDto(restaurantEntity));
          restaurantSet.add(restaurantEntity.getRestaurantId());
        }
      }

    }
    Optional<List<RestaurantEntity>> optionalInexactRestaurantEntityList =
        restaurantRepository.findRestaurantsByName(searchString);

    if (optionalInexactRestaurantEntityList.isPresent()) {
      List<RestaurantEntity> restaurantEntityList = optionalInexactRestaurantEntityList.get();
      for (RestaurantEntity restaurantEntity : restaurantEntityList) {
        if (isRestaurantCloseByAndOpen(restaurantEntity, currentTime, latitude, longitude,
            servingRadiusInKms) && !restaurantSet.contains(restaurantEntity.getRestaurantId())) {
          restaurantList.add(convertToDto(restaurantEntity));
          restaurantSet.add(restaurantEntity.getRestaurantId());
        }
      }
    }

    return restaurantList;


  }



  // TODO: CRIO_TASK_MODULE_RESTAURANTSEARCH
  // Objective:
  // Find restaurants whose attributes (cuisines) intersect with the search query.
  @Override
  public List<Restaurant> findRestaurantsByAttributes(Double latitude, Double longitude,
      String searchString, LocalTime currentTime, Double servingRadiusInKms) {

    Set<String> restaurantSet = new HashSet<>();
    List<Restaurant> restaurantList = new ArrayList<>();


    Optional<List<RestaurantEntity>> optionalExactRestaurantEntityList =
        restaurantRepository.findByAttributesInIgnoreCase(searchString);

    if (optionalExactRestaurantEntityList.isPresent()) {
      List<RestaurantEntity> restaurantEntityList = optionalExactRestaurantEntityList.get();
      for (RestaurantEntity restaurantEntity : restaurantEntityList) {
        if (isRestaurantCloseByAndOpen(restaurantEntity, currentTime, latitude, longitude,
            servingRadiusInKms) && !restaurantSet.contains(restaurantEntity.getRestaurantId())) {
          restaurantList.add(convertToDto(restaurantEntity));
          restaurantSet.add(restaurantEntity.getRestaurantId());
        }
      }

    }

    return restaurantList;
  }



  // TODO: CRIO_TASK_MODULE_RESTAURANTSEARCH
  // Objective:
  // Find restaurants which serve food items whose names form a complete or partial match
  // with the search query.

  @Override
  public List<Restaurant> findRestaurantsByItemName(Double latitude, Double longitude,
      String searchString, LocalTime currentTime, Double servingRadiusInKms) {
    Set<String> restaurantSet = new HashSet<>();
    List<Restaurant> restaurantList = new ArrayList<>();
    List<ItemEntity> itemEntities = itemRepository.findItemsByNameExact(searchString).get();
    List<String> itemIdList =
        itemEntities.stream().map(i -> i.getId()).collect(Collectors.toList());
    List<MenuEntity> menuEntities = menuRepository.findMenusByItemsItemIdIn(itemIdList).get();
    List<Optional<RestaurantEntity>> restaurantEntities = menuEntities.stream()
        .map(i -> restaurantRepository.findById(i.getRestaurantId())).collect(Collectors.toList());

    for (Optional<RestaurantEntity> restaurantEntitys : restaurantEntities) {
      if (restaurantEntitys.isPresent()) {
        RestaurantEntity restaurantEntity = restaurantEntitys.get();
        if (isRestaurantCloseByAndOpen(restaurantEntity, currentTime, latitude, longitude,
            servingRadiusInKms) && !restaurantSet.contains(restaurantEntity.getRestaurantId())) {
          restaurantList.add(convertToDto(restaurantEntity));
          restaurantSet.add(restaurantEntity.getRestaurantId());
        }
      }
    }
    List<ItemEntity> itemEntities1 = itemRepository.findItemsByNameInexact(searchString).get();
    List<String> itemIdList1 =
        itemEntities1.stream().map(i -> i.getId()).collect(Collectors.toList());
    List<MenuEntity> menuEntities1 = menuRepository.findMenusByItemsItemIdIn(itemIdList1).get();
    List<Optional<RestaurantEntity>> restaurantEntities1 = menuEntities1.stream()
        .map(i -> restaurantRepository.findById(i.getRestaurantId())).collect(Collectors.toList());

    for (Optional<RestaurantEntity> restaurantEntitys : restaurantEntities1) {
      if (restaurantEntitys.isPresent()) {
        RestaurantEntity restaurantEntity = restaurantEntitys.get();
        if (isRestaurantCloseByAndOpen(restaurantEntity, currentTime, latitude, longitude,
            servingRadiusInKms) && !restaurantSet.contains(restaurantEntity.getRestaurantId())) {
          restaurantList.add(convertToDto(restaurantEntity));
          restaurantSet.add(restaurantEntity.getRestaurantId());
        }
      }
    }


    return restaurantList;
  }

  // TODO: CRIO_TASK_MODULE_RESTAURANTSEARCH
  // Objective:
  // Find restaurants which serve food items whose attributes intersect with the search query.
  @Override
  public List<Restaurant> findRestaurantsByItemAttributes(Double latitude, Double longitude,
      String searchString, LocalTime currentTime, Double servingRadiusInKms) {
    Set<String> restaurantSet = new HashSet<>();
    List<Restaurant> restaurantList = new ArrayList<>();
    List<ItemEntity> itemEntity = itemRepository.findByAttributesInIgnoreCase(searchString).get();
    List<String> itemIdList = itemEntity.stream().map(i -> i.getId()).collect(Collectors.toList());
    List<MenuEntity> menuEntity = menuRepository.findMenusByItemsItemIdIn(itemIdList).get();
    List<Optional<RestaurantEntity>> restaurantEntities = menuEntity.stream()
        .map(i -> restaurantRepository.findById(i.getRestaurantId())).collect(Collectors.toList());
    for (Optional<RestaurantEntity> restaurantEntitys : restaurantEntities) {
      if (restaurantEntitys.isPresent()) {
        RestaurantEntity restaurantEntity = restaurantEntitys.get();
        if (isRestaurantCloseByAndOpen(restaurantEntity, currentTime, latitude, longitude,
            servingRadiusInKms) && !restaurantSet.contains(restaurantEntity.getRestaurantId())) {
          restaurantList.add(convertToDto(restaurantEntity));
          restaurantSet.add(restaurantEntity.getRestaurantId());
        }
      }
    }

    return restaurantList;
  }

  public List<Restaurant> findRestaurantsByNameExact(String name) {
    List<RestaurantEntity> restaurantEntities =
        restaurantRepository.findRestaurantsByName(name).get();

    List<Restaurant> restaurants =
        restaurantEntities.stream().map(i -> convertToDto(i)).collect(Collectors.toList());

    return restaurants;
  }



  /**
   * Utility method to check if a restaurant is within the serving radius at a given time.
   * 
   * @return boolean True if restaurant falls within serving radius and is open, false otherwise
   */
  private boolean isRestaurantCloseByAndOpen(RestaurantEntity restaurantEntity,
      LocalTime currentTime, Double latitude, Double longitude, Double servingRadiusInKms) {
    if (isOpenNow(currentTime, restaurantEntity)) {
      return GeoUtils.findDistanceInKm(latitude, longitude, restaurantEntity.getLatitude(),
          restaurantEntity.getLongitude()) <= servingRadiusInKms;
    }

    return false;
  }



}

