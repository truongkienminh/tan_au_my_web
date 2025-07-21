package com.kienminh.tanaumysystem.repository;

import com.kienminh.tanaumysystem.entity.User;
import com.kienminh.tanaumysystem.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    boolean existsByRole(UserRole role);

}
