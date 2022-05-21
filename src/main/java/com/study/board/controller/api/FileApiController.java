package com.study.board.controller.api;

import com.study.board.repository.FileRepository;
import com.study.board.service.StorageService;
import com.study.board.vo.FileVO;
import com.study.board.vo.ResponseVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Slf4j
@Transactional
@RestController
@RequestMapping("api")
public class FileApiController {
    private final FileRepository fileRepository;
    private final StorageService storageService;

    public FileApiController(FileRepository fileRepository, StorageService storageService) {
        this.fileRepository = fileRepository;
        this.storageService = storageService;
    }

    @GetMapping("/post/{postId}/file")
    public ResponseEntity<List<FileVO>> getFileListByPostId(@PathVariable int postId){
        List<FileVO> fileVOList = fileRepository.selectFilesByPostId(postId);
        return new ResponseEntity<>(fileVOList, null, HttpStatus.OK);
    }

    @PostMapping("/files/{postId}")
    public ResponseEntity<ResponseVO> handleFileListUpload(
            @RequestParam("files")List<MultipartFile> files,
            @PathVariable("postId") int postId
            ){
        try{
            for (MultipartFile item: files) {
                FileVO fileVO = new FileVO();
                String originName = item.getOriginalFilename();

                if(StringUtils.isNoneEmpty(originName)){
                    String fileName = originName.substring(0,originName.lastIndexOf("."));
                    String fileType = originName.substring(originName.lastIndexOf(".")+1);
                    String encodeFileName = UUID.randomUUID().toString();
                    String path ="/";

                    fileVO.setPostId(postId);
                    fileVO.setFileName(fileName);
                    fileVO.setFileType(fileType);
                    fileVO.setEncodeFileName(encodeFileName);
                    fileVO.setDirectoryPath(path);
                }

                storageService.store(item, fileVO.getEncodeFileName());
                fileRepository.insertFileInfo(fileVO);
            }
        }catch (IOException e){
            log.error("파일 저장 과정 오류", e);
        }catch (Exception e){
            log.error("파일 저장: 기타 오류", e);
        }
        ResponseVO<String> responseData = new ResponseVO<>("성공","파일 저장 성공");
        return ResponseEntity.ok(responseData);
    }
}
