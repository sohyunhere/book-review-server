package com.example.bookreviewserver.controller;


import com.example.bookreviewserver.model.Category;
import com.example.bookreviewserver.model.Post;
import com.example.bookreviewserver.service.BoardService;
import com.example.bookreviewserver.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class MainController {

    private final CategoryService categoryService;
    private final BoardService boardService;

    @GetMapping("/")
    public ResponseEntity<List<Category>> main() {
        List<Category> categories = categoryService.findAll();

        return ResponseEntity.ok(categories);
    }
    @GetMapping("/main/posts")
    public ResponseEntity<List<Post>> mainPost(){
        List<Post> posts = boardService.findAllByLatest();

        return ResponseEntity.ok(posts);
    }

    @GetMapping("/latest")
    public ResponseEntity<List<Post>> latestPost(){
        List<Post> posts = boardService.findAllByLatest();

        return ResponseEntity.ok(posts);
    }
    @GetMapping("/popular")
    public ResponseEntity<List<Post>> popPost(){
        List<Post> posts = boardService.findAllByView();

        return ResponseEntity.ok(posts);
    }
}
