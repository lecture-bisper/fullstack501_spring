package com.bitc.board1.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

  @Value("${resource.images.location}")
  private String path1;

  @Value("${resource.movies.location}")
  private String path2;

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler("/img/**")
        .addResourceLocations("file:///C:/fullstack501/images/");

//    addResourceHandler(주소) : 프로젝트 안에서 사용할 리소스의 주소 설정
//    addResourceLocations(경로) : 서버의 물리적 경로를 설정, file:// + 경로 로 설정
//    addResourceLocations() 실제 위치 설정 시 윈도우 시스템과 리눅스 혹은 맥 환경의 경로 표시가 다름
//    리눅스, 맥 : file://fullstack501/images/
//    윈도우 : file:///C:/fullstack501/images/

//    하나의 리소스 경로에 2개 이상의 외부 경로를 등록하고자 할 경우
//    registry.addResourceHandler("/imgs/**").addResourceLocations("file:///C:/fullstack501/img1/", "file:///C:/fullstack501/img2/");

//    2개 이상의 리소스 경로를 사용하고자 할 경우 (리소스 경로를 다르게 하여 2번 이상 등록)
//    registry.addResourceHandler("/img/**").addResourceLocations(file:///C:/fullstack501/image/");
//    registry.addResourceHandler("/movie/**").addResourceLocations("file:///C:/fullstack501/movies/");
    
//    application.properties 파일에 등록할 경로 정보를 입력하고 @Value 어노테이션을 사용하여 설정 파일에서 경로값을 가져와서 등록해도 상관없음

//    registry.addResourceHandler("/etc/**")
//        .addResourceLocations("file:///" + path1, "file:///" + path2);
  }
}







