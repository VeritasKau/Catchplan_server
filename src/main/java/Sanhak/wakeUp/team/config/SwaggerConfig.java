package Sanhak.wakeUp.team.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(title = "sanhak API 명세서",
                description = "sanhak",
                version = "v2")
)
@RequiredArgsConstructor
@Configuration
public class SwaggerConfig {
    @Bean
    public GroupedOpenApi Home() {
        String[] paths = {"/user/**"};

        return GroupedOpenApi.builder()
                .group("회원관리 page")
                .pathsToMatch(paths)
                .build();
    }

}
