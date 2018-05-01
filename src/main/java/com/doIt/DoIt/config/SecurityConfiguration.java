package com.doIt.DoIt.config;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
/**
 * Class that has settings and configurations for Spring Security
 * Based on the tutorial:
 * https://medium.com/@gustavo.ponce.ch/spring-boot-spring-mvc-spring-security-mysql-a5d8545d837d
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration  extends WebSecurityConfigurerAdapter{

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private DataSource dataSource;

    @Value("@{spring.queries.users-query}")
    private String usersQuery;

    @Value("@{spring.queries.roles-query}")
    private String rolesQuery;

    //sql query for authentication
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery("select username as principal, password as credentials, true from member where username= ?")
                .authoritiesByUsernameQuery("select m.username, r.name from member m inner join member_roles mr on(m.username=mr.username) inner join role r on(mr.roleID=r.roleID) where m.username=?");
    }

    //configuration for pages permissions
    @Override
    protected void configure(HttpSecurity http) throws Exception{

        http.
                authorizeRequests()
                    .antMatchers("/").permitAll()
                    .antMatchers("/login").permitAll()
                    .antMatchers("/registration").permitAll()
                    .antMatchers("/access-denied").permitAll()
                    .antMatchers("/resources/**/**").permitAll().anyRequest().permitAll()
                    .antMatchers("/admin/**").hasAnyAuthority("admin").anyRequest()
                    .authenticated().and().csrf().disable().formLogin()
                    .loginPage("/login").failureUrl("/login?error=true")
                    .defaultSuccessUrl("/tasks", true)
                    .usernameParameter("username")
                    .passwordParameter("password")
                    .and().logout()
                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                    .logoutSuccessUrl("/").and().exceptionHandling()
                    .accessDeniedPage("/access-denied");
    }


    //security set to ignore resource folders so that css/html loads properly for every user
    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/resources/**/**", "/static/**", "**/css/**", "**/js/**", "**/images/**");
    }
}
