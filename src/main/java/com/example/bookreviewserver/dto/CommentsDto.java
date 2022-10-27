package com.example.bookreviewserver.dto;

import com.example.bookreviewserver.model.Comments;
import com.example.bookreviewserver.model.Member;
import com.example.bookreviewserver.model.Post;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class CommentsDto {
    Long commentId;
    Long postId;
    String content;
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    Date writtenDate;

    Post post;
    Member member;

    public Comments toEntity(){
        return Comments.builder()
                .post(post)
                .content(content)
                .writtenDate(writtenDate)
                .member(member)
                .build();
    }

}
