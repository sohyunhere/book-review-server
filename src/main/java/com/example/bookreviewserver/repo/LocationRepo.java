package com.example.bookreviewserver.repo;

import com.example.bookreviewserver.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepo extends JpaRepository<Location, Long> {
}
