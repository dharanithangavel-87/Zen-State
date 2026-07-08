package com.example.demo.repository;

import com.example.demo.entity.MeditationSession;
import com.example.demo.entity.SessionEnrollment;
import com.example.demo.entity.ZenUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SessionEnrollmentRepository extends JpaRepository<SessionEnrollment, Long> {

    Optional<SessionEnrollment> findByPractitionerAndSessionAndCompleted(
            ZenUser practitioner,
            MeditationSession session,
            boolean completed
    );

    @Query("SELECT s FROM SessionEnrollment s WHERE s.practitioner.id = :userId")
    List<SessionEnrollment> findAllByUserId(Long userId);

}