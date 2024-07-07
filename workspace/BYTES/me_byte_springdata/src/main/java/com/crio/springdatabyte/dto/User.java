package com.crio.springdatabyte.dto;

import java.io.Serializable;
import java.util.List;


public class User implements Serializable {

  public String id;

  public String username;

  public List<Post> posts;

  public User(String id, String username, List<Post> posts) {
    this.id = id;
    this.username = username;
    this.posts = posts;
  }
  public User() {}

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

  public List<Post> getPosts() {
    return posts;
  }

  public void setPosts(List<Post> posts) {
    this.posts = posts;
  }

}
