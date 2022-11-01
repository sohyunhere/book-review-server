package com.example.bookreviewserver.repo;

import com.example.bookreviewserver.model.AttachedFile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FileRepo extends JpaRepository<AttachedFile, Long> {
    Optional<AttachedFile> findAttachedFileByPostPostId(Long postId);
    void deleteAttachedFileByPostPostId(Long postId);
}
