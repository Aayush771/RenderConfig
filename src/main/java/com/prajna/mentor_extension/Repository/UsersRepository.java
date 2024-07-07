package com.prajna.mentor_extension.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import com.prajna.mentor_extension.Entity.Users;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsersRepository extends MongoRepository<Users, String> {
    @NonNull
    Optional<Users> findById(@NonNull String id);

    @NonNull
    Optional<Users> findByEmail(@NonNull String email);

    @NonNull
    Optional<Users> findByName(@NonNull String name);

    @NonNull
    List<Users> findAll();

}
