package com.sports.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * AppConfig는 애플리케이션의 전역 설정을 정의하는 클래스입니다.
 * 필요한 Bean을 등록하여 스프링 컨테이너에서 관리할 수 있도록 합니다.
 */
@Configuration
public class AppConfig {

    /**
     * RestTemplate Bean을 생성하여 애플리케이션 전역에서 HTTP 요청을 보낼 수 있도록 제공합니다.
     * RestTemplate은 RESTful API와 통신하기 위한 클라이언트 도구입니다.
     *
     * @return RestTemplate RESTful API 호출에 사용되는 RestTemplate 객체
     */
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
