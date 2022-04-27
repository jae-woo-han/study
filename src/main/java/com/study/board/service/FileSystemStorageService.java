package com.study.board.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;
@Slf4j
@Service
public class FileSystemStorageService implements StorageService{
    @Override
    public void init() {

    }

    @Override
    public void store(MultipartFile file) throws IOException {
        if(!file.isEmpty()){
            log.debug("origin file name : {}", file.getOriginalFilename());
            log.debug("content type : {}",file.getContentType());
            file.transferTo(Paths.get(file.getOriginalFilename()));
        }
    }

    @Override
    public Stream<Path> loadAll() {
        return null;
    }

    @Override
    public Path load(String filename) {
        return null;
    }

    @Override
    public Resource loadAsResource(String filename) {
        return null;
    }

    @Override
    public void deleteAll() {

    }
}
