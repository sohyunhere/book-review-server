package com.example.bookreviewserver.dto;

import com.example.bookreviewserver.model.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
public class PostDd {
    private Long postId;
    private Member member;
    private String postTitle;
    private Date readBookDate;
    private String bookTitle;

    private String content;
    private Date writtenDate;
    private Long viewCount;

    private String author;
    private String publisher;
    private Location location;
    private AttachedFile attachedFile = null;
    private Category category;
    private List<Comments> comments = new ArrayList<>();
    public PostDd(){}
    public PostDd(Post post) {
        postId = post.getPostId();
        category = post.getCategory();
        member = post.getMember();
        location = post.getLocation();
        postTitle = post.getPostTitle();
        readBookDate = post.getReadBookDate();
        content = post.getContent();
        bookTitle = post.getBookTitle();
        writtenDate = post.getWrittenDate();
        viewCount = post.getViewCount();
        author = post.getAuthor();
        publisher = post.getPublisher();

        if(post.getComments() != null) {
            List<Comments> commentsList = post.getComments();

            for (Comments comment : commentsList) {
                comments.add(comment);
            }
        }

        if(post.getAttachedFile() != null) {
            attachedFile = post.getAttachedFile();
        }
    }
}
