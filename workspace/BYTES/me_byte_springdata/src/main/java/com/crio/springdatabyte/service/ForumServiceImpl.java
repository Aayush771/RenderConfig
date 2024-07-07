package com.crio.springdatabyte.service;

import com.crio.springdatabyte.dto.Post;
import com.crio.springdatabyte.dto.Stats;
import com.crio.springdatabyte.dto.User;
import com.crio.springdatabyte.repositoryservice.ForumRepositoryServiceImpl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ForumServiceImpl implements ForumService {

  @Autowired
  ForumRepositoryServiceImpl forumRepositoryService;

  @Override
  public Stats getForumStats() {
    return forumRepositoryService.getForumStats();
  }

  public List<Post> getPostsByUser(String username) {
    return forumRepositoryService.getPostsByUser(username);
  }

  public List<Post> getMostRecentPostsByUser(String username, int maxCount) {
    return null;
  }

  public List<User> getUsersWithPostText(String text) {
    return forumRepositoryService.getUsersWithPostText(text);
  }
}
