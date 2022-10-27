package com.example.bookreviewserver.service;

import com.example.bookreviewserver.dto.LocationDto;
import com.example.bookreviewserver.dto.PostDto;
import com.example.bookreviewserver.model.Location;
import com.example.bookreviewserver.model.Member;
import com.example.bookreviewserver.model.Post;
import com.example.bookreviewserver.repo.BoardQueryRepo;
import com.example.bookreviewserver.repo.BoardRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Slf4j
public class BoardService {

    private final BoardRepo boardRepo;
    private final BoardQueryRepo boardQueryRepo;
    private final CategoryService categoryService;
    private final LocationService locationService;

    //글 작성 등록
    @Transactional
    public Long registerPost(PostDto dto, Member user){
        LocationDto locationDto = new LocationDto();
        locationDto.setLat(new BigDecimal(dto.getLat()));
        locationDto.setLng(new BigDecimal(dto.getLng()));

        Location location = locationService.registerLocation(locationDto);

        dto.setLocation(location);
        dto.setLocationId(location.getLocationId());
//        Date today = new Date();
        dto.setMember(user);
//        dto.setWrittenDate(today);
        dto.setViewCount(1L);
        dto.setCategory(categoryService.findCategoryById(dto.getCategoryId()));
        Post post = dto.toEntity();

        Post savedPost = boardRepo.save(post);

        return savedPost.getPostId();
    }
    //글 목록 다 가져가기
    public List<Post> findAll(){
        return boardRepo.findAll();
    }

    //게시글 최신순으로 가져가기
    public List<Post> findAllByLatest(){
        List<Post> list = boardRepo.findAll(Sort.by(Sort.Direction.DESC, "postId"));

        return list;
    }
    //게시글 조회순으로 가져가기
    public List<Post> findAllByView(){
        List<Post> list = boardRepo.findAll(Sort.by(Sort.Direction.DESC, "viewCount"));

        return list;
    }

    //postId에 해당하는 post객체 가져오기
    @Transactional //JPA를 사용하다가 연관관계 매핑
    // comment list가 @onetomany로 되어있기 때문
    public Post findPostBypostId(Long id) {
        Optional<Post> result = Optional.ofNullable(boardRepo.findById(id).orElseThrow(() -> new IllegalStateException("post가 존재하지 않습니다.")));

        return result.get();
    }

    //조회수 올리기
    @Transactional
    public void updateVisit(Long id, Long viewCount){
        boardQueryRepo.updateView(id, viewCount+1L);
    }

    //게시글 수정하기
    @Transactional
    public void updatePost(Long id, String content){
        boardQueryRepo.updateContent(id, content);
    }

    //게시글 삭제하기
    @Transactional
    public Long deletePostById(Long id){
        boardRepo.deleteById(id);

        return id;
    }

    //카테고리 아이디별 게시글 리스트
    public List<Post> findListByCategoryId(Long id){
        return boardRepo.findByCategoryCategoryId(id);
    }

    //내가 작성한 글게시글 리스트 반환
    public List<Post> findPostListByUser(Long id){
        return boardRepo.findByMemberMemberIdOrderByPostIdDesc(id);

    }

    //게시글 검색
    public List<Post> searchList(String word, int searchType){
        List<Post> postList;
        //글 제목, 글 내용, 책 제목, 저자
        if(searchType == 1){
            postList=boardRepo.findByPostTitleContainingOrderByViewCountDesc(word);
        }else if(searchType == 2){
            postList=boardRepo.findByContentContainingOrderByViewCountDesc(word);
        }else if(searchType == 3){
            postList=boardRepo.findByBookTitleContainingOrderByViewCountDesc(word);
        }else{
            postList=boardRepo.findByAuthorContainingOrderByViewCountDesc(word);
        }
        return postList;
    }
}
