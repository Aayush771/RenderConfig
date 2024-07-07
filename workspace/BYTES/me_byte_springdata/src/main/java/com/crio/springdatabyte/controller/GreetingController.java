package com.crio.springdatabyte.controller;

import com.crio.springdatabyte.dto.Stats;
import com.crio.springdatabyte.service.ForumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GreetingController {

  @GetMapping("/greeting")
  public ResponseEntity<String> greeting() {
    String message = "Hello world!";
    return ResponseEntity.ok().body(message);
  }

}