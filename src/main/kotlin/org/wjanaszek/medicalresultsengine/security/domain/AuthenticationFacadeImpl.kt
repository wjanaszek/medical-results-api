package org.wjanaszek.medicalresultsengine.security.domain

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.context.SecurityContextHolder
import org.wjanaszek.medicalresultsengine.security.dto.request.JwtAuthenticationRequest
import org.wjanaszek.medicalresultsengine.security.dto.response.JwtAuthenticationResponse
import org.wjanaszek.medicalresultsengine.security.exceptions.RefreshTokenFailException
import org.wjanaszek.medicalresultsengine.security.jwt.JwtToken
import org.wjanaszek.medicalresultsengine.security.jwt.JwtTokenPayload
import org.wjanaszek.medicalresultsengine.security.jwt.JwtUser
import org.wjanaszek.medicalresultsengine.security.jwt.extractToken
import org.wjanaszek.medicalresultsengine.security.jwt.generateToken
import java.time.LocalDateTime
import javax.servlet.http.HttpServletRequest

internal class AuthenticationFacadeImpl(
  private val authenticationManager: AuthenticationManager,
  private val usersFacade: UsersFacade
) : AuthenticationFacade {
  private val log = LoggerFactory.getLogger(AuthenticationFacadeImpl::class.java)

  @Value("\${jwt.secret}")
  private lateinit var secret: String

  @Value("\${jwt.expiration}")
  private val expiration: Long = 0

  override fun signIn(authenticationRequest: JwtAuthenticationRequest): JwtAuthenticationResponse {
    log.info("Trying to authenticate user {}", authenticationRequest.username)

    // Perform the security
    authenticateWithUsernameAndPassword(authenticationRequest)

    // Reload password post-security, so we can generate token
    val userDetails = usersFacade.loadUserByUsername(authenticationRequest.username)

    val token = generateNewSecurityToken(userDetails)

    // Return the token
    return JwtAuthenticationResponse(token.token)
  }

  override fun refreshToken(request: HttpServletRequest): JwtAuthenticationResponse {
    val token = extractToken(request, secret)
    val username = token.getUsername()

    if (token.canBeRefreshed()) {
      log.info("Refreshing token for user: {}", username)
      val refreshedToken = token.refresh(expiration)
      return JwtAuthenticationResponse(refreshedToken.token)
    } else {
      throw RefreshTokenFailException()
    }
  }

  private fun authenticateWithUsernameAndPassword(authenticationRequest: JwtAuthenticationRequest): Authentication {
    val authenticationToken = UsernamePasswordAuthenticationToken(
      authenticationRequest.username,
      authenticationRequest.password
    )

    try {
      val authenticationResponse = authenticationManager.authenticate(authenticationToken)
      log.info("User {} is authenticated: {}", authenticationRequest.username, authenticationResponse.isAuthenticated)
      SecurityContextHolder.getContext().authentication = authenticationResponse
      return authenticationResponse
    } catch (authenticationException: AuthenticationException) {
      throw WrongCredentialsException()
    }
  }

  private fun generateNewSecurityToken(userDetails: JwtUser): JwtToken {
    log.info("Generating JWT for user {}", userDetails.username)
    val tokenPayload = JwtTokenPayload(
      username = userDetails.username,
      creationTime = LocalDateTime.now(),
      expirationTime = LocalDateTime.now().plusSeconds(expiration),
      roles = userDetails.getRoles()
    )
    return generateToken(tokenPayload, secret)
  }
}