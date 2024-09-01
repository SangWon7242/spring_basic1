package com.sbs.basic1.boundedContext.member.service;

import com.sbs.basic1.base.rsData.RsData;
import com.sbs.basic1.boundedContext.member.entity.Member;
import com.sbs.basic1.boundedContext.member.repository.MemberRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// @Component : 아래 클래스는 Ioc 컨테이너에 의해 생사 소멸이 관리된다.
// @Service : @Component와 같은 의미, 가독성 때문에 이렇게 표기
@AllArgsConstructor
@Service
public class MemberService {
  /*
  @Autowired
  private MemberRepository memberRepository;
  */

  private final MemberRepository memberRepository;

  /*
  public MemberService(MemberRepository memberRepository) {
    this.memberRepository = memberRepository;
  }
   */
  public RsData tryLogin(String username, String password) {
    Member member = memberRepository.findByUserName(username);

    if(member == null) {
      return RsData.of("F-2", "%s(은)는 존재하지 않는 회원입니다.".formatted(username));
    }

    if(!member.getPassword().equals(password)) {
      return RsData.of("F-1", "비밀번호가 일치하지 않습니다.");
    }

    return RsData.of("S-1", "'%s'님 환영합니다.".formatted(username), member);
  }

  public Member findByUserName(String username) {
    return memberRepository.findByUserName(username);
  }

  public Member findById(long id) {
    return memberRepository.findById(id);
  }
}

