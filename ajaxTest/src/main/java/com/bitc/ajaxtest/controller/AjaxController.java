package com.bitc.ajaxtest.controller;

import com.bitc.ajaxtest.dto.AreaDTO;
import com.bitc.ajaxtest.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/ajax")
public class AjaxController {

  @Autowired
  private AreaService areaService;

//  ajax 미사용 계산기 페이지
  @RequestMapping("/nonAjaxCalView")
  public String nonAjaxCalView() throws Exception {
    return "ajax/nonAjaxCalView";
  }

//  ajax 미사용 계산기 결과 페이지
  @RequestMapping("/nonAjaxCalResult")
  public ModelAndView nonAjaxCalResult(@RequestParam("num1") int num1, @RequestParam int num2) throws Exception {
    ModelAndView mv = new ModelAndView("ajax/nonAjaxCalResult");

    int result = num1 + num2;
    mv.addObject("num1", num1);
    mv.addObject("num2", num2);
    mv.addObject("result", result);

    return mv;
  }

//  ajax 사용 계산기 페이지
  @RequestMapping("/ajaxCalView")
  public String ajaxCalView() throws Exception {
    return "ajax/ajaxCalView";
  }

//  ajax 사용 계산기 결과 프로세스
//  @RequestParam : jsp의 request.getParameter() 메소드와 동일한 역할을 하는 어노테이션
//  매개변수로 여러가지 옵션을 추가하여 사용할 수 있음
//  value : 클라이언트에서 전달한 데이터의 파라미터 명을 지정
//  required : true(기본값)/false로 설정, 해당 파라미터 값이 없을 경우 오류를 표시(false 시 오류 미표시)
//  defaultValue : required 속성과 함께 사용하여 사용자가 해당 파라미터에 값을 입력하지 않을 경우 defaultValue로 설정된 값을 사용
  
//  @RequestMapping : 해당 메소드와 사용자가 접속할 URL을 매칭하는 어노테이션
//  value : 사용자가 접속할 URL을 설정
//  method : 사용자가 서버로 데이터 전달 시 사용할 통신 방식 설정(GET, POST), Restful 방식 사용 시 GET, POST, PUT, DELETE를 사용
  
//  @ResponseBody : 응답으로 리턴할 데이터를 html 템플릿이 아닌 데이터 자체를 출력하도록 하는 어노테이션
  @ResponseBody
  @RequestMapping(value = "/ajaxCalResult", method = RequestMethod.POST)
  public Object ajaxCalResult(@RequestParam("num1") int num1, @RequestParam("num2") int num2) throws Exception {
    int result = num1 + num2;

    Map<String, String> mapResult = new HashMap<>();
    mapResult.put("result", "success");
    mapResult.put("value", String.valueOf(result));

    return mapResult;
  }

  @RequestMapping(value = "/selectBox", method = RequestMethod.GET)
  public String selectBox() throws Exception {
    return "ajax/selectBox";
  }

  @ResponseBody
  @RequestMapping(value = "/selectBox", method = RequestMethod.POST)
  public Object selectBox(@RequestParam("areaName") String areaName) throws Exception {
    List<AreaDTO> areaList = areaService.getAreaList(areaName);

    return areaList;
  }
}







