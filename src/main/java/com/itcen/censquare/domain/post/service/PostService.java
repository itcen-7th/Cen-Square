package com.itcen.censquare.domain.post.service;

import com.itcen.censquare.domain.member.entity.Member;
import com.itcen.censquare.domain.post.dto.PostDetailRespDto;
import com.itcen.censquare.domain.post.dto.PostListRespDto;
import com.itcen.censquare.domain.post.dto.PostReqDto;
import com.itcen.censquare.domain.post.entity.Post;
import com.itcen.censquare.domain.post.mapper.PostMapper;
import com.itcen.censquare.domain.post.repository.PostRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class PostService {

  private final PostRepository postRepository;
  private final PostMapper postMapper;

  public Long createPost(PostReqDto request, Member member) {

    Post post = postMapper.toEntity(request, member);

    return postRepository.save(post).getPostId();
  }

  @Transactional(readOnly = true)
  public List<PostListRespDto> getPosts() {
    List<Post> posts = postRepository.findAll();
    return posts.stream()
        .map(postMapper::toDto)
        .collect(Collectors.toList());
  }

  public PostDetailRespDto getPostBy(Long postId) {
    postRepository.incrementViewCount(postId); // 조회수 1 증가

    Post post = postRepository.findById(postId)
        .orElseThrow(() -> new IllegalArgumentException("게시글을 찾을 수 없습니다."));

    return postMapper.toDetailDto(post);
  }

  public void updatePost(Long postId, PostReqDto request, Member member) {
    Post post = postRepository.findById(postId)
        .orElseThrow(() -> new IllegalArgumentException("게시글을 찾을 수 없습니다."));

    if (!post.getMember().getMemberId().equals(member.getMemberId())) {
      throw new AccessDeniedException("게시글 수정 권한이 없습니다.");
    }

    post.update(request.getTitle(), request.getContent(), request.getCategory());

  }
}
