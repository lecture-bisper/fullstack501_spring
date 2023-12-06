package com.bitc.board1.dto;

import lombok.Data;

//파일 정보를 저장하기 위한 DTO 클래스
@Data
public class BoardFileDto {
  private int idx; // 파일 번호, PK
  private int boardIdx; // 게시물 번호, FK
  private String originalFileName; // 파일 원본 이름
  private String storedFileName; // 서버에 저장된 파일 경로 및 이름
  private long fileSize; // 파일 크기
  private String createId;
  private String createDate;
  private String updateId;
  private String updateDate;
}







