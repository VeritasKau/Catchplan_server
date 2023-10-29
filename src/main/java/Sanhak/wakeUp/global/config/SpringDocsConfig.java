package Sanhak.wakeUp.global.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;

@Configuration
public class SpringDocsConfig {

    @Bean
    public OpenAPI openAPI() {
        Info info = new Info()
                .title("Catchplan API 문서") // 타이틀
                .version("v1") // 문서 버전
                .description("Catchplan API") // 문서 설명
                .contact(new Contact() // 연락처
                        .name("JSJ"));

        // Security 스키마 설정
        SecurityScheme bearerAuth = new SecurityScheme()
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT")
                .in(SecurityScheme.In.HEADER)
                .name(HttpHeaders.AUTHORIZATION);

        SecurityRequirement securityItem = new SecurityRequirement();
        securityItem.addList("Authorization");

        return new OpenAPI()
                // Security 인증 컴포넌트 설정
                .components(new Components().addSecuritySchemes("Authorization", bearerAuth))
                // API 마다 Security 인증 컴포넌트 설정
                .addSecurityItem(securityItem)
                .info(info);
    }
}

