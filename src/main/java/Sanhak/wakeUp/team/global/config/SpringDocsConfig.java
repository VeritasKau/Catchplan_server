package Sanhak.wakeUp.team.global.config;


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
                .title("Catchplan API 문서")
                .version("v1")
                .description("Catchplan API")
                .contact(new Contact().name("JSJ"));

        SecurityScheme bearerAuth = new SecurityScheme()
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT")
                .in(SecurityScheme.In.HEADER)
                .name(HttpHeaders.AUTHORIZATION);

        SecurityRequirement securityItem = new SecurityRequirement()
                .addList("Authorization");

        return new OpenAPI()
                .components(new Components().addSecuritySchemes("Authorization", bearerAuth))
                .addSecurityItem(securityItem)
                .info(info);
    }
}
