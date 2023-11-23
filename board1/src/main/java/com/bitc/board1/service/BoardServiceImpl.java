package com.bitc.board1.service;

import com.bitc.board1.dto.BoardDto;
import com.bitc.board1.mapper.BoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// @Service : 스프링 프레임워크에 해당 파일이 서비스 파일임을 알려주는 어노테이션
@Service
// 구현 클래스 이므로 부모인 서비스 인터페이스를 상속받아 구현해야 함
public class BoardServiceImpl implements BoardService {

  @Autowired
//  데이터 베이스를 사용하기 위한 Mapper 인터페이스
  private BoardMapper boardMapper;

//  상속받아 구현한 메소드
  @Override
  public List<BoardDto> selectBoardList() throws Exception {
    return boardMapper.selectBoardList();
  }
}







