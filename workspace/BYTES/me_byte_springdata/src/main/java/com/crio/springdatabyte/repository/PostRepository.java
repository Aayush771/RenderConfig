package com.crio.springdatabyte.repository;

import com.crio.springdatabyte.entity.PostModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PostRepository extends MongoRepository<PostModel, Integer> {

}
