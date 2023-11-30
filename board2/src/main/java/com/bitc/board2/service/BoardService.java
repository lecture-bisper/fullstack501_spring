package com.bitc.board2.service;

import com.bitc.board2.dto.BoardDTO;

import java.util.List;

public interface BoardService {
  List<BoardDTO> selectBoardList() throws Exception;

  BoardDTO selectBoardDetail(int boardIdx) throws Exception;
}













