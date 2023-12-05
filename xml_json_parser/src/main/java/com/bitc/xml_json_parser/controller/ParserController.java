package com.bitc.xml_json_parser.controller;

import com.bitc.xml_json_parser.dto.DailyBoxOfficeListDTO;
import com.bitc.xml_json_parser.dto.PharmacyFullDataItemDTO;
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

    List<DailyBoxOfficeListDTO> dailyBoxOfficeList = parserService.getDailyBoxOfficeJson(serviceUrl);

    return dailyBoxOfficeList;
  }
}







