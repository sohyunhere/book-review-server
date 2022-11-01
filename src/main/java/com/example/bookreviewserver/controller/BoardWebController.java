package com.example.bookreviewserver.controller;

import com.example.bookreviewserver.dto.PostDd;
import com.example.bookreviewserver.model.Category;
import com.example.bookreviewserver.model.Comments;
import com.example.bookreviewserver.model.Post;
import com.example.bookreviewserver.service.BoardService;
import com.example.bookreviewserver.service.CategoryService;
import com.example.bookreviewserver.service.CommentService;
import com.example.bookreviewserver.service.FileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


@Slf4j
@Controller
@RequiredArgsConstructor
public class BoardWebController {
    private final BoardService boardService;
    private final CommentService commentService;

    //글 보기 + 조회수 1씩 올리기
    @GetMapping("/board/{postId}")
    public ResponseEntity<PostDd>  postView(@PathVariable("postId") Long id){
        Post post = boardService.findPostBypostId(id);
        boardService.updateVisit(id, post.getViewCount());
        PostDd dd = new PostDd(post);

        return  ResponseEntity.ok(dd);
    }

    //내가 작성한 글게시글 화면이동
    @GetMapping("/board/mypost")
    public String goMyPost(){
        return "member/myPost";
    }

    //수정 게시글로 이동
    @ResponseBody
    @GetMapping("/board/update/{postId}")
    public ResponseEntity<PostDd> updatePost(@PathVariable("postId") Long id){
        Post post = boardService.findPostBypostId(id);
        PostDd dd = new PostDd(post);

        return ResponseEntity.ok(dd);
    }


    //내가 작성한 댓글
//    @ResponseBody
    @GetMapping("/board/myComment/{email}")
    public  ResponseEntity<List<Comments>> getMyComment(@PathVariable("email") String email) {

        List<Comments> comments = commentService.findListByEmail(email);

        return ResponseEntity.ok(comments);
    }

    //나의 업로드 위치
    @GetMapping("/board/myUploadLocation/{id}")
    public ResponseEntity<List<Post>> getMyUploadLocation(@PathVariable("id") Long id){

        List<Post> posts = boardService.findPostListByUser(id);

        return ResponseEntity.ok(posts);
    }

}
