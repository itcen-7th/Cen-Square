package com.itcen.censquare.global.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Controller
public class MainController {

    // 전역 더미 데이터: 게시글 토픽 목록
    private static final List<Map<String, Object>> DUMMY_POST_TOPICS = Arrays.asList(
            Map.of("id", 1, "tag", "공지", "title", "cen-square 사용 가이드", "likes", 12, "comments", 3),
            Map.of("id", 2, "tag", "자유", "title", "스프링 vs 타임리프 템플릿 뭐가 더 좋나요?", "likes", 5, "comments", 2),
            Map.of("id", 3, "tag", "질문", "title", "게시글 등록이 안됩니다", "likes", 0, "comments", 5)
    );

    // 전역 더미 데이터: 인기 회사 목록
    private static final List<Map<String, Object>> DUMMY_POPULAR_COMPANIES = Arrays.asList(
            Map.of("name", "네이버"),
            Map.of("name", "카카오"),
            Map.of("name", "토스"),
            Map.of("name", "쿠팡"),
            Map.of("name", "배달의민족")
    );

    @GetMapping("/")
    public String showHomePage(Model model) {
        model.addAttribute("posts", DUMMY_POST_TOPICS);
        model.addAttribute("popularCompanies", DUMMY_POPULAR_COMPANIES);
        return "index";
    }
}
