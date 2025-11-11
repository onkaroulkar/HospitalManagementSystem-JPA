package com.onkar.hospitalManagement.repository;

import com.onkar.hospitalManagement.entity.User;
import com.onkar.hospitalManagement.type.AuthProviderType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User>  findByUsername(String username);
    Optional<User> findByProviderIdAndAuthProviderType(String providerId, AuthProviderType providerType);
}