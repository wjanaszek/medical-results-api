package org.wjanaszek.medicalresultsengine.security.jwt

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.stereotype.Component
import java.io.IOException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

private const val UNAUTHORIZED_MESSAGE = "You are not authorized to access this resource."

@Component
class JwtAuthenticationEntryPoint : AuthenticationEntryPoint {

  private val log: Logger = LoggerFactory.getLogger(this.javaClass)

  @Throws(IOException::class)
  override fun commence(
    request: HttpServletRequest,
    response: HttpServletResponse,
    authException: AuthenticationException
  ) {
    // This is invoked when user tries to access a secured REST resource without
    // supplying any credentials. We should just send a 401 Unauthorized response,
    // because there is no 'login page' to redirect to.
    log.error("Responding with 'Unauthorized' error. Message - {}", authException)
    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, UNAUTHORIZED_MESSAGE)
  }
}
