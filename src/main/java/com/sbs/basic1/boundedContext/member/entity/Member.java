package com.sbs.basic1.boundedContext.member.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@ToString
@Getter
public class Member {
  private static int lastId;
  private final long id;
  private final String username;
  @Setter
  private String password;

  static {
    lastId = 0;
  }

  public Member(String username, String password) {
    this(++lastId, username, password);
  }
}
