package com.user.usermanage.user.repository;

import com.user.usermanage.user.entity.User;

import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByIdentifier(String identifier);

    Optional<User> getByEmail(String identifier);

    @Transactional
    @Modifying
    @Query("UPDATE User e SET e.nickname = :newNickname WHERE e.userId = :userId")
    void updateNickname(@Param("newNickname") String newNickname, @Param("userId") Long userId);


    @Transactional
    @Modifying
    @Query("UPDATE User e SET e.password = :newPassword WHERE e.userId = :userId")
    void updatePassword(@Param("newPassword") String newPassword, @Param("userId") Long userId);

}
