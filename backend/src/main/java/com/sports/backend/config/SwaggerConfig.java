package com.sports.backend.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * SwaggerConfig는 OpenAPI(Swagger) 설정을 관리하는 클래스입니다.
 * Swagger UI를 통해 API 문서를 제공하고, API 테스트를 지원합니다.
 */
@Configuration
public class SwaggerConfig {

    /**
     * OpenAPI Bean을 생성하여 애플리케이션의 OpenAPI 문서 구성을 제공합니다.
     *
     * @return OpenAPI OpenAPI 설정 객체
     */
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .components(new Components())
                .info(apiInfo());
    }

    /**
     * API 문서의 기본 정보를 설정합니다.
     *
     * @return Info OpenAPI의 기본 정보 객체
     */
    private Info apiInfo() {
        return new Info()
                .title("Sports Management API")
                .description("스포츠 관련 데이터 관리 및 서비스를 제공하는 API 플랫폼입니다. " +
                             "전국의 체육 시설과 강좌 정보를 조회할 수 있습니다.")
                .version("1.0.0");
    }
}