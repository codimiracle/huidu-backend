package com.codimiracle.application.platform.huidu;

import com.codimiracle.web.mybatis.contract.Paginator;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.annotation.MapperScans;
import org.springframework.context.annotation.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableAspectJAutoProxy
@ComponentScans({
        @ComponentScan(basePackageClasses = HuiduWebBackendApplication.class),
        @ComponentScan(basePackages = "com.codimiracle.web.basic.contract"),
        @ComponentScan(basePackages = "com.codimiracle.web.middleware.content"),
        @ComponentScan(basePackages = "com.codimiracle.web.notification.middleware")
})
@MapperScans({
        @MapperScan("com.codimiracle.web.middleware.content.mapper"),
        @MapperScan("com.codimiracle.web.notification.middleware.mapper"),
        @MapperScan("com.codimiracle.application.platform.huidu.mapper")
})
//@EnableElasticsearchRepositories(basePackages = "com.codimiracle.application.platform.huidu.elasticsearch.repository")
public class HuiduWebBackendConfiguration {

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public Paginator paginator() {
        return new Paginator();
    }
}
