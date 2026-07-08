package com.example.demo.repository;

import com.example.demo.entity.ZenUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ZenUserRepository extends JpaRepository<ZenUser, Long> {

    Optional<ZenUser> findByEmail(String email);

    boolean existsByEmail(String email);
}