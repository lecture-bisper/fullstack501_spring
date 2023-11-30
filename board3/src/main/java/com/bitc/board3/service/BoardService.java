package com.bitc.board3.service;

import com.bitc.board3.dto.BoardDTO;

import java.util.List;

public interface BoardService {
  List<BoardDTO> selectBoardList();

  BoardDTO selectBoardDetail(int boardIdx);

  void insertBoard(BoardDTO board);

  void updateBoard(BoardDTO board);

  void deleteBoard(int boardIdx);
}













