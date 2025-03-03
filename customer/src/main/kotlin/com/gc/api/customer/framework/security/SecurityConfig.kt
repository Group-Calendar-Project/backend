package com.gc.api.customer.framework.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableWebSecurity
class SecurityConfig(
  val jwtAuthenticationFilter: JwtAuthenticationFilter,
) {

  @Bean
  fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
    return http
      .httpBasic { it.disable() }
      .csrf { it.disable() }
      .formLogin { it.disable() }
      .sessionManagement { it.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }
      .authorizeHttpRequests {
        it.requestMatchers("/login/**").permitAll()
          .requestMatchers("/graphql/**")
          .hasAnyAuthority(CustomAuthority.MEMBER.authority, CustomAuthority.ADMIN.authority)
          .anyRequest()
          .permitAll()
      }
      .addFilterBefore(
        jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter::class.java)
      .build()
  }
}