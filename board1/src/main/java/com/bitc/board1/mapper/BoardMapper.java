package com.bitc.board1.mapper;

import com.bitc.board1.dto.BoardDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

// 데이터 베이스와 연동되어 있는 인터페이스
// JSP의 DAO의 기능을 수행함
// @Mapper : Mybatis의 Mapper로써 데이터 베이스와 연동됨을 스프링 프레임워크에 알려주는 어노테이션
// mybatis에서 제공하는 SQL 쿼리 xml 파일과 1:1로 연동되어 있음
@Mapper
public interface BoardMapper {

  public List<BoardDto> selectBoardList() throws Exception;

//  연동된 xml 파일의 SQL문을 실행하여 데이터 베이스에 글 등록
  public void insertBoard(BoardDto board) throws Exception;

//  @Param : mybatis의 xml에서 사용할 매개변수의 이름을 직접 지정할 수 있는 어노테이션
//  자바 기본 타입을 사용했을 경우 사용할 수 있으며, 해당 어노테이션을 사용하지 않을 경우 매개변수 이름을 xml에서 그대로 사용할 수 있음
  public BoardDto selectBoardDetail(int boardIdx) throws Exception;

  public void updateHitCount(@Param("boardIdx") int idx) throws Exception;

  public void updateBoard(BoardDto board) throws Exception;

  public void deleteBoard(@Param("boardIdx") int boardIdx) throws Exception;
}










