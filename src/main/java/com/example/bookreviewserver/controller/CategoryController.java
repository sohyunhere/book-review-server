package com.example.bookreviewserver.controller;

import com.example.bookreviewserver.model.Category;
import com.example.bookreviewserver.model.Post;
import com.example.bookreviewserver.service.BoardService;
import com.example.bookreviewserver.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequiredArgsConstructor
public class CategoryController {
    private final BoardService boardService;

    //카테고리 게시글
    @ResponseBody
    @PostMapping("/category/post/{categoryId}")
    public ResponseEntity<List<Post>> categoryPost(@PathVariable("categoryId") Long id) {

        List<Post> posts = boardService.findListByCategoryId(id);
        return ResponseEntity.ok(posts);
    }
}
