package com.bitc.board3.service;

import com.bitc.board3.dto.BoardDTO;
import com.github.pagehelper.Page;

import java.util.List;

public interface BoardPagingService {
  Page<BoardDTO> selectBoardPageList(int pageNum);
}













