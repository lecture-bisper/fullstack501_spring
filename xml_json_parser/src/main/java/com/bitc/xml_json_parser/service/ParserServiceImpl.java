package com.bitc.xml_json_parser.service;

import com.bitc.xml_json_parser.dto.*;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import org.springframework.stereotype.Service;

import java.io.File;
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
}







