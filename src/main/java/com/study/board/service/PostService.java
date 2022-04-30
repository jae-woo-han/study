package com.study.board.service;

import com.study.board.repository.FileRepository;
import com.study.board.repository.PostRepository;
import com.study.board.vo.FileVO;
import com.study.board.vo.PostCreateForm;
import org.springframework.stereotype.Service;

import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

/**
 * 게시글 관련 처리 서비스
 * @author jaewoo
 */
@Service
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public int getLastInsertKeyInPostInfo(PostCreateForm postCreateForm){
        postRepository.insertPost(postCreateForm);
        return postRepository.selectLastInsertKey();
    };


}
