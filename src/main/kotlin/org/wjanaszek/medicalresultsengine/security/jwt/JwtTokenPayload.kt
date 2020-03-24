package org.wjanaszek.medicalresultsengine.security.jwt

import java.time.LocalDateTime

data class JwtTokenPayload(
  val username: String,
  val roles: List<String>,
  val creationTime: LocalDateTime,
  val expirationTime: LocalDateTime
)
