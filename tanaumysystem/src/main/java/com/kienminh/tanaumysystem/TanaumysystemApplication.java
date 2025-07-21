package com.kienminh.tanaumysystem;

import com.kienminh.tanaumysystem.entity.User;
import com.kienminh.tanaumysystem.enums.UserRole;
import com.kienminh.tanaumysystem.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class TanaumysystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(TanaumysystemApplication.class, args);
	}
	@Bean
	public CommandLineRunner initAdmin(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		return args -> {
			boolean hasAdmin = userRepository.existsByRole(UserRole.ADMIN);
			if (!hasAdmin) {
				User admin = new User();
				admin.setUsername("admin");
				admin.setEmail("admin@yourcenter.com");
				admin.setPassword(passwordEncoder.encode("admin123"));
				admin.setRole(UserRole.ADMIN);
				userRepository.save(admin);
			}
		};
	}
}
