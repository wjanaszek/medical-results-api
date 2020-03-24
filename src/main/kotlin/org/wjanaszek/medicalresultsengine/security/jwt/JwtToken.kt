package org.wjanaszek.medicalresultsengine.security.jwt

import io.jsonwebtoken.Claims
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.MalformedJwtException
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.UnsupportedJwtException
import org.slf4j.LoggerFactory
import org.springframework.http.HttpHeaders
import java.security.SignatureException
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*
import javax.servlet.http.HttpServletRequest

data class JwtToken(val token: String, private val secret: String) {

  private val log = LoggerFactory.getLogger(this.javaClass)

  fun canBeRefreshed(): Boolean {
    return !isExpired()
  }

  fun getUsername(): String {
    return getClaims().subject
  }

  fun getRoles(): List<String> {
    val joinedRoles = getClaims()["roles"] as String
    return joinedRoles.split(",").map { it.trim() }
  }

  fun getCreatedDate(): LocalDateTime {
    return convertToLocalDate(getClaims().issuedAt)
  }

  fun getExpirationDate(): LocalDateTime {
    return convertToLocalDate(getClaims().expiration)
  }

  fun isValid(): Boolean {
    try {
      Jwts.parser()
        .setSigningKey(secret)
        .parseClaimsJws(token)
      return true
    } catch (ex: SignatureException) {
      log.error("Invalid JWT signature.")
    } catch (ex: MalformedJwtException) {
      log.error("Invalid JWT token")
    } catch (ex: ExpiredJwtException) {
      log.error("Expired JWT token.")
    } catch (ex: UnsupportedJwtException) {
      log.error("Unsupported JWT token.")
    } catch (ex: IllegalArgumentException) {
      log.error("JWT claims string is empty.")
    }

    return false
  }

  fun refresh(tokenLifespanInSeconds: Long): JwtToken {
    val currentTime = LocalDateTime.now()
    val newPayload = JwtTokenPayload(
      username = getUsername(),
      roles = getRoles(),
      creationTime = currentTime,
      expirationTime = currentTime.plusSeconds(tokenLifespanInSeconds)
    )
    return generateToken(newPayload, secret)
  }

  private fun getClaims(): Claims {
    return Jwts.parser()
      .setSigningKey(secret)
      .parseClaimsJws(token)
      .body
  }

  private fun isExpired(): Boolean {
    return try {
      getClaims()
      false
    } catch (ex: ExpiredJwtException) {
      true
    }
  }
}

fun extractToken(request: HttpServletRequest, secret: String): JwtToken {
  val tokenHeader = HttpHeaders.AUTHORIZATION
  val authToken: String = request.getHeader(tokenHeader) ?: ""
  return if (authToken.startsWith("Bearer ")) {
    JwtToken(authToken.substring(7), secret)
  } else {
    JwtToken(authToken, secret)
  }
}

fun generateToken(payload: JwtTokenPayload, secret: String): JwtToken {
  val token = Jwts.builder()
    .setSubject(payload.username)
    .setIssuedAt(convertToDate(payload.creationTime))
    .setExpiration(convertToDate(payload.expirationTime))
    .signWith(SignatureAlgorithm.HS512, secret)
    .claim("roles", payload.roles.joinToString(","))
    .compact()
  return JwtToken(token, secret)
}

private fun convertToDate(localDateTime: LocalDateTime): Date {
  return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant())
}

private fun convertToLocalDate(date: Date): LocalDateTime {
  return date.toInstant()
    .atZone(ZoneId.systemDefault())
    .toLocalDateTime()
}
