package com.user.usermanage.user.repository;

import com.user.usermanage.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByIdentifier(String identifier);


}
