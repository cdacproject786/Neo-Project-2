package com.gateway.config;

import com.gateway.jwt.Jwtutil;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.context.ServerSecurityContextRepository;
import org.springframework.web.client.RestTemplate;

@Configuration

@EnableWebFluxSecurity
public class SecurityConfig {

    @Autowired
    private ServerSecurityContextRepository customeSecurityContext;
    @Autowired
    private ReactiveAuthenticationManager authenticationManager;
    @Bean
    public Jwtutil jwtutil()
    {
        return new Jwtutil();
    }
    @Bean
    public PasswordEncoder passwordEncoder()
    {
       return new BCryptPasswordEncoder();
    }

    @Bean
    public RestTemplate restTemplate()
    {
        return new RestTemplate();
    }


@Bean
protected SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity serverHttpSecurity) {
    serverHttpSecurity.csrf().disable();
    serverHttpSecurity.formLogin().disable();

    serverHttpSecurity.authorizeExchange()
            .pathMatchers(HttpMethod.POST, "/user/security/**").permitAll()
            //.pathMatchers(HttpMethod.GET,"/user/security/demo").permitAll()
            .pathMatchers(HttpMethod.POST,"/api/login/user").permitAll()
            .anyExchange().authenticated();

    serverHttpSecurity.securityContextRepository(this.customeSecurityContext);
    serverHttpSecurity.authenticationManager(this.authenticationManager);


    return serverHttpSecurity.build();
}
    //@Bean
    //public AuthenticationProvider authenticationProvider()
    /*{
        return new CustomeAuthenticationProvider();
    }*/

}
/*
    @Order(Ordered.HIGHEST_PRECEDENCE)
    @Bean
    SecurityWebFilterChain apiHttpSecurity(ServerHttpSecurity http) {
        http
                .securityMatcher(new PathPatternParserServerWebExchangeMatcher("/user/security/**"))
                .authorizeExchange((exchanges) -> exchanges
                        .anyExchange().authenticated()
                );
                //.oauth2ResourceServer(OAuth2ResourceServerSpec::jwt);
        return http.build();
    }

    @Bean
    SecurityWebFilterChain webHttpSecurity(ServerHttpSecurity http) {
        http
                .authorizeExchange((exchanges) -> exchanges
                        .anyExchange().authenticated()
                )
                .httpBasic(Customizer.withDefaults());
        return http.build();
    }*/

/*    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception
    {
        return config.getAuthenticationManager();
    }*/

/*    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
    {
       return http
                .cors()
                .disable()
                .authorizeHttpRequests()
                .requestMatchers("/user/security/**","/test")
                .permitAll()
                 .anyRequest()
                .authenticated()
                .and()
               .formLogin()
                .disable()
                .build();
    }*/