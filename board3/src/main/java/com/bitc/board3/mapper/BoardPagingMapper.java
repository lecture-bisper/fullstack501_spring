package com.bitc.board3.mapper;

import com.bitc.board3.dto.BoardDTO;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BoardPagingMapper {
  Page<BoardDTO> selectBoardPageList();
}













