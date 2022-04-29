package com.study.board.controller;

import com.study.board.exception.ValidateException;
import com.study.board.repository.CategoryRepository;
import com.study.board.repository.FileRepository;
import com.study.board.repository.PostRepository;
import com.study.board.service.StorageService;
import com.study.board.vo.FileVO;
import com.study.board.vo.PostCreateForm;
import com.study.board.vo.PostUpdateForm;
import com.study.board.vo.PostViewVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.UUID;
@Slf4j
@Transactional
@Controller
public class PostController {

    private final PostRepository postRepository;
    private final CategoryRepository categoryRepository;
    private final FileRepository fileRepository;
    private final StorageService storageService;
    public PostController(PostRepository postRepository, CategoryRepository categoryRepository, FileRepository fileRepository, StorageService storageService) {
        this.postRepository = postRepository;
        this.categoryRepository = categoryRepository;
        this.fileRepository = fileRepository;
        this.storageService = storageService;
    }
    @GetMapping("/post/{postId}")
    public String moveViewPostPage(Model model, @PathVariable("postId") int postId){
        PostViewVO postView = postRepository.selectPostOne(postId);
        model.addAttribute("postView",postView);
        List<FileVO> fileVOList = fileRepository.selectFilesByPostId(postId);
        model.addAttribute("fileList",fileVOList);
        return "viewPost";
    }
    @GetMapping("/file/{fileSeq}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable int fileSeq){
        FileVO fileVO = fileRepository.selectFileByFileSeq(fileSeq);
        Resource file = storageService.loadAsResource(fileVO.getEncodeFileName());
        String downloadFileName = URLEncoder.encode(fileVO.getFileName()+"."+fileVO.getFileType(), StandardCharsets.UTF_8);
        log.debug("download File Name : {}",downloadFileName);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + downloadFileName + "\"").body(file);
    }
    @GetMapping("/write")
    public String movePostWritePage(Model model){
        model.addAttribute("categoryList",categoryRepository.selectCategoryAll());
        return "writePost";
    }
    @PostMapping("/post")
    public RedirectView writePost(Model model
            , PostCreateForm postForm
            , MultipartFile[] files)
            throws IOException {
        postRepository.insertPost(postForm);
        int insertedPostId = postForm.getPostId();

        for (MultipartFile multipartFile:files) {
            String originName = multipartFile.getOriginalFilename();
            if(StringUtils.isNoneEmpty(originName)){
                String fileName = originName.substring(0,originName.lastIndexOf("."));
                String fileType = originName.substring(originName.lastIndexOf(".")+1);
                String encodeFileName = UUID.randomUUID().toString();
                String path ="/";

                FileVO fileVO = new FileVO();
                fileVO.setPostId(insertedPostId);
                fileVO.setFileName(fileName);
                fileVO.setFileType(fileType);
                fileVO.setEncodeFileName(encodeFileName);
                fileVO.setDirectoryPath(path);
                storageService.store(multipartFile,encodeFileName);
                fileRepository.insertFileInfo(fileVO);
            }else {
                throw new ValidateException("파일이름 null");
            }

        }
        model.addAttribute("postId",insertedPostId);
        String redirectPath = "/post/"+ insertedPostId;
        return new RedirectView(redirectPath);
    }
    @PostMapping("/passConfirm/{postId}/delete")
    public RedirectView deletePost(Model model, @PathVariable("postId") int postId,String password){
        String selectPassword = postRepository.selectPostPasswordOne(postId);

        if(StringUtils.isNoneEmpty(password)){
            log.debug("********password Confirm********");
            log.debug("{} == {}",password,selectPassword);
            if(StringUtils.equals(password,selectPassword)){
                postRepository.deletePostOne(postId);
                model.addAttribute("alertMessage","삭제가 완료되었습니다.");
            }else {
                throw new ValidateException("비밀번호 불일치");
            }
        }

        return new RedirectView("/board");
    }
    @PostMapping("/passConfirm/{postId}/update")
    public String movePostUpdatePage(Model model, @PathVariable("postId") int postId,String password){
        String selectPassword = postRepository.selectPostPasswordOne(postId);

        PostViewVO postView;
        if(StringUtils.isNoneEmpty(password)){
            log.debug("********password Confirm********");
            log.debug(password+" == "+selectPassword);
            if(StringUtils.equals(password,selectPassword)){

                postView = postRepository.selectPostOne(postId);
                model.addAttribute("postView",postView);
            }else {
                throw new ValidateException("비밀번호 불일치");
            }
        }

        return "updatePost";
    }
    @PostMapping("/post/{postId}/update")
    public RedirectView updatePost(Model model, @PathVariable("postId") int postId, PostUpdateForm postUpdateForm){
        //비밀번호확인
        String selectPassword = postRepository.selectPostPasswordOne(postId);
        String password = postUpdateForm.getPassword();
        //수정 작업
        if(StringUtils.isNoneEmpty(password)){
            log.debug("********password Confirm********");
            log.debug("{} == {}",password,selectPassword);
            if(StringUtils.equals(password,selectPassword)){
                postRepository.updatePostOne(postUpdateForm);
                model.addAttribute("alertMessage","수정이 완료되었습니다.");
            }else {
                throw new ValidateException("비밀번호 불일치");
            }
        }
        //해당 게시글 페이지로 이동
        return new RedirectView("/post/"+postId);
    }

    @GetMapping("/passConfirm/update/{postId}")
    public String movePostUpdateConfirmPage(Model model,@PathVariable("postId")int postId){
        model.addAttribute("postId", postId);
        model.addAttribute("method","update");

        return "confirmPost";
    }
    @GetMapping("/passConfirm/delete/{postId}")
    public String movePostDeleteConfirmPage(Model model,@PathVariable("postId")int postId){
        model.addAttribute("postId", postId);
        model.addAttribute("method","delete");

        return "confirmPost";
    }
}
