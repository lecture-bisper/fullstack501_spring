package com.bitc.xml_json_parser.controller;

import com.bitc.xml_json_parser.dto.DailyBoxOfficeListDTO;
import com.bitc.xml_json_parser.dto.PharmacyFullDataItemDTO;
import com.bitc.xml_json_parser.dto.tago.TagoItemDTO;
import com.bitc.xml_json_parser.service.ParserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/parser")
public class ParserController {

  @Autowired
  private ParserService parserService;

  @Value("${fullstack501.pharmacy.service.key}")
  private String serviceMyKey;

  @Value("${fullstack501.pharmacy.service.url}")
  private String pharmacyServiceUrl;

  @Value("${fullstack501.kobis.service.url}")
  private String kobisServiceUrl;

  @Value("${fullstack501.kobis.service.key}")
  private String kobisServiceMyKey;

  @RequestMapping({"/", ""})
  public String index() throws Exception {
    return "index";
  }

  @RequestMapping("/pharmacy/fullDataFile")
  public ModelAndView getFullDataFile() throws Exception {
    ModelAndView mv = new ModelAndView("pharmacy/fullDataFile");

    List<PharmacyFullDataItemDTO> pharmacyList = parserService.getItemListFile("C:\\fullstack501\\pharmacy.xml");
    mv.addObject("pharmacyList", pharmacyList);

    return mv;
  }

  @ResponseBody
  @PostMapping("/pharmacy/fullDataUrl")
  public Object getFullDataUrl(@RequestParam("pageNo") int pageNo, @RequestParam("numOfRows") int numOfRows) throws Exception {
//    https://apis.data.go.kr/B552657/ErmctInsttInfoInqireService/getParmacyFullDown?serviceKey=MQ8moCpDCqz42c3kxwz2LHfm%2BevXvXYOTYnrZjpLTYgYqArm4QD7hxWxCGM%2F24BBmgODLVMFUM6mAAH4XCFQcg%3D%3D&pageNo=1&numOfRows=10
//    String pharmacyServiceUrl = "http://apis.data.go.kr/B552657/ErmctInsttInfoInqireService/getParmacyFullDown";
    String optKey = "?serviceKey=";
//    String myKey = "MQ8moCpDCqz42c3kxwz2LHfm%2BevXvXYOTYnrZjpLTYgYqArm4QD7hxWxCGM%2F24BBmgODLVMFUM6mAAH4XCFQcg%3D%3D";
    String opt1 = "&pageNo=";
    String opt2 = "&numOfRows=";

    List<PharmacyFullDataItemDTO> pharmacyList = parserService.getItemListUrl(pharmacyServiceUrl + optKey + serviceMyKey + opt1 + pageNo + opt2 + numOfRows);

    return pharmacyList;
  }

  @GetMapping("/kobis/dailyBoxOffice")
  public String dailyBoxOfficeView() throws Exception {
    return "kobis/dailyBoxOffice";
  }

  @ResponseBody
  @PostMapping("/kobis/dailyBoxOfficeJson")
  public Object getDailyBoxOfficeJson(@RequestParam("targetDt") String targetDt) throws Exception {
    String optKey = "?key=";
    String opt1 = "&targetDt=";
    String serviceUrl = kobisServiceUrl + optKey + kobisServiceMyKey + opt1 + targetDt;

//    서비스를 통해서 데이터 가져오기
    List<DailyBoxOfficeListDTO> dailyBoxOfficeList = parserService.getDailyBoxOfficeJson(serviceUrl);

    return dailyBoxOfficeList;
  }

//  문제 1) 공공데이터 포털에서 "열차정보" 를 검색하여 "국토교통부_(TAGO)_열차정보"를 활용 신청하고, 제공하는 서비스 중 "도시코드 목록 조회" 서비스를 json 방식으로 파싱하여 화면에 출력하는 웹 페이지를 제작하세요
//  * json 방식의 데이터를 파싱
//  * 웹 페이지 표시 및 데이터 출력을 위한 "ParserController" 파일에 메소드 생성
//  * ajax 통신을 통하여 스프링 서버와 통신

  @GetMapping("/tago/cityCodeList")
  public String cityCodeList() throws Exception {
    return "tago/cityCodeList";
  }

  @ResponseBody
  @PostMapping("/tago/cityCodeList")
  public Object getCityCodeList(@RequestParam("type") String type) throws Exception {
//    https://apis.data.go.kr/1613000/TrainInfoService/getCtyCodeList?serviceKey=MQ8moCpDCqz42c3kxwz2LHfm%2BevXvXYOTYnrZjpLTYgYqArm4QD7hxWxCGM%2F24BBmgODLVMFUM6mAAH4XCFQcg%3D%3D&_type=json
//    http://apis.data.go.kr/1613000/TrainInfoService/getCtyCodeList?serviceKey=MQ8moCpDCqz42c3kxwz2LHfm%2BevXvXYOTYnrZjpLTYgYqArm4QD7hxWxCGM%2F24BBmgODLVMFUM6mAAH4XCFQcg%3D%3D&_type=json
    String url = "http://apis.data.go.kr/1613000/TrainInfoService/getCtyCodeList";
    String optKey = "?serviceKey=";
    String opt1 = "&_type=";
    String serviceUrl = url + optKey + serviceMyKey + opt1 + type;

    List<TagoItemDTO> cityList = parserService.getCityCodeList(serviceUrl);

    return cityList;
  }
}







