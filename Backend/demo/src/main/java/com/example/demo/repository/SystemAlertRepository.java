package com.example.demo.repository;

import com.example.demo.entity.SystemAlert;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SystemAlertRepository extends JpaRepository<SystemAlert, Long> {

    List<SystemAlert> findTop5ByOrderByCreatedAtDesc();

}