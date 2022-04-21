package com.study.board.repository;

import com.study.board.vo.CategoryInfoVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CategoryRepository {
    public List<CategoryInfoVO> selectCategoryAll();
}
