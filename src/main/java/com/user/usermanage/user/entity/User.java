package com.user.usermanage.user.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table
public class User extends BaseEntity implements UserDetails {

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


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return this.identifier;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }
    @JsonProperty(access = JsonProperty.Access.READ_WRITE)
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @JsonProperty(access = JsonProperty.Access.READ_WRITE)
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @JsonProperty(access = JsonProperty.Access.READ_WRITE)
    @Override
    public boolean isEnabled() {
        return true;
    }
}
