package com.itcen.censquare.domain.post.entity.Enum;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Category {
    GENERAL("자유"),
    CORPORATE("법인"),
    GENERATION("기수");

    private final String text;
}
