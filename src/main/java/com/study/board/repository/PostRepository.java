package com.study.board.repository;

import com.study.board.vo.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface PostRepository {

    List<BoardItemVO> selectBoardViewList(PostSearchForm postSearchForm);
    int selectPostCount(PostSearchForm postSearchForm);
    PostViewVO selectPostOne(int postId);

    int insertPost(PostCreateForm postCreateForm);
    int selectLastInsertKey();
    int updatePostOne(PostUpdateForm postUpdateForm);

    String selectPostPasswordOne(int postId);

    int deletePostOne(int postId);
    int updateViewCount(@Param("postId") int postId,@Param("viewCount") int viewCount);
}
