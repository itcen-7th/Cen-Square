package com.itcen.censquare.domain.post.entity;

import com.itcen.censquare.domain.member.entity.Member;
import com.itcen.censquare.domain.post.entity.enums.Category;
import com.itcen.censquare.global.entity.TimeStampedEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Post extends TimeStampedEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "post_id")
  private Long postId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "member_id", nullable = false)
  private Member member;

  @Enumerated(EnumType.STRING)
  @Column(name = "category", nullable = false)
  private Category category;

  @Column(name = "title", nullable = false, length = 255)
  private String title;

  @Column(name = "content", columnDefinition = "TEXT")
  private String content;

  @Builder.Default
  @Column(name = "view_count")
  private Long viewCount = 0L;

  @Builder.Default
  @Column(name = "like_count")
  private Long likeCount = 0L;

  @Column(name = "deleted")
  private Boolean deleted;

  @Column(name = "deleted_at")
  private LocalDateTime deletedAt;
}
