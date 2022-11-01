package com.example.bookreviewserver.controller;

import com.example.bookreviewserver.dto.AttachedFileDto;
import com.example.bookreviewserver.model.AttachedFile;
import com.example.bookreviewserver.service.FileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@Slf4j
public class UploadController {

    @Value("${com.example.upload.path}") // application.properties의 변수
    private String uploadPath;//이미지 파일 경로

    @Value("${com.example.upload.filepath}")
    private String filePath;//첨부 파일 경로

    private final FileService fileService;

//    //위지윅 에디터 이미지 업로드
//    @PostMapping("/board/image/editorUpload")
//    public String uploadImage(@RequestParam("image") MultipartFile upload) throws IOException {
//        //랜덤 문자 생성
//        UUID uid = UUID.randomUUID();
//
//        String fileName = upload.getOriginalFilename();//파일 이름 가져오기
//        String toastUploadPath = uid + "_" + fileName;
//
//        File file = new File(uploadPath, toastUploadPath);
//        upload.transferTo(file);
//
//        log.info("toastUploadPath : "+toastUploadPath);
//        return toastUploadPath;
//    }

    //첨부파일 업로드
    @PostMapping("/board/fileUpload")
    public String uploadFile(@RequestBody AttachedFileDto dto) {

        AttachedFile savedFile = fileService.registerFile(dto);

        return String.valueOf(savedFile.getFileId());
    }

    @GetMapping("/filedownload/{idx}")
    public AttachedFile downloadFile(@PathVariable("idx") Long fileId) {
        AttachedFile f = fileService.getFilebyId(fileId);
        return f;
    }
}
