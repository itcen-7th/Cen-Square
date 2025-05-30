package com.itcen.censquare.domain.post.repository;

import com.itcen.censquare.domain.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
