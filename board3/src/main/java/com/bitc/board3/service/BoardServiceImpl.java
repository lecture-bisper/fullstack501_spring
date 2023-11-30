package com.bitc.board3.service;

import com.bitc.board3.dto.BoardDTO;
import com.bitc.board3.mapper.BoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardServiceImpl implements BoardService {

  @Autowired
  private BoardMapper boardMapper;

  @Override
  public List<BoardDTO> selectBoardList() {
    return boardMapper.selectBoardList();
  }

  @Override
  public BoardDTO selectBoardDetail(int boardIdx) {
    return boardMapper.selectBoardDetail(boardIdx);
  }

  @Override
  public void insertBoard(BoardDTO board) {
    boardMapper.insertBoard(board);
  }

  @Override
  public void updateBoard(BoardDTO board) {
    boardMapper.updateBoard(board);
  }

  @Override
  public void deleteBoard(int boardIdx) {
    boardMapper.deleteBoard(boardIdx);
  }
}







