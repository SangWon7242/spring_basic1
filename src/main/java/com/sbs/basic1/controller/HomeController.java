package com.sbs.basic1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
// 개발자가 스프링부트한테 말한다.
// HomeController는 컨트롤러다.
public class HomeController {
  /*
  @GetMapping 의 의미
  // 개발자가 스프링부트한테 말한다
  // 만약에 /home/main이라는 요청이 들어오면 아래 메서드를 실행시켜줘
  */

  @GetMapping("/home/main")
  @ResponseBody
  // @ResponseBody의 의미
  // 아래 메서드를 실행한 후 리턴값을 응답으로 삼아서
  // body에 출력해줘
  public String showHome() {
    return "안녕하세요.";
  }
}
