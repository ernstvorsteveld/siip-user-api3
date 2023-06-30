package group.siip.userapi.user.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.reactive.config.EnableWebFlux;

@Configuration
@EnableWebFlux
public class WebConfiguration {

    @Bean
    public Customizer<CsrfConfigurer<HttpSecurity>> disableCsrf() {
        return (csrf) -> csrf.disable();
    }

    @Bean
    public WebSecurityCustomizer ignoreActuators() {
        return (web) -> web.ignoring().antMatchers("/actuator/**");
    }

    @Bean
    @Order(1)
    public SecurityFilterChain apiHttpSecurityFilterChain(
            HttpSecurity http,
            Customizer<CsrfConfigurer<HttpSecurity>> disableCsrf) throws Exception {
        http
                .authorizeRequests(r -> ignoreActuators())
                .antMatcher("/users/**")
                .authorizeRequests(auth -> {
                    auth.anyRequest().authenticated();
                })
                .csrf(disableCsrf)
                .oauth2ResourceServer()
                .jwt();

        return http.build();
    }
}
