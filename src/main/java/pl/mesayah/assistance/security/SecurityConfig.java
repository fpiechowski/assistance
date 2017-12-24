package pl.mesayah.assistance.security;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Configuration for Spring Security.
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // Temporary disable all authentication.
        http.csrf().disable();
        http
                .authorizeRequests()
                .antMatchers("/test").permitAll()
                .and()
                .formLogin()
                .loginPage("/login").failureUrl("/login-error");
    }
}
