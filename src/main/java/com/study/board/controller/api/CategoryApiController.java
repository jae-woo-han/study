package com.study.board.controller.api;

import com.study.board.repository.CategoryRepository;
import com.study.board.vo.CategoryInfoVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@Transactional
@RestController
@RequestMapping("api")
public class CategoryApiController {

    private final CategoryRepository categoryRepository;

    public CategoryApiController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @GetMapping("/category")
    public ResponseEntity<List<CategoryInfoVO>> getCategoryList(){
        return new ResponseEntity<>(categoryRepository.selectCategoryAll(),null, HttpStatus.OK);
    }
}
