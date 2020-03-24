package org.wjanaszek.medicalresultsengine.security.jwt

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.lang.NonNull
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import org.wjanaszek.medicalresultsengine.security.domain.UsersFacade
import java.io.IOException
import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class JwtAuthenticationTokenFilter : OncePerRequestFilter() {

  private val log = LoggerFactory.getLogger(this.javaClass)

  @Autowired
  private lateinit var usersFacade: UsersFacade

  @Value("\${jwt.secret}")
  private lateinit var secret: String

  @Throws(ServletException::class, IOException::class)
  override fun doFilterInternal(
    @NonNull request: HttpServletRequest,
    @NonNull response: HttpServletResponse,
    @NonNull filterChain: FilterChain
  ) {
    val jwt = extractToken(request, secret)

    if (jwt.isValid()) {
      val username = jwt.getUsername()
      log.debug("Checking authentication for user {}", username)

      // It is not compelling necessary to load the use details from the database.
      // You could also store the information in the token and read it from it. It's up to you ;)
      val userDetails = usersFacade.loadUserByUsername(username)

      val authenticationToken = createAuthToken(userDetails)
      authenticationToken.details = WebAuthenticationDetailsSource().buildDetails(request)

      log.debug("Authenticated user {}, setting security context", username)
      SecurityContextHolder.getContext().authentication = authenticationToken
    }

    filterChain.doFilter(request, response)
  }

  private fun createAuthToken(userDetails: UserDetails): UsernamePasswordAuthenticationToken {
    return UsernamePasswordAuthenticationToken(
      userDetails,
      null,
      userDetails.authorities
    )
  }
}
