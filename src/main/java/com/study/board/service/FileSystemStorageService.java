package com.study.board.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.stream.Stream;
@Slf4j
@Service
public class FileSystemStorageService implements StorageService{
    @Value("${spring.servlet.multipart.location}")
    private String fileRootPath;
    @Override
    public void init() {
        try {
            Files.createDirectories(Paths.get(fileRootPath));
        } catch (IOException e) {
            log.error("파일 경로 생성 못함",e);
        }
    }

    @Override
    public void store(MultipartFile file) throws IOException {
        if(file.isEmpty()) throw new IOException("file Empty");
        Path root = Paths.get(fileRootPath);
        if (!Files.exists(root))init();
        try(InputStream inputStream = file.getInputStream()){
            Files.copy(inputStream
                    , root.resolve(file.getOriginalFilename())
                    , StandardCopyOption.REPLACE_EXISTING);
        }catch(Exception e){
            log.error("file store Exception",e);
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
