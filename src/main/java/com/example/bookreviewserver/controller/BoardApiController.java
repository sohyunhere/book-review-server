package com.example.bookreviewserver.controller;

import com.example.bookreviewserver.dto.PostDto;
import com.example.bookreviewserver.model.Member;
import com.example.bookreviewserver.model.Post;
import com.example.bookreviewserver.service.BoardService;
import com.example.bookreviewserver.service.ChartService;
import com.example.bookreviewserver.service.FileService;
import com.example.bookreviewserver.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@Slf4j
public class BoardApiController {
    private final BoardService boardService;
    private final FileService fileService;
    private final ChartService chartService;
    private final MemberService memberService;


    //글 작성
    @PostMapping("/board/write")
    public int write(@RequestBody PostDto dto) {
        System.out.println(dto.getMember().getMemberEmail());
        Member user = memberService.findMemberByEmail(dto.getMember().getMemberEmail());
        Long postId = boardService.registerPost(dto, user);

        return Math.toIntExact(postId);
    }

    //글 수정
    @PostMapping("/board/update/{postId}")
    public int updatePost(@PathVariable("postId") Long id, @RequestBody PostDto dto){

        boardService.updatePost(id,dto.getContent());

        return Math.toIntExact(id);
    }
    //파일 삭제
    @GetMapping("/delete/{fileId}")
    public int deleteFile(@PathVariable("fileId") Long id){

        fileService.deleteFile(id);
        return Math.toIntExact(id);
    }

    //글 삭제
    @PostMapping("/board/delete/post/{postId}")
    public int deletePost(@PathVariable("postId") Long id){

        Long postId = boardService.deletePostById(id);

        return Math.toIntExact(postId);
    }

    //내가 작성한 게시글 가져오기
    @GetMapping ("/board/mypost/list/{id}")
    public ResponseEntity<List<Post>> getMyPost(@PathVariable("id") Long id) {

        List<Post> posts = boardService.findPostListByUser(id);
        return ResponseEntity.ok(posts);
    }

    //글 검색
    @GetMapping("/board/search/{type}/{word}")
    public  ResponseEntity<List<Post>> search(
            @PathVariable("type") String type, @PathVariable("word") String word){

        List<Post> posts = boardService.searchList(word, Integer.parseInt(type));

        return ResponseEntity.ok(posts);
    }

    //차트데이터 보내기
    @GetMapping("/chart/data")
    public  ResponseEntity<Map<String, Object>> chart(){
        Map<String, Object> map = new HashMap<>();

        map.put("days", chartService.findLabels());
        map.put("literature", chartService.literatureData());
        map.put("philosophy", chartService.philosophyData());
        map.put("ss", chartService.ssData());
        map.put("ts", chartService.tsData());
        map.put("ns", chartService.nsData());
        map.put("art", chartService.artData());
        map.put("lan", chartService.lanData());
        map.put("his", chartService.hisData());
        map.put("ex", chartService.exData());

        return ResponseEntity.ok(map);
    }

}
