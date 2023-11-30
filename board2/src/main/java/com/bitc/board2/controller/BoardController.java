package com.bitc.board2.controller;

import com.bitc.board2.dto.BoardDTO;
import com.bitc.board2.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/board2")
public class BoardController {

  @Autowired
  private BoardService boardService;

  @RequestMapping("/")
  public String index() throws Exception {
    return "index";
  }

  @RequestMapping("/boardList.do")
  public ModelAndView boardList() throws Exception {
    ModelAndView mv = new ModelAndView("board2/boardList");

    List<BoardDTO> boardList = boardService.selectBoardList();
    mv.addObject("boardList", boardList);

    return mv;
  }

  @RequestMapping("/boardDetail.do")
  public ModelAndView boardDetail(@RequestParam("boardIdx") int boardIdx) throws Exception {
    ModelAndView mv = new ModelAndView("board2/boardDetail");

    BoardDTO board = boardService.selectBoardDetail(boardIdx);
    mv.addObject("board", board);

    return mv;
  }
}







