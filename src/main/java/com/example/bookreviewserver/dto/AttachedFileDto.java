package com.example.bookreviewserver.dto;

import com.example.bookreviewserver.model.AttachedFile;
import com.example.bookreviewserver.model.Post;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AttachedFileDto {

    private String name;

    private String path;

    private Post post;
    private long postId;
    public AttachedFile toEntity(){
        return AttachedFile.builder()
                .post(post)
                .path(path)
                .name(name)
                .build();
    }

}
