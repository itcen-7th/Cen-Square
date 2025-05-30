package com.itcen.censquare.domain.post.repository;

import com.itcen.censquare.domain.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PostRepository extends JpaRepository<Post, Long> {

  @Modifying
  @Query("update Post p set p.viewCount = p.viewCount + 1 where p.postId = :postId")
  void incrementViewCount(@Param("postId") Long postId);
}
