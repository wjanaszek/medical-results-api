package org.wjanaszek.medicalresultsengine.security

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.BeanIds
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.wjanaszek.medicalresultsengine.security.domain.UsersFacade
import org.wjanaszek.medicalresultsengine.security.jwt.JwtAuthenticationEntryPoint
import org.wjanaszek.medicalresultsengine.security.jwt.JwtAuthenticationTokenFilter

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
internal class WebSecurityConfiguration @Autowired
constructor(
  private val unauthorizedHandler: JwtAuthenticationEntryPoint,
  private val jwtAuthenticationTokenFilter: JwtAuthenticationTokenFilter,
  private val usersFacade: UsersFacade,
  private val passwordEncoder: PasswordEncoder
) : WebSecurityConfigurerAdapter() {
  private val log = LoggerFactory.getLogger(WebSecurityConfiguration::class.java)

  @Bean(BeanIds.AUTHENTICATION_MANAGER)
  @Throws(Exception::class)
  override fun authenticationManagerBean(): AuthenticationManager {
    return super.authenticationManagerBean()
  }

  @Throws(Exception::class)
  override fun configure(authenticationManagerBuilder: AuthenticationManagerBuilder) {
    authenticationManagerBuilder
      .userDetailsService(usersFacade)
      .passwordEncoder(passwordEncoder)
  }

  @Throws(Exception::class)
  override fun configure(httpSecurity: HttpSecurity) {
    httpSecurity
      .csrf().disable()
      .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
      .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
      .authorizeRequests()
      .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
      .antMatchers(
        HttpMethod.POST,
        "/api/auth/sign-in",
        "/api/users/sign-up"
      ).permitAll()
      .antMatchers(
        HttpMethod.GET,
        "/api/auth/refresh"
      ).permitAll()
      .antMatchers().permitAll()
      .anyRequest().authenticated()

    httpSecurity
      .addFilterBefore(
        jwtAuthenticationTokenFilter,
        UsernamePasswordAuthenticationFilter::class.java
      )

    httpSecurity
      .headers()
      .frameOptions().sameOrigin()
      .cacheControl()
  }

  @Throws(Exception::class)
  override fun configure(web: WebSecurity) {
    web
      .ignoring()
      .antMatchers(
        HttpMethod.GET,
        "/",
        "/*.html",
        "/favicon.ico",
        "/**/*.html",
        "/**/*.css",
        "/**/*.js",
        "/*.css",
        "/assets/**",
        "/*.js"
      )
  }
}
