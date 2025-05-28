package com.itcen.censquare.domain.post.controller;

import com.itcen.censquare.domain.post.dto.PostReqDto;
import com.itcen.censquare.domain.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/posts")
@RequiredArgsConstructor
public class PostController {

  private final PostService postService;

  @PostMapping("/create")
  public ResponseEntity<?> createPost(@RequestBody PostReqDto request) {
    postService.createPost(request);
    return ResponseEntity.ok("게시글이 등록되었습니다.");
  }
}
