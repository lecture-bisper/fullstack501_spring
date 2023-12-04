package com.bitc.xml_json_parser.dto;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

// @XmlRootElement : xml 데이터에서 부모가 되는 태그를 뜻하는 어노테이션
// @XmlElement : xml 데이터에서 실제 데이터가 들어있는 태그를 뜻하는 어노테이션
// @XmlAttribute : xml 데이터에서 지정한 태그의 속성을 뜻하는 어노테이션
@XmlRootElement(name = "response")
public class PharmacyFullDataResponseDTO {
  private PharmacyFullDataHeaderDTO header;
  private PharmacyFullDataBodyDTO body;

  @XmlElement(name = "header")
  public PharmacyFullDataHeaderDTO getHeader() {
    return header;
  }

  public void setHeader(PharmacyFullDataHeaderDTO header) {
    this.header = header;
  }

  @XmlElement(name = "body")
  public PharmacyFullDataBodyDTO getBody() {
    return body;
  }

  public void setBody(PharmacyFullDataBodyDTO body) {
    this.body = body;
  }
}







