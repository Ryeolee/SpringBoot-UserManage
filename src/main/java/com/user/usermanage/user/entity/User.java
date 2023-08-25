package com.user.usermanage.user.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table
public class User extends BaseEntity  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long userId;


    @Column
    private String role;


    @Column(nullable = false, unique = true)
    private String identifier;

    @JsonProperty(access = JsonProperty.Access.READ_WRITE)
    @Column(nullable = false)
    private String password;


    @Column(nullable = true)
    private String email;

    @Column(nullable = false)
    private String nickname;

}
