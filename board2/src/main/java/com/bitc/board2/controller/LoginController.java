package com.bitc.board2.controller;

import com.bitc.board2.dto.UserDTO;
import com.bitc.board2.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.tomcat.util.http.parser.HttpParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.URLEncoder;

@Controller
@RequestMapping("/board2/login")
public class LoginController {

  @Autowired
  private UserService userService;

//  로그인 form 화면
  @RequestMapping("/login.do")
  public String login() throws Exception {
    return "login/login";
  }

//  로그인 처리
//  클라이언트에서 파라미터로 userId, userPw를 받아옴
//  HttpServletRequest 클래스의 객체를 매개변수로 받아옴(현재 세션 정보를 request 객체가 가지고 있음)
  @RequestMapping(value = "/loginProcess.do", method = RequestMethod.POST)
  public String loginProcess(@RequestParam("userId") String userId, @RequestParam("userPw") String userPw, HttpServletRequest req) throws Exception {
//    1. 클라이언트에 전달된 데이터 가져오기
//    2. 서비스를 이용하여 DB에 해당 데이터(사용자 ID)가 있는지 확인
//    3. 데이터 존재 시 세션 생성
//    4. 세션에 데이터 추가
//    5. 로그인 성공 페이지로 리다이렉트 (데이터가 없으면 로그인 실패 페이지로 리다이렉트)

//    클라이언트에서 전달 받은 사용자 id/pw가 데이터 베이스 존재하는지 service를 통해서 확인
    int result = userService.isUserInfo(userId, userPw);

//    데이터가 존재 시 로그인 처리(세션에 데이터 저장)
    if (result == 1) {
//      데이터가 존재 시 사용자ID로 service를 통해서 사용자 정보를 조회
      UserDTO user = userService.getUserInfo(userId);

//      세션 객체 생성, 현재 세션 정보는 HttpServletRequest 클래스 타입의 객체 req가 가지고 있음
//      현재 세션 정보를 getSession() 메소드를 통해서 가져옴
      HttpSession session = req.getSession();
//      setAttribute()메소드를 통해서 세션에 데이터 저장
      session.setAttribute("userId", user.getUserId());
      session.setAttribute("userName", user.getUserName());
      session.setAttribute("userEmail", user.getUserEmail());
      session.setMaxInactiveInterval(60 * 60 * 1); // 세션 유지 시간 설정

//      로그인 후 화면 이동
      return "redirect:/board2/boardList.do";
    }
//    데이터가 없을 경우 로그인 페이지로 다시 이동
    else {
//      get 방식으로 페이지 이동 및 파라미터값을 전달 시 영문자만 사용할 경우에는 문제가 없음
//      특수 문자 및 한글(영문자가 아닌 다른 언어) 사용 시 정상적으로 전달되지 않음
//      특수 문자 및 한글을 정상적으로 전달하기 위해서는 URLEncoder 클래스를 사용하여 문자를 변환시켜야 함
//      URLEncoder.encode(변환할 문자, "문자셋") : 특수 문자 및 한글을 웹 브라우저가 인식할 수 있는 문자로 변환시켜주는 클래스
//      URLDecoder.decode(변환할 문자, "문자셋") : 변환된 문자를 원본 문자로 재 변환하는 클래스
//      return "redirect:/board2/login/login.do?errorMsg=오류발생";
//      String msg = URLEncoder.encode("문자", "UTF-8");
      return "redirect:/board2/login/login.do?errorMsg=" + URLEncoder.encode("로그인 정보가 다릅니다.", "UTF-8");
    }
  }

  @RequestMapping("/logout.do")
  public String logout(HttpServletRequest req) throws Exception {
    HttpSession session = req.getSession();

    session.removeAttribute("userId");
    session.removeAttribute("userName");
    session.removeAttribute("userEmail");

    session.invalidate();

    return "redirect:/board2/boardList.do";
  }
}







