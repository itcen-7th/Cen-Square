package com.itcen.censquare.domain.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MemberViewController {

  @GetMapping("/login")
  public String showLoginPage() {
    return "redirect:/";
  }

  @GetMapping("/member/signup-extra")
  public String showSignupExtraPage() {
    return "member/signup-extra";
  }
}
