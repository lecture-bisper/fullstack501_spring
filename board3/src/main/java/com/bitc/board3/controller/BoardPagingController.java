package com.bitc.board3.controller;

import com.bitc.board3.dto.BoardDTO;
import com.bitc.board3.service.BoardPagingService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/board3")
public class BoardPagingController {
  
//  PageInfo : PageHelper 라이브러리에서 제공하는 클래스
//  아래와 같이 페이징에 관련된 정보를 제공함
//  list : PageInfo에 저장된 게시물 List
//  pageNum : 현재 페이지 번호
//  pageSize : 한 페이지당 출력할 게시물 수
//  size : 현재 페이지에 출력된 게시물 수
//  startRow : 현재 페이지의 첫번째 요소가 데이터 베이스에 있는 줄 번호
//  endRow : 현재 페이지의 마지막 요소가 데이터 베이스에 있는 줄 번호
//  pages : 전체 페이지 수
//  prePage : 이전 페이지
//  nextPage : 다음 페이지
//  isFirstPage : 현재 페이지가 첫 페이지인지 여부 확인, true/false
//  isLastPage : 현재 페이지가 마지막 페이지인지 여부 확인, true/false
//  hasPreviousPage : 이전 페이지가 존재하는지 여부 확인, true/false
//  hasNextPage : 다음 페이지가 존재하는지 여부 확인, true/false
//  navigatePages : 네비게이션 블록의 크기
//  navigatepageNums : 전체 네비게이션 페이지 번호, 배열로 구성
//  navigateFirstPage : 네비게이션 블록의 첫 페이지 번호
//  navigateLastPage : 네비게이션 블록의 마지막 페이지 번호

  @Autowired
  private BoardPagingService boardPagingService;

//  @RequestParam 사용 중 required=false 속성 사용 시 지정한 값이 입력되지 않아도 오류를 발생 시키지 않음
//  defaultValue : 지정한 파라미터 값이 없을 경우 default로 지정된 값이 사용 됨
  @GetMapping("/page/board")
  public ModelAndView selectBoardPagingList(@RequestParam(required = false, defaultValue = "1", value = "pageNum") int pageNum) throws Exception {
    ModelAndView mv = new ModelAndView("page/boardPageList");

//    PageInfo : PageHelper에서 제공하는 데이터 타입, HashMap과 비슷한 데이터 타입
//    첫번째 매개변수 : 서비스를 사용하여 DB에서 데이터를 가져오는 메소드를 입력
//    두번째 매개변수 : 한 페이지에서 출력한 페이지 이동 버튼 수 설정
    PageInfo<BoardDTO> boardPageList = new PageInfo<>(boardPagingService.selectBoardPageList(pageNum), 5);
    mv.addObject("boardPageList", boardPageList);

    return mv;
  }
}







