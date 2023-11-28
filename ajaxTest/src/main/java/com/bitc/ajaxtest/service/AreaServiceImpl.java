package com.bitc.ajaxtest.service;

import com.bitc.ajaxtest.dto.AreaDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AreaServiceImpl implements AreaService {
  @Override
  public List<AreaDTO> getAreaList(String areaName) throws Exception {
    List<AreaDTO> areaList = new ArrayList<>();

    if (areaName.equals("서울")) {
      AreaDTO se1 = new AreaDTO();
      AreaDTO se2 = new AreaDTO();
      AreaDTO se3 = new AreaDTO();
      AreaDTO se4 = new AreaDTO();

      se1.setAreaName("강북구");
      se2.setAreaName("강남구");
      se3.setAreaName("강서구");
      se4.setAreaName("강동구");

      areaList.add(se1);
      areaList.add(se2);
      areaList.add(se3);
      areaList.add(se4);
    }
    else if (areaName.equals("대전")) {
      AreaDTO dj1 = new AreaDTO();
      AreaDTO dj2 = new AreaDTO();
      AreaDTO dj3 = new AreaDTO();
      AreaDTO dj4 = new AreaDTO();

      dj1.setAreaName("동구");
      dj2.setAreaName("중구");
      dj3.setAreaName("서구");
      dj4.setAreaName("유성구");

      areaList.add(dj1);
      areaList.add(dj2);
      areaList.add(dj3);
      areaList.add(dj4);
    }
    else if (areaName.equals("대구")) {
      AreaDTO dg1 = new AreaDTO();
      AreaDTO dg2 = new AreaDTO();
      AreaDTO dg3 = new AreaDTO();
      AreaDTO dg4 = new AreaDTO();

      dg1.setAreaName("북구");
      dg2.setAreaName("남구");
      dg3.setAreaName("동구");
      dg4.setAreaName("서구");

      areaList.add(dg1);
      areaList.add(dg2);
      areaList.add(dg3);
      areaList.add(dg4);
    }
    else if (areaName.equals("부산")) {
      AreaDTO bs1 = new AreaDTO();
      AreaDTO bs2 = new AreaDTO();
      AreaDTO bs3 = new AreaDTO();
      AreaDTO bs4 = new AreaDTO();

      bs1.setAreaName("부산진구");
      bs2.setAreaName("동래구");
      bs3.setAreaName("해운대구");
      bs4.setAreaName("수영구");

      areaList.add(bs1);
      areaList.add(bs2);
      areaList.add(bs3);
      areaList.add(bs4);
    }

    return areaList;
  }
}







