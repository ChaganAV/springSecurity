package com.example.springSecurity.configure;

import com.example.springSecurity.model.JwtAuthenticationFilter;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
//@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    //private PasswordEncoder passwordEncoder;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/private-data").hasRole("ADMIN") // на этот ресурс требуется роль админ
                .antMatchers("/public-data").authenticated() // требуется аутентификация пользователя
                .and()// разделяем фильтры
                .formLogin()// страница логина
                //.loginPage("/login") // воспользуемся страничкой логина Spring
                .permitAll() // разрешаем всем пользоваться этой страницей
                .loginProcessingUrl("/process_login")// переход на обработку запроса
                .defaultSuccessUrl("/public-data")// после идентификации переход на страничку
                .and()
                .logout().logoutSuccessUrl("/login")// после выхода направление на страничку
                //.anyRequest().authenticated()
                .and()
                .addFilter(new JwtAuthenticationFilter(authenticationManager()))
                .addFilterBefore(new JwtAuthenticationFilter(authenticationManager()),
                        UsernamePasswordAuthenticationFilter.class);
    }
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
        auth.inMemoryAuthentication()
                .withUser("user").password("{noop}password")
                .roles("USER")
                .and()
                .withUser("admin").password("{noop}password")
                .roles("ADMIN");
    }
}
