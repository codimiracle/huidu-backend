package com.codimiracle.application.platform.huidu.configurer;

import com.codimiracle.application.platform.huidu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Value("${debug}")
    private boolean debug;

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    public AuthenticationProvider getAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
        daoAuthenticationProvider.setUserDetailsService(userService);
        return daoAuthenticationProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        super.configure(auth);
        auth.authenticationProvider(getAuthenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
//        http.cors().disable();
        if (debug) {
            http.authorizeRequests().anyRequest().permitAll();
            return;
        }
        http.authorizeRequests()
                .antMatchers("/**/*.js", "/**.css").permitAll()
                .antMatchers("/exception/404", "/exception/403", "/api/me/authorities", "/api/heartbeat/beat").permitAll()
                .antMatchers("/api/sign-in", "/api/sign-up").anonymous()
                .antMatchers("/api/sign-out").authenticated()
                .and()
                .authorizeRequests().antMatchers("/api/user", "/api/department", "/api/argument", "/api/printer").hasAuthority("ADMIN")
                .and()
                .authorizeRequests().anyRequest().hasAuthority("USER")
                .and()
                .formLogin().loginPage("/api/system/sign-in").usernameParameter("username").passwordParameter("password").successForwardUrl("/api/system/login?success=true").failureForwardUrl("/api/system/login?error=true")
                .and()
                .logout().logoutUrl("/api/system/sign-out").logoutSuccessUrl("/api/system/logout?success=true");
    }
}
