package com.acelera.java.hotel.Config;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(HttpMethod.POST, "/usuario").permitAll()
                .antMatchers(HttpMethod.GET, "/usuario").authenticated()
                 .antMatchers(HttpMethod.GET, "/limpos").permitAll();

       // http://localhost:8080/oauth/token?grant_type=password&username=admin&password=admin
    }
}
