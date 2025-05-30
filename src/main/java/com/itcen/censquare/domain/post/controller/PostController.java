package com.itcen.censquare.domain.post.controller;

import com.itcen.censquare.domain.auth.oauth.CustomUserDetails;
import com.itcen.censquare.domain.post.dto.PostDetailRespDto;
import com.itcen.censquare.domain.post.dto.PostListRespDto;
import com.itcen.censquare.domain.post.dto.PostReqDto;
import com.itcen.censquare.domain.post.service.PostService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/posts")
@RequiredArgsConstructor
public class PostController {

  private final PostService postService;

  @PostMapping
  public ResponseEntity<String> createPost(
      @Valid @RequestBody PostReqDto request,
      @AuthenticationPrincipal CustomUserDetails userDetails) {
    Long postId = postService.createPost(request, userDetails.getMember());
    return ResponseEntity.ok("게시글이 등록되었습니다." + postId);
  }

  @GetMapping
  public ResponseEntity<List<PostListRespDto>> getAllPosts() {
    List<PostListRespDto> posts = postService.getPosts();
    return ResponseEntity.ok(posts);
  }

  @GetMapping("/{postId}")
  public ResponseEntity<PostDetailRespDto> getPostDetail(@PathVariable Long postId) {

    PostDetailRespDto dto = postService.getPostBy(postId);
    return ResponseEntity.ok(dto);
  }

  @PutMapping("/{postId}")
  public ResponseEntity<Void> updatePost(
      @PathVariable Long postId,
      @RequestBody @Valid PostReqDto request,
      @AuthenticationPrincipal CustomUserDetails userDetails) {

    postService.updatePost(postId, request, userDetails.getMember());
    return ResponseEntity.ok().build();
  }

}
