package com.bitc.board1.controller;

import com.bitc.board1.dto.BoardDto;
import com.bitc.board1.mapper.BoardMapper;
import com.bitc.board1.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

// @Controller : 해당 클래스가 Spring MVC 의 Controller 파일임을 스프링 프레임워크에 알려주는 어노테이션
// JSP MVC2 방식의 Servlet 파일과 동일한 역할을 함
// JSP Servlet은 URL 하나에 Servlet 파일 하나를 연동하여 사용하였지만 Spring MVC의 Controller는 하나의 Controller 파일에 @RequestMapping 을 사용하여 메소드에 URL을 연동하여 사용함
// Controller 파일 하나에 여러개의 URL을 사용할 수 있음
@Controller
public class BoardController {

  @Autowired
  private BoardService boardService;

//  @RequestMapping : 사용자가 접속할 URL을 지정하는 어노테이션
//  클래스 및 메소드에 적용할 수 있음
//  클래스에 적용 시 전체 URL의 기준이 되는 URL로 사용할 수 있음
//  메소드에 적용 시 상세 URL 로 사용할 수 있음
//  주요 속성으로 value, method 가 있음
//  value : URL 을 입력, RequestMapping만 입력 시 value 속성명 생략 가능
//  method : 클라이언트의 데이터 전달 방식을 지정(GET, POST, UPDATE, DELETE)
  @RequestMapping("/")
  public String index() throws Exception {
    //    반환값을 String 사용 시 'resources/templates' 폴더의 html 파일을 뜻함
    return "index";
  }

  
  @RequestMapping(value = "/board/boardList.do", method = RequestMethod.GET)
  public ModelAndView selectBoardList() throws Exception {
//    ModelAndView : View 템플릿과 데이터인 Model을 동시에 가지고 있는 클래스
//    생성자로 View 템플릿의 위치를 지정
//    setView() 메소드를 통해서 설정한 View 템플릿을 변경할 수 있음
    ModelAndView mv = new ModelAndView("board/boardList");
    
//    서비스 객체를 통해서 데이터베이스에서 데이터를 가져옴
    List<BoardDto> boardList = boardService.selectBoardList();
//    addObject() : JSP 의 request.setAttribute() 와 동일한 역할, ModelAndView 클래스 타입의 객체에 데이터를 추가하는 역할
    mv.addObject("boardList", boardList);

//    클라이언트에서 확인할 View 템플릿을 출력
    return mv;
  }

//  글쓰기 사용자 입력 뷰 페이지
  @RequestMapping("/board/boardWrite.do")
  public String boardWrite() throws Exception {
    return "board/boardWrite";
  }

//  사용자가 입력한 데이터로 글쓰기 처리
//  매개변수를 BoardDto 클래스 타입으로 지정했기 때문에 html의 input 태그 중 name 속성값을 BoardDto 클래스의 필드명과 동일하게 사용해야 함
  @RequestMapping("/board/insertBoard.do")
  public String insertBoard(BoardDto board, MultipartHttpServletRequest multipart) throws Exception {
//    서비스를 이용하여 데이터 베이스에 데이터 입력
//    boardService.insertBoard(board);
      boardService.insertBoard(board, multipart);
//    지정한 주소로 리다이렉트
    return "redirect:/board/boardList.do";
  }

//  @RequestParam : 클라이언트에서 서버로 전달하는 데이터를 받아오기 위한 어노테이션
//  jsp의 request.getParameter() 메소드와 같은 역할
  @RequestMapping("/board/boardDetail.do")
  public ModelAndView boardDetail(@RequestParam int boardIdx) throws Exception {
    ModelAndView mv = new ModelAndView("board/boardDetail");

    BoardDto board = boardService.selectBoardDetail(boardIdx);
    mv.addObject("board", board);

    return mv;
  }

//  게시글 수정하기
  @RequestMapping("/board/updateBoard.do")
  public String updateBoard(BoardDto board) throws Exception {
//    Service를 사용하여 사용자가 입력한 데이터로 변경
    boardService.updateBoard(board);

    return "redirect:/board/boardList.do";
//    return "redirect:/board/boardDetail.do?boardIdx=" + board.getBoardIdx();
  }

//  게시글 삭제하기
  @RequestMapping("/board/deleteBoard.do")
  public String deleteBoard(@RequestParam("boardIdx") int boardIdx) throws Exception {
//    Service를 사용하여 데이터 베이스의 내용 삭제
    boardService.deleteBoard(boardIdx);

    return "redirect:/board/boardList.do";
  }
}







