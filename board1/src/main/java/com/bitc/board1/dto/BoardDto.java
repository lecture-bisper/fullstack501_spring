package com.bitc.board1.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

//@Data : Lombok 라이브러리에서 제공하는 어노테이션
// 자동으로 getter/setter 메소드를 만들어줌
// 자동으로 toString() 메소드를 만들어줌
// @Getter, @Setter, @ToString 어노테이션이 합쳐진 것이 @Data 어노테이션
//@Getter : getter 메소드를 자동 생성
//@Setter : setter 메소드를 자동 생성
//@ToString : toString 메소드를 자동 생성
@Getter
@Setter
public class BoardDto {
  private int boardIdx;
  private String title;
  private String contents;
  private int hitCnt;
  private String createDate;
  private String createId;
  private String updateDate;
  private String updateId;
//  deleted_yn 컬럼의 내용을 사용하지 않기 때문에 제외
//  private String deletedYn;
}







