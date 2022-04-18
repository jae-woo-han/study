package com.study.board.repository;

import com.study.board.vo.BoardItemVO;
import com.study.board.vo.PostSearchForm;
import com.study.board.vo.PostViewVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface PostRepository {

    public List<BoardItemVO> selectBoardViewList(PostSearchForm postSearchForm);
    public PostViewVO selectPostOne(int postId);
}
