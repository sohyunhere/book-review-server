package com.example.bookreviewserver.controller;

import com.example.bookreviewserver.dto.CommentsDto;
import com.example.bookreviewserver.model.Member;
import com.example.bookreviewserver.service.CommentService;
import com.example.bookreviewserver.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
public class CommentController
{
    private final CommentService commentService;
    private final MemberService memberService;

    //댓글 작성
    @PostMapping("/comment/write")
    public int write(@RequestBody CommentsDto dto){
        Member member = memberService.findMemberByEmail(dto.getMember().getMemberEmail());
        Long commentId = commentService.registerComment(dto, member);

        return Math.toIntExact(commentId);
    }

    //댓글 삭제
    @GetMapping("/board/delete/comment/{commentId}")
    public int deleteComment(@PathVariable("commentId") Long id){
        Long commentId = commentService.deleteComment(id);
        return Math.toIntExact(commentId);
    }

    //댓글 수정
    @PostMapping("/board/update/comment/{commentId}")
    public int updateComment(@PathVariable("commentId") Long id,
                             @RequestBody String content){
        Long commentId = commentService.updateComment(id, content);
        return Math.toIntExact(commentId);
    }
}
