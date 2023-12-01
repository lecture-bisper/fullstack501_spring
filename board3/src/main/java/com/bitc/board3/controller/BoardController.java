package com.bitc.board3.controller;

import com.bitc.board3.dto.BoardDTO;
import com.bitc.board3.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/board3")
public class BoardController {
//  RESTful 이란 : 자원의 이름으로 구분하여 해당 자원의 정보를 주고 받는 방식
//  HTTP URI를 통해 자원을 명시하고 HTTP Method(GET, POST, PUT, DELETE)를 통해 자원에 대한 CRUD 명령을 적용하는 방식

//  URI : Uniform Resource Identifier 의 줄임말, 자원 식별자
//  URL : Uniform Resosurce Locator의 줄임말, 자원의 위치
//  현재는 거의 같은 의미로 사용함

//  RESTful 방식은 데이터를 전송하는 방법을 지정함
//  HttpMethod를 사용하여 GET(조회), POST(입력), PUT(수정), DELETE(삭제)로 구분하여 사용함
//  @RequestMapping 어노테이션 사용 시 URI를 value 속성을 사용하여 설정하고, method 속성을 사용하여 GET, POST, PUT, DELETE를 설정하여 사용함
//  @GetMapping, @PostMapping, @PutMapping, @DeleteMapping 어노테이션을 사용하여 구분해도 상관없음

//  전체 URL 설정 방법
//  서버URL:포트번호/기본주소/추가주소/게시물번호

  @Autowired
  private BoardService boardService;

//  url을 여러개 사용하고자 할 경우 {} 로 감싼 후 여러개 입력
  @RequestMapping({"", "/"})
  public String index() throws Exception {
    return "board3/index";
  }

//  게시판 글 목록 페이지
  @RequestMapping(value = "/board", method = RequestMethod.GET)
  public ModelAndView boardList() throws Exception {
    ModelAndView mv = new ModelAndView("board3/boardList");

    List<BoardDTO> boardList = boardService.selectBoardList();
    mv.addObject("boardList", boardList);

    return mv;
  }

//  게시판 글 상세보기
//  @GetMapping : 데이터 전송 방식을 GET으로 설정한 URL만 접속, RequestMapping(method = RequestMethod.GET)과 동일한 방식
  @GetMapping("/board/{boardIdx}")
  public ModelAndView boardDetail(@PathVariable("boardIdx") int boardIdx) throws Exception {
    ModelAndView mv = new ModelAndView("board3/boardDetail");

    BoardDTO board = boardService.selectBoardDetail(boardIdx);
    mv.addObject("board", board);

    return mv;
  }

//  게시판 글 등록하기 form 화면
  @GetMapping("/board/insert")
  public String boardInsertView() throws Exception {
    return "board3/boardInsert";
  }

//  사용자가 입력한 데이터를 기반으로 게시판 글 등록하기
//  URL이 동일하더라도 method 타입이 다르면 동일한 URL을 사용할 수 있음
//  @PostMapping : 데이터 전송 방식을 POST으로 설정한 URL만 접속, RequestMapping(method = RequestMethod.POST)과 동일한 방식
  @PostMapping("/board/insert")
  public String boardInsertProcess(BoardDTO board) throws Exception {
    boardService.insertBoard(board);

    return "redirect:/board3/board";
  }

//  게시글 수정하기
//  @PutMapping : 데이터 전송 방식을 PUT으로 설정한 URL만 접속, RequestMapping(method = RequestMethod.PUT)과 동일한 방식
  @PutMapping("/board/{boardIdx}")
  public String boardUpdate(BoardDTO board) throws Exception {
    boardService.updateBoard(board);

    return "redirect:/board3/board";
  }

//  게시물 삭제하기
//  @DeleteMapping : 데이터 전송 방식을 DELETE으로 설정한 URL만 접속, RequestMapping(method = RequestMethod.DELETE)과 동일한 방식
//  @PathVariable : rest 방식 사용 시 {} 로 지정해 놓은 리소스 값을 받아오는 어노테이션, @RequestParam과 같이 파라미터값을 가져오기 위한 어노테이션
  @DeleteMapping("/board/{boardIdx}")
  public String boardDelete(@PathVariable("boardIdx") int boardIdx) throws Exception {
    boardService.deleteBoard(boardIdx);

    return "redirect:/board3/board";
  }
}







