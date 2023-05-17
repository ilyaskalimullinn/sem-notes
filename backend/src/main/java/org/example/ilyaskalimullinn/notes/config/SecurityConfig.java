package org.example.ilyaskalimullinn.notes.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;

import javax.annotation.Resource;
import java.util.List;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@PropertySource("classpath:/application.properties")
public class SecurityConfig {
    @Resource
    private Environment environment;

    @Bean
    protected SecurityFilterChain configure(HttpSecurity http) throws Exception {
        return http
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                    .cors()
                    .configurationSource(request -> {
                        CorsConfiguration cors = new CorsConfiguration();
                        cors.setAllowedOrigins(List.of(this.getAllowedOrigins()));
                        cors.setAllowedMethods(List.of("GET","POST", "PUT", "DELETE", "OPTIONS"));
                        cors.setAllowedHeaders(List.of("*"));
                        return cors;
                    })
                .and()
                    .authorizeRequests()
                    .anyRequest().permitAll()
                .and()
                    .formLogin().disable()
                    .httpBasic()
                .and()
                    .csrf().disable()
                .build();
    }

    private String[] getAllowedOrigins() {
        return environment.getProperty("api.cors.allowed-origins").split(",");
    }
}
