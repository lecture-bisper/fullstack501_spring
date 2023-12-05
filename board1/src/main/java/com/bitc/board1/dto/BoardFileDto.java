package com.bitc.board1.dto;

import lombok.Data;

@Data
public class BoardFileDto {
  private int idx;
  private int boardIdx;
  private String originalFileName;
  private String storedFileName;
  private long fileSize;
}







