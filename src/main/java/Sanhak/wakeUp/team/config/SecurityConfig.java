//package Sanhak.wakeUp.team.config;
//
//
//import Sanhak.wakeUp.team.service.CustomOauth2UserService;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
//    private final CustomOauth2UserService customOauth2UserService;
//
//    @Bean
//    public BCryptPasswordEncoder encoder(){
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
//        http
//                .csrf().disable()
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                .formLogin().disable()
//                .httpBasic().disable()
//                .authorizeRequests()
//                .andMatchers("/api/user").permitAll()
//                .and()
//                .oauth2Login().userInfoEndpoint().userService(customOauth2UserService);
//        return  http.build();
//    }
//
//}
