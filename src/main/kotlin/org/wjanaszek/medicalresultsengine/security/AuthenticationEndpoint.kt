package org.wjanaszek.medicalresultsengine.security

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.wjanaszek.medicalresultsengine.API_URI
import org.wjanaszek.medicalresultsengine.security.domain.AuthenticationFacade
import org.wjanaszek.medicalresultsengine.security.dto.request.JwtAuthenticationRequest
import org.wjanaszek.medicalresultsengine.security.dto.response.JwtAuthenticationResponse
import javax.servlet.http.HttpServletRequest
import javax.validation.Valid

const val AUTH_URI = "$API_URI/auth"
const val SIGN_IN_URI = "/sign-in"
const val REFRESH_TOKEN_URI = "/refresh"

@RestController
@RequestMapping(AUTH_URI)
internal class AuthenticationEndpoint @Autowired constructor(
  private val authenticationFacade: AuthenticationFacade
) {
  @PostMapping(SIGN_IN_URI)
  fun createAuthenticationToken(
    @Valid @RequestBody authenticationRequest: JwtAuthenticationRequest
  ): JwtAuthenticationResponse {
    return authenticationFacade.signIn(authenticationRequest)
  }

  @GetMapping(REFRESH_TOKEN_URI)
  fun refreshAndGetAuthenticationToken(
    request: HttpServletRequest
  ): JwtAuthenticationResponse {
    return authenticationFacade.refreshToken(request)
  }
}