package com.example.demo.config;

import com.example.demo.security.CustomUserDetailsService;
import com.example.demo.security.TokenAuthenticationFilter;
import com.example.demo.security.oauth2.HttpCookieOAuth2AuthorizationRequestRepository;
import com.example.demo.security.oauth2.OAuth2AuthenticationFailureHandler;
import com.example.demo.security.oauth2.OAuth2AuthenticationSuccessHandler;
import com.example.demo.security.oauth2.RestAuthenticationEntryPoint;
import com.example.demo.security.oauth2.CustomOAuth2UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.Filter;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    private final CustomUserDetailsService customUserDetailsService;

    private final OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler;

    private final OAuth2AuthenticationFailureHandler oAuth2AuthenticationFailureHandler;


    /*@Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .headers().frameOptions().disable() // h2-console 사용을 위해 disable
                .and()
                .authorizeRequests()
                .antMatchers("/**").permitAll()
//                .antMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**", "/profile").permitAll()
                .antMatchers("/api/v1/**").hasAnyRole(Role.GUEST.name(), Role.USER.name())
                .anyRequest().authenticated()
                .and()
                .cors()
                .and()
                .logout()
                .logoutSuccessUrl("/")
                .and()
                .oauth2Login()
                .userInfoEndpoint()
                .userService(customOAuth2UserService);
    }*/
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .csrf()
                .disable()
                .formLogin()
                .disable()
                .httpBasic()
                .disable()
                .exceptionHandling()
                .authenticationEntryPoint(new RestAuthenticationEntryPoint())
                .and()
                .authorizeRequests()
                .antMatchers("/")
                .permitAll()
                .antMatchers("/auth/**", "/oauth2/**")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .oauth2Login()
                .authorizationEndpoint()
                .baseUri("/oauth2/authorization")
                .authorizationRequestRepository(cookieAuthorizationRequestRepository())
                .and()
                .userInfoEndpoint()
                .userService(customOAuth2UserService)
                .and()
                .successHandler(oAuth2AuthenticationSuccessHandler)
                .failureHandler(oAuth2AuthenticationFailureHandler);

        // Add our custom Token based authentication filter
        http.addFilterBefore((Filter) tokenAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public TokenAuthenticationFilter tokenAuthenticationFilter() {
        return new TokenAuthenticationFilter();
    }

    @Bean
    public HttpCookieOAuth2AuthorizationRequestRepository cookieAuthorizationRequestRepository() {
        return new HttpCookieOAuth2AuthorizationRequestRepository();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(customUserDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
