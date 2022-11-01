package com.example.bookreviewserver.service;

import com.example.bookreviewserver.dto.AttachedFileDto;
import com.example.bookreviewserver.model.AttachedFile;
import com.example.bookreviewserver.model.Post;
import com.example.bookreviewserver.repo.FileRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Slf4j
public class FileService {
    private final FileRepo fileRepo;
    private final BoardService boardService;

    //파일 등록
    @Transactional
    public AttachedFile registerFile(AttachedFileDto dto){
        Post post = boardService.findPostBypostId(dto.getPostId());
        dto.setPost(post);
        AttachedFile file = dto.toEntity();
        AttachedFile savedFile = fileRepo.save(file);

        return savedFile;
    }

    //postId파일 가져오기
    public AttachedFile getFile(long postId){
        Optional<AttachedFile> result = Optional.ofNullable(fileRepo.findAttachedFileByPostPostId(postId).orElseThrow(
                ()-> new IllegalStateException("postId에 해당하는 첨부파일이 존재하지 않습니다."))
        );
        return result.get();
    }
    //fileId 파일 가져오기
    public AttachedFile getFilebyId(long fileId){
        Optional<AttachedFile> file = fileRepo.findById(fileId);
        return file.get();
    }
    //파일 다운로드
    public void downloadFile(long fileId, HttpServletResponse response) throws Exception {
        AttachedFile f = getFilebyId(fileId);

        try {
            String path = f.getPath(); // 경로에 접근할 때 역슬래시('\') 사용

            String name = new String(f.getName().getBytes("UTF-8"), "ISO-8859-1");
            response.setHeader("Content-Disposition", "attachment;filename=" + name); // 다운로드 되거나 로컬에 저장되는 용도로 쓰이는지를 알려주는 헤더

            FileInputStream fileInputStream = new FileInputStream(path); // 파일 읽어오기
            OutputStream out = response.getOutputStream();

            int read = 0;
            byte[] buffer = new byte[1024];
            while ((read = fileInputStream.read(buffer)) != -1) { // 1024바이트씩 계속 읽으면서 outputStream에 저장, -1이 나오면 더이상 읽을 파일이 없음
                out.write(buffer, 0, read);
            }

        } catch (Exception e) {
            throw new Exception("download error");
        }
    }

    //파일 삭제
    public void deleteFile(long fileId){
        fileRepo.deleteById(fileId);
    }
}
