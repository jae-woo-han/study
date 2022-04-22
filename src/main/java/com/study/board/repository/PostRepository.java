package com.study.board.repository;

import com.study.board.vo.BoardItemVO;
import com.study.board.vo.PostCreateForm;
import com.study.board.vo.PostSearchForm;
import com.study.board.vo.PostViewVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface PostRepository {

    List<BoardItemVO> selectBoardViewList(PostSearchForm postSearchForm);
    int selectPostCount(PostSearchForm postSearchForm);
    PostViewVO selectPostOne(int postId);

    int insertPost(PostCreateForm postCreateForm);
    int selectLastInsertKey();
}
