package com.bitc.xml_json_parser.service;

import com.bitc.xml_json_parser.dto.*;
import com.bitc.xml_json_parser.dto.tago.TagoDTO;
import com.bitc.xml_json_parser.dto.tago.TagoItemDTO;
import com.google.gson.Gson;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

@Service
public class ParserServiceImpl implements ParserService {
  @Override
  public List<PharmacyFullDataItemDTO> getItemListFile(String fileName) throws Exception {
//    jaxb : 자바에서 xml 데이터 파싱을 도와주는 라이브러리
//    jaxb가 자바 9부터 자바 기본 라이브러리에서 제외됨
//    Marshal(마샬) : 자바 클래스를 XML 데이터로 변환
//    UnMarshal(언마샬) : XML 데이터를 자바 클래스 타입의 객체로 변환
    
//    JAXB 라이브러리 사용 선언
//    PharmacyFullDataResponseDTO 클래스 타입으로 xml 데이터를 파싱하도록 설정
    JAXBContext jc = JAXBContext.newInstance(PharmacyFullDataResponseDTO.class);
//    JAXB 라이브러리를 사용하여 XML 데이터를 자바 클래스 타입의 객체로 변환하는 언마샬 생성
    Unmarshaller um = jc.createUnmarshaller();

//    기존에 제공된 xml 테이터를 기반으로 PharmacyFullDataResponseDTO 클래스의 객체를 생성하므로 xml 데이터를 파싱하여 가져온 데이터를 PharmacyFullDataResponseDTO 클래스 타입의 객체에 타입 변환하여 저장함
//    unmarshal() : 언마샬을 실행하는 메소드, 매개변수로 파일 및 URL을 받음
    PharmacyFullDataResponseDTO fullData = (PharmacyFullDataResponseDTO) um.unmarshal(new File(fileName));
//    PharmacyFullDataHeaderDTO header = fullData.getHeader();
//    PharmacyFullDataBodyDTO body = fullData.getBody();
//    PharmacyFullDataItemsDTO items = body.getItems();
//    List<PharmacyFullDataItemDTO> itemList = items.getItemList();
//
//    return itemList;

    return fullData.getBody().getItems().getItemList();
  }

  @Override
  public List<PharmacyFullDataItemDTO> getItemListUrl(String serviceUrl) throws Exception {
    List<PharmacyFullDataItemDTO> itemList = null;
    URL url = null;
    HttpURLConnection urlConn = null;

    try {
      url = new URL(serviceUrl);
      urlConn = (HttpURLConnection) url.openConnection();
      urlConn.setRequestMethod("GET");

      JAXBContext jc = JAXBContext.newInstance(PharmacyFullDataResponseDTO.class);
      Unmarshaller um = jc.createUnmarshaller();

      PharmacyFullDataResponseDTO fullData = (PharmacyFullDataResponseDTO) um.unmarshal(url);
      itemList = fullData.getBody().getItems().getItemList();
    }
    catch (JAXBException e) {
      e.printStackTrace();
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    finally {
      if (urlConn != null) { urlConn.disconnect(); }
    }
    return itemList;
  }

//  json 방식으로 데이터 파싱하기
  @Override
  public List<DailyBoxOfficeListDTO> getDailyBoxOfficeJson(String serviceUrl) throws Exception {
//    json 데이터를 파싱하여 저장할 List
    List<DailyBoxOfficeListDTO> itemList = null;

    URL url = null;
    HttpURLConnection urlConn = null;
//    파일 혹은 네트워크 통신 시 가져온 데이터를 임시 버퍼에 저장하면서 데이터를 읽기 위한 클래스
    BufferedReader reader = null;

    try {
//    json 데이터를 제공하는 서버 URL을 지정
      url = new URL(serviceUrl);
//      지정한 서버로 접속
      urlConn = (HttpURLConnection) url.openConnection();
//      접속 방식 설정
      urlConn.setRequestMethod("GET");

//      ** JAXB 라이브러리는 클래스 내부에 네트워크 데이터 처리 부분이 이미 존재함
//      json 파싱을 위한 라이브러리는 네트워크 데이터 처리 부분이 없기 때문에 InputStreamReader를 통해서 네트워크에서 데이터를 가져오는 부분과 가져온 데이터 처리 부분이 필요함

//      지정한 서버에서 json 데이터를 가져옴, 가져온 데이터를 BufferedReader에 데이터를 저장
      reader = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
      
//      네트워크를 통해서 전달된 데이터가 너무 길기 때문에 하나의 문자열로 합쳐줄 클래스
      StringBuilder sb = new StringBuilder();
      String line; // json 데이터가 저장될 변수

//      readLine() : 스트림에 저장된 데이터를 한 라인씩 읽어오는 메소드
//      스트림에 더이상 데이터가 없을때까지 연속해서 읽어옴
      while ((line = reader.readLine()) != null) {
//        StringBuilder의 객체의 가장 뒤쪽에 계속 추가함
        sb.append(line);
      }

//      Gson 라이브러리를 사용하여 json을 파싱하기 위한 객체 생성
      Gson gson = new Gson();

//      Json문자열을 자바 클래스 타입으로 변환
//      첫번째 매개변수는 json문자열, 두번째 매개변수는 변환하고자하는 자바 클래스
      BoxOfficeDTO boxOffice = gson.fromJson(sb.toString(), BoxOfficeDTO.class);
      itemList = boxOffice.getBoxOfficeResult().getDailyBoxOfficeList();
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    finally {
      if (reader != null) { reader.close(); }
      if (urlConn != null) { urlConn.disconnect(); }
    }

    return itemList;
  }

  @Override
  public List<TagoItemDTO> getCityCodeList(String serviceUrl) throws Exception {
    List<TagoItemDTO> cityList = null;

    URL url = null;
    HttpURLConnection urlConn = null;
    BufferedReader reader = null;

    try {
      url = new URL(serviceUrl);
      urlConn = (HttpURLConnection) url.openConnection();
      urlConn.setRequestMethod("GET");

      reader = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));

      StringBuilder sb = new StringBuilder();
      String line;

      while ((line = reader.readLine()) != null) {
        sb.append(line);
      }

      Gson gson = new Gson();
      TagoDTO tago = gson.fromJson(sb.toString(), TagoDTO.class);
      cityList = tago.getResponse().getBody().getItems().getItem();
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    finally {
      if (reader != null) { reader.close(); }
      if (urlConn != null) { urlConn.disconnect(); }
    }

    return cityList;
  }
}







