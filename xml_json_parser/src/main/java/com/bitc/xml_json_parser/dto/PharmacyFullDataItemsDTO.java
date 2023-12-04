package com.bitc.xml_json_parser.dto;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.List;

@XmlRootElement(name = "items")
public class PharmacyFullDataItemsDTO {
  private List<PharmacyFullDataItemDTO> itemList;

  @XmlElement(name = "item")
  public List<PharmacyFullDataItemDTO> getItemList() {
    return itemList;
  }

  public void setItemList(List<PharmacyFullDataItemDTO> itemList) {
    this.itemList = itemList;
  }
}







