package com.user.usermanage.user.repository;

import com.user.usermanage.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByIdentifier(String identifier);

    User findByPassword(String password);


}
