package org.wjanaszek.medicalresultsengine.result.dto.request

import javax.validation.constraints.NotEmpty

internal data class CreateInfoAboutResultDto(
  @field:NotEmpty(message = "Name cannot be empty")
  val name: String
)
