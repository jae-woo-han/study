package com.study.board.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
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
    public void store(MultipartFile file,String fileName) throws IOException {
        if(file.isEmpty()) throw new IOException("file Empty");
        Path root = Paths.get(fileRootPath);
        if (!Files.exists(root))init();
        try(InputStream inputStream = file.getInputStream()){
            Files.copy(inputStream
                    , root.resolve(fileName)
                    , StandardCopyOption.REPLACE_EXISTING);
        }catch(Exception e){
            log.error("file store Exception",e);
        }
    }

    @Override
    public Stream<Path> loadAll() {
        try{
            Path root = Paths.get(fileRootPath);
            return Files.walk(root,1)
                    .filter(path -> !path.equals(root));
        }catch(IOException e){
            log.error("파일 읽기 실패",e);
        }
        return null;
    }

    @Override
    public Path load(String fileName) {
        Path root = Paths.get(fileRootPath).resolve(fileName);
        return root;
    }

    @Override
    public Resource loadAsResource(String fileName) {
        try{
            Path file = load(fileName);
            Resource resource = new UrlResource(file.toUri());
            if(resource.exists()||resource.isReadable()){
                return resource;
            }else{
                log.error("파일 읽기 에러");
            }
        }catch (MalformedURLException e){
            log.error("파일을 읽을 수 없음", e);
        }
        return null;
    }

    @Override
    public void deleteAll() {

    }
}
