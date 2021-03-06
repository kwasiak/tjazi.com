package com.tjazi.webapp.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;

/**
 * Created by kwasiak on 18/07/15.
 */
@Configuration
@EnableWebMvcSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomAuthenticationProvider customAuthenticationProvider;

    private static final Logger log = LoggerFactory.getLogger(SecurityConfig.class);

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

        log.debug("Setting global configuration for SecurityConfig");

        auth.authenticationProvider(customAuthenticationProvider);
    }

    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception {

        httpSecurity
                .authorizeRequests()
                .antMatchers("/**/*.js", "/**/*.css", "/", "/templates/**", "/chatroom/**", "/security/isuserauthenticated/**")
                .permitAll()
                .and()

                /* TODO: enable csrf for production */
                .csrf().disable();

        httpSecurity
                .httpBasic().and()
                .authorizeRequests().antMatchers("/security/**", "/templatessecure/**").authenticated()

                // this by default set's '/logout' address, which is just enough to call
                // to logout from this session
                .and().logout();
    }
}
