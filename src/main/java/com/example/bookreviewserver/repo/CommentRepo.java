package com.example.bookreviewserver.repo;
import com.example.bookreviewserver.model.Comments;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepo extends JpaRepository<Comments, Long> {
    List<Comments> findCommentsByPostPostId(Long postId, Sort sort);
    List<Comments> findCommentsByMemberMemberId(Long memberId);
}
