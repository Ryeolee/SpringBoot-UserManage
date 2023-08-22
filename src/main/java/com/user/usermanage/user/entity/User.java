package com.user.usermanage.user.entity;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table
public class User extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long userId;

    @Column
    private String role;


    @Column(nullable = false, unique = true)
    private String identifier;

    @Column(nullable = false)
    private String password;


    @Column(nullable = true)
    private String email;

    @Column(nullable = false)
    private String nickname;





}
