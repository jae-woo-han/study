package com.study.board.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.util.stream.Stream;
/**
 * File StorageService Interface
 * 파일업로드에서 사용하는 서비스
 * 스프링 가이드 기반
 * https://spring.io/guides/gs/uploading-files/
 *
 * @author jaewoo
 * @version 0.0.1
 */
public interface StorageService {
    /**
     * 런타임시에 해당 서비스 실행시키는 메소드
     * 지금 app에서 어떤식으로 사용할지는 모르겠다
     */
    void init();

    /**
     * MultipartFile을 저장
     * @param file
     */
    void store(MultipartFile file) throws IOException;

    /**
     * 저장된 파일 경로 전부 load
     * @return
     */
    Stream<Path> loadAll();

    /**
     * filename 따라 단일 load
     * @param filename
     * @return
     */
    Path load(String filename);

    /**
     * Resource 반환
     * @param filename
     * @return
     */
    Resource loadAsResource(String filename);

    /**
     * 런타임시에 storage 데이터 전부 지우는 clear 메소드
     * 지금 app에서 어떤식으로 사용할지는 모르겠다
     */
    void deleteAll();
}
