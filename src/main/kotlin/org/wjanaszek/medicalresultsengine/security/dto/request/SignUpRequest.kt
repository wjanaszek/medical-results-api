package org.wjanaszek.medicalresultsengine.security.dto.request

import javax.validation.constraints.NotEmpty

data class SignUpRequest(
  @field:NotEmpty(message = "Username can not be empty")
  val username: String,

  @field:NotEmpty(message = "Password can not be empty")
  val password: String
)
