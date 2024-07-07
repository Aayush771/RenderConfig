package com.crio.springdatabyte.dto;

import java.io.Serializable;


public class Post implements Serializable {
  public int posterId;

  public String link;

  public String content;

  public long postCreatedTimestamp;
  

  public Post(int posterId, String link, String content, long postCreatedTimestamp) {
    this.posterId = posterId;
    this.link = link;
    this.content = content;
    this.postCreatedTimestamp = postCreatedTimestamp;
  }
  public Post() {}

  public int getPosterId() {
    return posterId;
  }

  public void setPosterId(int posterId) {
    this.posterId = posterId;
  }

  public String getLink() {
    return link;
  }

  public void setLink(String link) {
    this.link = link;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public long getPostCreatedTimestamp() {
    return postCreatedTimestamp;
  }

  public void setPostCreatedTimestamp(long postCreatedTimestamp) {
    this.postCreatedTimestamp = postCreatedTimestamp;
  }

}
