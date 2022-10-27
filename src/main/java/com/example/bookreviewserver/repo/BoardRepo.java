package com.example.bookreviewserver.repo;

import com.example.bookreviewserver.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepo extends JpaRepository<Post, Long> {
    List<Post> findByCategoryCategoryId(Long categoryId);
    List<Post> findByMemberMemberIdOrderByPostIdDesc(Long memberId);

    //글제목으로 검색
    List<Post> findByPostTitleContainingOrderByViewCountDesc(String postTitle);
    //글내용으로 검색
    List<Post> findByContentContainingOrderByViewCountDesc(String content);
    //책 제목으로 검색
    List<Post> findByBookTitleContainingOrderByViewCountDesc(String title);
    //저자로 검색
    List<Post> findByAuthorContainingOrderByViewCountDesc(String author);
}
