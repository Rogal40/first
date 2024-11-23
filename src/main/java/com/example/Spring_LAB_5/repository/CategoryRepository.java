package com.example.Spring_LAB_5.repository;

import com.example.Spring_LAB_5.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
