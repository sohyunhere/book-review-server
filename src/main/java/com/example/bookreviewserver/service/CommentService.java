package com.example.bookreviewserver.service;

import com.example.bookreviewserver.dto.CommentsDto;
import com.example.bookreviewserver.model.Comments;
import com.example.bookreviewserver.model.Member;
import com.example.bookreviewserver.repo.CommentQueryRepo;
import com.example.bookreviewserver.repo.CommentRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepo commentRepo;
    private final BoardService boardService;
    private final MemberService memberService;
    private final CommentQueryRepo commentQueryRepo;

    //댓글 작성
    @Transactional
    public Long registerComment(CommentsDto dto, Member member) {
        dto.setMember(member);
        dto.setPost(boardService.findPostBypostId(dto.getPostId()));

        Comments comment = dto.toEntity();
        Comments saveComm = commentRepo.save(comment);

        return saveComm.getCommentId();
    }

    //댓글 삭제
    @Transactional
    public Long deleteComment(Long commentId){
        commentRepo.deleteById(commentId);
        return commentId;
    }

    //postId에 따른 댓글 리스트 불러오기
    public List<Comments> findListByPostId(Long postId){
        return commentRepo.findCommentsByPostPostId(postId, Sort.by(Sort.Direction.ASC, "commentId"));
    }

    //email에 따른 댓글 리스트
    @Transactional
    public List<Comments> findListByEmail(String email){
        Member member = memberService.findMemberByEmail(email);
        List<Comments> comments = member.getComments();
        return comments;
    }

    //댓글 내용 수정
    @Transactional
    public Long updateComment(Long commentId, String content){
        commentQueryRepo.updateContent(commentId, content);
        return commentId;
    }
}
