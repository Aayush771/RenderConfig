package com.crio.springdatabyte.repository;

import com.crio.springdatabyte.entity.UserEntity;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface UserRepository extends MongoRepository<UserEntity, Integer> {

  List<UserEntity> findByUsername(String username);

  @Query("{'posts.content': {$regex: ?0}}")
  List<UserEntity> findUsersWithPostText(String content);

}
