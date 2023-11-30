package com.bitc.board2.configuration;

import com.bitc.board2.interceptor.LoginCheck;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// @Configuration : 해당 클래스가 Spring Framework의 설정 파일임을 알려줌
// WebMvcConfigurer : 스프링 프레임워크의 웹 설정 정보를 가지고 있는 인터페이스
@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

//  사용자 인터셉터를 추가
  @Override
  public void addInterceptors(InterceptorRegistry registry) {
//    addInterceptor() : 사용자가 생성한 인터셉터 클래스 객체를 등록
    registry.addInterceptor(new LoginCheck())
//        addPathPatterns() : 인터셉터를 동작시킬 주소 패턴 등록
        .addPathPatterns("/board2/*")
//        excludePathPatterns() : 인터셉터에서 제외할 주소 패턴 등록
        .excludePathPatterns("/board2/boardList.do")
        .excludePathPatterns("/board2/login/login.do")
        .excludePathPatterns("/board2/login/logout.do");
  }
}







