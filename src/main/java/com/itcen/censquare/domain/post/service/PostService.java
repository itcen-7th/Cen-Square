package com.itcen.censquare.domain.post.service;

import com.itcen.censquare.domain.post.dto.PostReq;
import com.itcen.censquare.domain.post.entity.Post;
import com.itcen.censquare.domain.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public void createPost(PostReq request) {
        Post post = Post.builder()
//                .memberId(request.getMemberId())
                .category(request.getCategory())
                .title(request.getTitle())
                .content(request.getContent())
                .viewCount(0L)
                .likeCount(0L)
                .deleted(false)
                .build();

        postRepository.save(post);
    }
}
