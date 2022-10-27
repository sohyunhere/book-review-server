package com.example.bookreviewserver.repo;

import com.example.bookreviewserver.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepo extends JpaRepository<Category, Long> {
    Optional<Category> findByCategoryId(Long categoryId);
}
