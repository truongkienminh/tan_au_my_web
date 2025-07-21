package com.kienminh.tanaumysystem.repository;

import com.kienminh.tanaumysystem.entity.Parent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParentRepository extends JpaRepository<Parent, Long> {
}
