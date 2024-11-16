package com.authenticationservice.repo.entity;

import com.authenticationservice.domain.models.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> getByEmail(String email);
    boolean existsByEmail(String email);
}