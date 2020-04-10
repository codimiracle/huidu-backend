package com.codimiracle.application.platform.huidu.configurer;

import com.codimiracle.application.platform.huidu.security.HuiduTokenAuthenticationFilter;
import com.codimiracle.application.platform.huidu.security.HuiduTokenAuthenticationProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;

@Configuration
public class WebSecurityConfigurer extends WebSecurityConfigurerAdapter {
    private HuiduTokenAuthenticationProvider huiduTokenAuthenticationProvider = new HuiduTokenAuthenticationProvider();

    private HuiduTokenAuthenticationFilter huiduTokenAuthenticationFilter = new HuiduTokenAuthenticationFilter();

    @Bean
    public HuiduTokenAuthenticationProvider getHuiduTokenAuthenticationProvider() {
        return huiduTokenAuthenticationProvider;
    }

    @Bean
    public HuiduTokenAuthenticationFilter getHuiduTokenAuthenticationFilter() throws Exception {
        huiduTokenAuthenticationFilter.setAuthenticationManager(authenticationManager());
        return huiduTokenAuthenticationFilter;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(huiduTokenAuthenticationProvider);
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        //启用跨越配置
        http.cors()
                .and()
                //启用匿名用户配置
                .anonymous()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterAfter(huiduTokenAuthenticationFilter, AnonymousAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers("/api/system/sign-in", "/api/system/sign-up").anonymous()
                .antMatchers("/api/system/sign-out").authenticated()
                .antMatchers(
                        "/api/electronic-books/**", "/api/audio-books/**", "/api/paper-books/**",
                        "/api/contents/**", "/api/categories/**", "/api/common/**",
                        "/api/reference-data/**", "/api/user/avatar/**", "/api/books/cover/**",
                        "/api/community/**", "/api/search/**").permitAll()
                .antMatchers("/api/user/**").hasAuthority("frontend-services")

                .antMatchers("/api/backend/creator").hasAuthority("creator")
                .antMatchers("/api/backend/creator/dashboard").hasAuthority("author-data-services")
                .antMatchers("/api/backend/creator/audio-books/**").hasAuthority("author-audio-books-service")
                .antMatchers("/api/backend/creator/electronic-books/**").hasAuthority("author-electronic-books-service")
                .antMatchers("/api/backend/shopping").hasAuthority("shopping").antMatchers("/api/backend/shopping/commodities/**").hasAuthority("shopping-commodities")
                .antMatchers("/api/backend/shopping/orders/**").hasAuthority("shopping-orders")
                .antMatchers("/api/backend/shopping/commodities/**").hasAuthority("shopping-commodities")
                .antMatchers("/api/backend/shopping/paper-books/**").hasAuthority("shopping-paper-books")
                .antMatchers("/api/backend/classification").hasAuthority("classification")
                .antMatchers("/api/backend/classification/categories/**").hasAuthority("classification-categories")
                .antMatchers("/api/backend/classification/tags/**").hasAuthority("classification-tags")
                .antMatchers("/api/backend/classification/collections/**").hasAuthority("classification-collection")
                .antMatchers("/api/backend/user").hasAuthority("system")
                .antMatchers("/api/backend/user/users").hasAuthority("user-users")
                .antMatchers("/api/backend/classification/tags").hasAuthority("classification-tags")
                .antMatchers("/api/backend/user/roles").hasAuthority("user-roles")
                .antMatchers("/api/backend/content/topics/**").hasAuthority("content-topics")
                .antMatchers("/api/backend/content/reviews/**").hasAuthority("content-reviews")
                .antMatchers("/api/backend/content/comments/**").hasAuthority("content-comments")
                .antMatchers("/api/backend/content/electronic-books/**").hasAuthority("content-electronic-books")
                .antMatchers("/api/backend/content/audio-books/**").hasAuthority("content-electronic-books")
                .and()
                .csrf().disable()
                .httpBasic().disable()
                .formLogin().disable()
                .logout().disable();
    }
}
