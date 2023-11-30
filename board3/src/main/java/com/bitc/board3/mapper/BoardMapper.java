package com.bitc.board3.mapper;

import com.bitc.board3.dto.BoardDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {
  List<BoardDTO> selectBoardList();

  BoardDTO selectBoardDetail(int boardIdx);

  void insertBoard(BoardDTO board);

  void updateBoard(BoardDTO board);

  void deleteBoard(int boardIdx);
}













