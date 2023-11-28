package com.bitc.thymeleaf.controller;

import com.bitc.thymeleaf.dto.MemberDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/thymeleaf")
public class ThymeleafController {

  @RequestMapping("/")
  public String index() throws Exception {
    return "thymeleaf/index";
  }

  @RequestMapping("/test1")
  public ModelAndView test1() throws Exception {
    ModelAndView mv = new ModelAndView("thymeleaf/test1");

    mv.addObject("value1", "서버에서 전달한 문자열");
    mv.addObject("value2", 1000);
    mv.addObject("value3", 2000);
    mv.addObject("utext", "문자열과 <strong>html 태그</strong>가 혼합된 문자열");
    mv.addObject("idx", 10);

    return mv;
  }

  @RequestMapping("/test2")
  public ModelAndView test2() throws Exception {
    ModelAndView mv = new ModelAndView("thymeleaf/test2");

    MemberDTO member = new MemberDTO();
    member.setMemberIdx(1);
    member.setMemberName("아이유");
    member.setMemberId("iu");
    member.setMemberAge(30);
    member.setMemberEmail("iu@bitc.ac.kr");

    mv.addObject("member", member);

    mv.addObject("str1", "첫번째");
    mv.addObject("str2", "두번째");
    mv.addObject("str3", null);

    mv.addObject("num1", 100);
    mv.addObject("num2", 3);
    mv.addObject("num3", 11);

    mv.addObject("grade", "B");

    String[] strArray = {"첫번째", "두번째", "세번째", "네번째", "다섯번째"};
    List<String> strList = new ArrayList<>();
    strList.add("첫번째");
    strList.add("두번째");
    strList.add("세번째");
    strList.add("네번째");
    strList.add("다섯번째");

    mv.addObject("itemArray", strArray);
    mv.addObject("itemList", strList);

    MemberDTO member1 = new MemberDTO();
    member1.setMemberIdx(1);
    member1.setMemberId("test1");
    member1.setMemberName("테스터1");
    member1.setMemberAge(20);
    member1.setMemberEmail("tester1@bitc.ac.kr");
    MemberDTO member2 = new MemberDTO();
    member2.setMemberIdx(2);
    member2.setMemberId("test2");
    member2.setMemberName("테스터2");
    member2.setMemberAge(20);
    member2.setMemberEmail("tester2@bitc.ac.kr");
    MemberDTO member3 = new MemberDTO();
    member3.setMemberIdx(3);
    member3.setMemberId("test3");
    member3.setMemberName("테스터3");
    member3.setMemberAge(30);
    member3.setMemberEmail("tester3@bitc.ac.kr");

    List<MemberDTO> memberList = new ArrayList<>();
    memberList.add(member1);
    memberList.add(member2);
    memberList.add(member3);

    mv.addObject("memberList", memberList);


    return mv;
  }

  @RequestMapping("/test3")
  public ModelAndView test3() throws Exception {
    ModelAndView mv = new ModelAndView("thymeleaf/test3");

    mv.addObject("chkVal", true);
    mv.addObject("mulVal", true);
    mv.addObject("selVal", true);
    mv.addObject("disVal", false);
    mv.addObject("readVal", false);

    return mv;
  }

  @RequestMapping("/test4")
  public String test4() throws Exception {
    return "thymeleaf/test4";
  }
}







