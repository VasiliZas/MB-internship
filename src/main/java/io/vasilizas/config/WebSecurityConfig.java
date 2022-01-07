package io.vasilizas.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .anyRequest().authenticated()
                .and().logout().clearAuthentication(true).invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .and().oauth2Login().defaultSuccessUrl("/loginSuccess")
                .and().csrf().disable();
    }
}
