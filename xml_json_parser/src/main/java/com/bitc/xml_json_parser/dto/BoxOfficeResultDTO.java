package com.bitc.xml_json_parser.dto;

import lombok.Data;

import java.util.List;

@Data
public class BoxOfficeResultDTO {
  private String boxofficeType;
  private String showRange;
  private List<DailyBoxOfficeListDTO> dailyBoxOfficeList;
}







