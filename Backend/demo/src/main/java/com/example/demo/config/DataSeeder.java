package com.example.demo.config;

import java.time.LocalDate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.demo.entity.ZenUser;
import com.example.demo.repository.ZenUserRepository;

@Component
public class DataSeeder implements CommandLineRunner {

    private final ZenUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // Spring dependencies automatically injected on runtime initialization
    public DataSeeder(ZenUserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        // Evaluate if the MySQL relational users ledger is empty
        if (userRepository.count() == 0) {
            System.out.println("🚀 [ZenState DataSeeder] Database is empty. Seeding secure system infrastructure roles...");

            // 1. Seed the Primary Platform Administrator (ZEN_MASTER)
            ZenUser master = new ZenUser();
            master.setFullName("Zen Master Admin");
            master.setEmail("master@zenstate.com");
            // Encrypts plain password safely using your BCryptPasswordEncoder bean configuration
            master.setPassword(passwordEncoder.encode("admin123")); 
            master.setRole(ZenUser.UserRole.ZEN_MASTER); // Assigned strict RBAC context
            master.setTotalMinutesMeditated(0);
            master.setCurrentStreak(0);
            master.setLastMeditationDate(LocalDate.now());
            userRepository.save(master);

            // 2. Seed a Default Instructor Profile (WELLNESS_COACH)
            ZenUser coach = new ZenUser();
            coach.setFullName("Coach Professional");
            coach.setEmail("coach@zenstate.com");
            coach.setPassword(passwordEncoder.encode("coach123"));
            coach.setRole(ZenUser.UserRole.WELLNESS_COACH); // Assigned instructor context
            coach.setTotalMinutesMeditated(0);
            coach.setCurrentStreak(0);
            coach.setLastMeditationDate(LocalDate.now());
            userRepository.save(coach);

            System.out.println("✅ [ZenState DataSeeder] Secure structural accounts seeded successfully.");
            System.out.println("👉 ZEN_MASTER Profile Credentials: master@zenstate.com | admin123");
            System.out.println("👉 WELLNESS_COACH Profile Credentials: coach@zenstate.com | coach123");
        } else {
            System.out.println("ℹ️ [ZenState DataSeeder] Relational ledger entries detected. Skipping system database seeding initialization.");
        }
    }
}