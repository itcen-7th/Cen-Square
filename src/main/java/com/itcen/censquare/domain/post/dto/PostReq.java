package com.itcen.censquare.domain.post.dto;

import com.itcen.censquare.domain.post.entity.Enum.Category;
import lombok.Getter;

@Getter
public class PostReq {
    private Long memberId;
    private Category category;
    private String title;
    private String content;
}
