package com.bitc.board1.mapper;

import com.bitc.board1.dto.BoardDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

// 데이터 베이스와 연동되어 있는 인터페이스
// JSP의 DAO의 기능을 수행함
// @Mapper : Mybatis의 Mapper로써 데이터 베이스와 연동됨을 스프링 프레임워크에 알려주는 어노테이션
// mybatis에서 제공하는 SQL 쿼리 xml 파일과 1:1로 연동되어 있음
@Mapper
public interface BoardMapper {

  public List<BoardDto> selectBoardList() throws Exception;
}
