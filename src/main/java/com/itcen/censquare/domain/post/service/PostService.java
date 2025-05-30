package com.itcen.censquare.domain.post.service;

import com.itcen.censquare.domain.member.entity.Member;
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

  public Long createPost(PostReqDto request, Member member) {

    Post post = postMapper.toEntity(request, member);

    return postRepository.save(post).getPostId();
  }
}
