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

@Controller
@RequestMapping("/board2/login")
public class LoginController {

  @Autowired
  private UserService userService;

  @RequestMapping("/login.do")
  public String login() throws Exception {
    return "login/login";
  }

  @RequestMapping(value = "/loginProcess.do", method = RequestMethod.POST)
  public String loginProcess(@RequestParam("userId") String userId, @RequestParam("userPw") String userPw, HttpServletRequest req) throws Exception {
//    1. 클라이언트에 전달된 데이터 가져오기
//    2. 서비스를 이용하여 DB에 해당 데이터(사용자 ID)가 있는지 확인
//    3. 데이터 존재 시 세션 생성
//    4. 세션에 데이터 추가
//    5. 로그인 성공 페이지로 리다이렉트 (데이터가 없으면 로그인 실패 페이지로 리다이렉트)

    int result = userService.isUserInfo(userId, userPw);

    if (result == 1) {
      UserDTO user = userService.getUserInfo(userId);

      HttpSession session = req.getSession();
      session.setAttribute("userId", user.getUserId());
      session.setAttribute("userName", user.getUserName());
      session.setAttribute("userEmail", user.getUserEmail());
      session.setMaxInactiveInterval(60 * 60 * 1);

      return "redirect:/board2/login/loginOk.do";
    }
    else {
      return "redirect:/board2/login/loginFail.do";
    }
  }
}







