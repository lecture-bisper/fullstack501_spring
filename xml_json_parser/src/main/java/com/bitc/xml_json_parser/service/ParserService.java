package com.bitc.xml_json_parser.service;

import com.bitc.xml_json_parser.dto.DailyBoxOfficeListDTO;
import com.bitc.xml_json_parser.dto.PharmacyFullDataItemDTO;

import java.util.List;

public interface ParserService {
  List<PharmacyFullDataItemDTO> getItemListFile(String fileName) throws Exception;

  List<PharmacyFullDataItemDTO> getItemListUrl(String serviceUrl) throws Exception;

  List<DailyBoxOfficeListDTO> getDailyBoxOfficeJson(String serviceUrl) throws Exception;
}













