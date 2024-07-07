package com.crio.springdatabyte.entity;

import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "users")

public class UserEntity {

  @Id
  public String id;

  public String username;

  public List<PostModel> posts;
  

  public UserEntity(String id, String username, List<PostModel> posts) {
    this.id = id;
    this.username = username;
    this.posts = posts;
  }
  public UserEntity(){}
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public List<PostModel> getPosts() {
    return posts;
  }

  public void setPosts(List<PostModel> posts) {
    this.posts = posts;
  }

}
