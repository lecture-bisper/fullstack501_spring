package com.bitc.board1.controller;

import com.bitc.board1.dto.BoardDto;
import com.bitc.board1.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
}







