package com.sbs.basic1.boundedContext.member.repository;

import com.sbs.basic1.boundedContext.member.entity.Member;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository // 컴포넌트랑 똑같음
public class MemberRepository {
  List<Member> members;

  public MemberRepository() {
    members = new ArrayList<>();

    members.add(new Member("user1", "1234"));
    members.add(new Member("abc", "12345"));
    members.add(new Member("test2", "22222"));
    members.add(new Member("love123", "12345"));
    members.add(new Member("giving333", "123456"));
    members.add(new Member("bts0209", "125827"));
    members.add(new Member("good", "120911"));
    members.add(new Member("peace", "23445"));
    members.add(new Member("java777", "122334"));
  }

  public Member findByUserName(String username) {
    return members.stream()
        .filter(member -> member.getUsername().equals(username))
        .findFirst()
        .orElse(null);
  }

  public Member findById(long id) {
    return members.stream()
        .filter(member -> member.getId() == id)
        .findFirst()
        .orElse(null);
  }
}
