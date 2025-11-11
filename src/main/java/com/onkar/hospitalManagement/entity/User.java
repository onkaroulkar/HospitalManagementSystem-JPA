package com.onkar.hospitalManagement.entity;

import com.onkar.hospitalManagement.type.AuthProviderType;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "app_user")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column
    private String password;

    private String providerId;

    @Enumerated(EnumType.STRING)
    private AuthProviderType authProviderType;

    public User() {}

    public User(String username) {
        this.username = username;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public void setUsername(String username) { this.username = username; }
    public void setPassword(String password) { this.password = password; }

    @Override
    public String getUsername() {
        return username;  // return actual username
    }

    @Override
    public String getPassword() {
        return password;  // return actual password
    }

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }

    public AuthProviderType getAuthProviderType() {
        return authProviderType;
    }

    public void setAuthProviderType(AuthProviderType authProviderType) {
        this.authProviderType = authProviderType;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public boolean isAccountNonExpired() { return true; }

    @Override
    public boolean isAccountNonLocked() { return true; }

    @Override
    public boolean isCredentialsNonExpired() { return true; }

    @Override
    public boolean isEnabled() { return true; }
}
