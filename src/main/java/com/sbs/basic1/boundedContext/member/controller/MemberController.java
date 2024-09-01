package com.sbs.basic1.boundedContext.member.controller;

import com.sbs.basic1.base.rq.Rq;
import com.sbs.basic1.base.rsData.RsData;
import com.sbs.basic1.boundedContext.member.entity.Member;
import com.sbs.basic1.boundedContext.member.service.MemberService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;

@Controller
@RequestMapping("/member")
public class MemberController {
  
  /*
  @Autowired // 필드 주입
  private MemberService memberService;  
   */

  private final MemberService memberService;

  @Autowired // 생성자 주입
  public MemberController(MemberService memberService) {
    this.memberService = memberService;
  }

  @GetMapping("/login")
  @ResponseBody
  public RsData login(String username, String password, HttpServletRequest req, HttpServletResponse resp) {
    Rq rq = new Rq(req, resp);

    if (username == null || username.trim().isEmpty()) {
      return RsData.of("F-3", "username(을)를 입력해주세요.");
    }

    if (password == null || password.trim().isEmpty()) {
      return RsData.of("F-4", "password(을)를 입력해주세요.");
    }

    RsData rsData = memberService.tryLogin(username, password);

    if (rsData.isSuccess()) {
      Member member = (Member) rsData.getData();
      rq.setCookie("loginedMemberId", member.getId());
    }

    return rsData;
  }

  @GetMapping("/logout")
  @ResponseBody
  public RsData logout(HttpServletRequest req, HttpServletResponse resp) {
    Rq rq = new Rq(req, resp);

    boolean cookieRemoved = rq.removeCookie("loginedMemberId");

    if(!cookieRemoved) {
      return RsData.of("F-1", "이미 로그아웃 상태입니다.");
    }

    return RsData.of("S-1", "로그아웃 되었습니다.");
  }

  @GetMapping("/me")
  @ResponseBody
  public RsData showMe(HttpServletRequest req, HttpServletResponse resp) {
    Rq rq = new Rq(req, resp);

    long loginedMemberId = rq.getCookieAsLong("loginedMemberId", 0);

    boolean isLogined = loginedMemberId > 0;

    if (!isLogined) {
      return RsData.of("F-1", "로그인 후 이용해주세요.");
    }

    Member member = memberService.findById(loginedMemberId);

    return RsData.of("S-1", "당신의 username(은)는 '%s' 입니다.".formatted(member.getUsername()));
  }
}
