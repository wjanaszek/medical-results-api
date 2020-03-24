package org.wjanaszek.medicalresultsengine.security.domain

import org.wjanaszek.medicalresultsengine.security.dto.request.JwtAuthenticationRequest
import org.wjanaszek.medicalresultsengine.security.dto.response.JwtAuthenticationResponse
import javax.servlet.http.HttpServletRequest

internal interface AuthenticationFacade {
  fun signIn(authenticationRequest: JwtAuthenticationRequest): JwtAuthenticationResponse
  fun refreshToken(request: HttpServletRequest): JwtAuthenticationResponse
}
