package com.itcen.censquare.domain.post.service;

import com.itcen.censquare.domain.post.dto.PostReqDto;
import com.itcen.censquare.domain.post.entity.Post;
import com.itcen.censquare.domain.post.mapper.PostMapper;
import com.itcen.censquare.domain.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {

  private final PostRepository postRepository;
  private final PostMapper postMapper;

  public void createPost(PostReqDto request) {
//      Member member = memberRepository.findById(memberId)
//          .orElseThrow(() -> new RuntimeException("존재하지 않는 사용자"));

    Post post = postMapper.toEntity(request);

    postRepository.save(post);
  }
}
