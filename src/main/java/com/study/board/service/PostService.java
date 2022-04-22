package com.study.board.service;

import com.study.board.repository.PostRepository;
import com.study.board.vo.PostCreateForm;
import org.springframework.stereotype.Service;

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
