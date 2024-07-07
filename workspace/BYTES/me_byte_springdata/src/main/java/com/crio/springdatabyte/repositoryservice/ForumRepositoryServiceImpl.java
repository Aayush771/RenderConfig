package com.crio.springdatabyte.repositoryservice;

import com.crio.springdatabyte.dto.Post;
import com.crio.springdatabyte.dto.Stats;
import com.crio.springdatabyte.dto.User;
import com.crio.springdatabyte.entity.PostModel;
import com.crio.springdatabyte.entity.UserEntity;
import com.crio.springdatabyte.repository.UserRepository;
import java.util.ArrayList;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ForumRepositoryServiceImpl implements ForumRepositoryService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private ModelMapper modelMapper;

  @Override
  public Stats getForumStats() {
    List<UserEntity> users = userRepository.findAll();

    return new Stats(users.size());
  }

  @Override
  public List<Post> getPostsByUser(String username) {
    List<UserEntity> userEntityList = userRepository.findByUsername(username);

    List<Post> posts = new ArrayList<>();

    for(UserEntity userEntity : userEntityList) {
      List<PostModel> postModelEntityList = userEntity.getPosts();
      for(PostModel postModelEntity : postModelEntityList) {
        posts.add(modelMapper.map(postModelEntity, Post.class));
      }
    }

    return posts;
  }

  @Override
  public List<User> getUsersWithPostText(String text) {
    List<UserEntity> userEntityList = userRepository.findUsersWithPostText(text);

    List<User> userList = new ArrayList<>();
    for(UserEntity userEntity : userEntityList) {
      userList.add(modelMapper.map(userEntity, User.class));
    }

    return userList;
  }

}
