package com.bitc.board1.service;

import com.bitc.board1.dto.BoardDto;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.List;

// 스프링 프레임워크에서 내부 연산을 진행
// 인터페이스이기 때문에 사용하는 방법을 지정한 메소드만 존재함
// 인터페이스이기 때문에 해당 서비스를 구현하는 구현 클래스가 반드시 필요함
public interface BoardService {

  public List<BoardDto> selectBoardList() throws Exception;

  public void insertBoard(BoardDto board, MultipartHttpServletRequest multipart) throws Exception;

  public BoardDto selectBoardDetail(int boardIdx) throws Exception;

  public void updateBoard(BoardDto board) throws Exception;

  public void deleteBoard(int boardIdx) throws Exception;
}









