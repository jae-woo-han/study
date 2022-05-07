package com.study.board.controller.api;

import com.study.board.repository.FileRepository;
import com.study.board.vo.FileVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@Transactional
@RestController
@RequestMapping("api")
public class FileApiController {
    private final FileRepository fileRepository;

    public FileApiController(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    @GetMapping("/post/{postId}/file")
    public ResponseEntity<List<FileVO>> getFileListByPostId(@PathVariable int postId){
        List<FileVO> fileVOList = fileRepository.selectFilesByPostId(postId);
        return new ResponseEntity<>(fileVOList, null, HttpStatus.OK);
    }
}
