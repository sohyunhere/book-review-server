package com.example.bookreviewserver.dto;

import com.example.bookreviewserver.model.Category;
import com.example.bookreviewserver.model.Location;
import com.example.bookreviewserver.model.Member;
import com.example.bookreviewserver.model.Post;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


@Data
public class PostDto {
    private Member member;
    private Category category;
    private Long categoryId;
    private String postTitle;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date readDate;
    private String bookTitle;
    private String content;

    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date writtenDate;
    private Long viewCount;

    private String author;
    private String publisher;

    private Location location;
    private Long locationId;
    private double lat;
    private double lng;
    public Post toEntity(){
        return Post.builder()
                .member(member)
                .postTitle(postTitle)
                .readBookDate(new java.sql.Date(readDate.getTime()))
                .bookTitle(bookTitle)
                .content(content)
                .viewCount(viewCount)
                .author(author)
                .publisher(publisher)
                .category(category)
                .writtenDate(writtenDate)
                .location(location)
                .build();
    }


}
