package com.tienlm.studentmanagement.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import static com.tienlm.studentmanagement.security.ApplicationUserPersmission.*;
import static com.tienlm.studentmanagement.security.ApplicationUserRole.*;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public ApplicationSecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
//                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "index","/css/*","/js/*") .permitAll()
                .antMatchers("api/**").hasAnyRole(ADMIN.name(), USER.name())
                .antMatchers(HttpMethod.GET,"api/students").hasAuthority(STUDENT_MODULE_ACCESS.name())

                .antMatchers(HttpMethod.POST, "api/students").hasAuthority(STUDENT_CREATE.getPersmission())
                .antMatchers(HttpMethod.GET, "api/students/**").hasAuthority(STUDENT_READ.getPersmission())
                .antMatchers(HttpMethod.PUT, "api/students/**").hasAuthority(STUDENT_UPDATE.getPersmission())
                .antMatchers(HttpMethod.DELETE, "api/students/**").hasAuthority(STUDENT_DELETE.getPersmission())

                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }


    @Override
    @Bean
    protected UserDetailsService userDetailsService(){
        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder.encode("123"))
                .roles(ADMIN.name())
                .build();
        UserDetails user = User.builder()
                .username("user")
                .password(passwordEncoder.encode("123"))
                .roles(USER.name())
                .build();
        return new InMemoryUserDetailsManager(admin, user);
    }
}
