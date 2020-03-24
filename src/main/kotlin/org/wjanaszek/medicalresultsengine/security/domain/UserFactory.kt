package org.wjanaszek.medicalresultsengine.security.domain

import org.wjanaszek.medicalresultsengine.security.dto.request.SignUpRequest
import org.wjanaszek.medicalresultsengine.security.value.AuthorityName

class UserFactory {
  internal fun createUser(
    payload: SignUpRequest,
    encryptedPassword: String,
    roles: Set<AuthorityName>
  ): User {
    return User(
      username = payload.username,
      password = encryptedPassword,
      roles = roles.map { Role(name = it) }.toSet(),
      enabled = true
    )
  }
}
