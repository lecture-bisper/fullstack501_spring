package com.bitc.ajaxtest.service;

import com.bitc.ajaxtest.dto.AreaDTO;

import java.util.List;

public interface AreaService {
  public List<AreaDTO> getAreaList(String areaName) throws Exception;

  List<AreaDTO> getDistrictList(String areaName) throws Exception;

  List<AreaDTO> getTownList(String areaName) throws Exception;
}













