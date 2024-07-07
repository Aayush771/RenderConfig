package com.crio.springdatabyte.controller;

import com.crio.springdatabyte.dto.Post;
import com.crio.springdatabyte.dto.Stats;
import com.crio.springdatabyte.dto.User;
import com.crio.springdatabyte.service.ForumService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ForumController {

  @Autowired
  ForumService service;

  @GetMapping("/stats")
  public ResponseEntity<String> stats() {
    Stats stats = service.getForumStats();
    String message = "<p>Hello!</p>"
        + "<div><b>Forum stats</b></div>"
        + "<div>---------------</div>"
        + "<div>Number of users: " + stats.getNumUsers() + "</div>";

    return ResponseEntity.ok().body(message);
  }

  @GetMapping("/user/{username}/posts")
  public ResponseEntity<List<Post>> getPostsByUser(@PathVariable("username") String username) {
    List<Post> posts = service.getPostsByUser(username);
    return ResponseEntity.ok().body(posts);
  }

  @GetMapping("/user/search")
  public ResponseEntity<List<User>> getUsersWithPostText(
      @RequestParam(name = "content") String content) {
    List<User> users = service.getUsersWithPostText(content);
    return ResponseEntity.ok().body(users);
  }

}
