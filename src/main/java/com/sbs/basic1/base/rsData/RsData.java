package com.sbs.basic1.base.rsData;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class RsData {
  private final String resultCode;
  private final String msg;
  private final Object data;

  public static RsData of(String resultCode, String msg) {
    return new RsData(resultCode, msg, null);
  }

  public static RsData of(String resultCode, String msg, Object data) {
    return new RsData(resultCode, msg, data);
  }

  public boolean isSuccess() {
    return resultCode.startsWith("S-");
  }
  /*
  // Jackson 룰에 의해 함수 타입이 boolean이고 is로 시작하는 메서드는
  // is가 빠지고 메서드명이 소문자로 변경되서 리턴된 결과가 브라우저에 나타남
  public boolean isAge() {
    return true;
  }

  // get이 빠지고 메서드명이 소문자로 변경되서 리턴된 결과가 브라우저에 나타남
  public int getABC() {
    return 10;
  }
   */
}
