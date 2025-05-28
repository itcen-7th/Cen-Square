package com.itcen.censquare.domain.comment.entity;

import com.itcen.censquare.domain.member.entity.Member;
import com.itcen.censquare.domain.post.entity.Post;
import com.itcen.censquare.global.entity.TimeStampedEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Comment extends TimeStampedEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "comment_id")
  private Long commentId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "member_id", nullable = false)
  private Member member;

  @ManyToOne
  @JoinColumn(name = "post_id", nullable = false)
  private Post post;

  @Lob
  @Column(nullable = false)
  private String content;

}
