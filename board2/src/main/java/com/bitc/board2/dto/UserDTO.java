package com.bitc.board2.dto;

import lombok.Data;

// DTO : 데이터 베이스와 Java 클래스 간의 데이터를 주고 받기 위해서 데이터 타입을 맞추기 위한 클래스
// 사용자가 원하는 형태대로 생성할 수 있음
@Data
public class UserDTO {
  private String userId;
  private String userPw;
  private String userName;
  private String userEmail;
  private String userCreateDate;
  private String userUpdateDate;
}







