package com.bitc.board3.controller;


import com.bitc.board3.dto.BoardDTO;
import com.bitc.board3.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// @RestController : @Controller + @ResponseBody 어노테이션이 하나로 합쳐진 어노테이션
// @Controller와 @ResponseBody 어노테이션을 클래스에 함께 사용해도 동일한 효과를 가짐
// ajax 통신 시 rest api 방식으로 데이터를 전송하는 컨트롤러를 만들어서 사용하면 편리함
// 리액트 사용 시 프론트엔드 부분과 백엔드 부분이 완전히 분리되어 있기 때문에 데이터를 주고 받기 위해서 ajax 통신이 필요함

// RESTful API : REST 방식과 동일한 형태로 HTTP 프로토콜을 사용하지만, 클라이언트로 전달하는 데이터가 View를 제외한 데이터만 전달하는 방식
// 예전에는 클라이언트가 컴퓨터 밖에 없었기 때문에 웹 브라우저가 클라이언트의 기능을 모두 담당했음
// 서버의 기능이 웹 서버의 기능만 주로 사용되었기 때문에 클라이언트로 전달하는 데이터가 View와 Model 데이터 모두 필요한 형태였음
// 현재는 클아이언트가 다양한 디바이스로 동작하기 때문에 View 부분은 웹 브라우저가 반드시 필요한 것이 아니게 되어 데이터만 전달하는 방식으로 변경되었음
@RestController
@RequestMapping("/board3/api")
public class BoardApiController {

  @Autowired
  private BoardService boardService;

//  게시판 목록 가져오기 API
  @RequestMapping(value = "/board", method = RequestMethod.GET)
  public Object boardList() throws Exception {
    List<BoardDTO> boardList = boardService.selectBoardList();

    return boardList;
  }

//  게시판 글 상세 정보 가져오기 API
  @GetMapping("/board/{boardIdx}")
  public Object boardDetail(@PathVariable("boardIdx") int boardIdx) throws Exception {
    BoardDTO board = boardService.selectBoardDetail(boardIdx);

    return board;
  }

//  게시판 글 삭제하기 API
  @DeleteMapping("/board/{boardIdx}")
  public void boardDelete(@PathVariable("boardIdx") int boardIdx) throws Exception {
    boardService.deleteBoard(boardIdx);
  }

//  게시판 글 등록하기 API
//  @RequestBody : 클라이언트에서 서버로 전달하는 데이터를 Http 프로토콜의 body 부분에 데이터를 저장하여 서버로 전달하는 방식
//  @RequestParam은 서버 주소(URI)에 데이터를 포함하여 전달하는 방식
  @PostMapping("/board")
  public void boardInsert(@RequestBody BoardDTO board) throws Exception {
    boardService.insertBoard(board);
  }
  
//  게시판 글 수정하기 API
//  rest api 방식으로 게시물 수정 시 매개벼수로 받아오는 BoardDTO 타입의 객체에 게시물 번호인 boardIdx가 없을 수 있음 (기본값인 0이 사용될 수 있음)
//  @PathVariable 어노테이션으로 게시물 번호를 직접 받아온 후 BoardDTO 타입의 객체에 게시글 번호를 추가하여 사용함
  @PutMapping("/board/{boardIdx}")
  public void boardUpdate(@PathVariable("boardIdx") int boardIdx, @RequestBody BoardDTO board) throws Exception {
//    파라미터 값으로 받아온 게시글 번호를 파라미터로 받아온 BoardDTO 객체에 저장함
    board.setBoardIdx(boardIdx);
//    서비스를 사용하여 DB 의 내용 수정
    boardService.updateBoard(board);
  }

}

//문제 1) BoardAjaxController 를 생성하고 View 화면을 출력하기 위한 템플릿 ajax/boardList.html 파일도 함께 생성한 후 html 파일에서 목록 조회 버튼 클릭 시 게시물 전체 목록이 ajax 통신을 통해서 내용 및 UI가 출력되는 프로그램을 작성하세요 (게시물 목록은 rest api를 통해서 가져옴)
// * controller : BoardAjaxController.java
// * url : /board3/ajax/boardList
// * 게시물 목록 URL : /board3/api/board
// * html 파일 : ajax/boardList.html







