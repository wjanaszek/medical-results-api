package org.wjanaszek.medicalresultsengine.result.dto.request

import org.wjanaszek.medicalresultsengine.result.domain.ResultNorm
import javax.validation.constraints.NotEmpty

internal class UpdateInfoAboutResultDto(
  @field:NotEmpty(message = "Id cannot be empty")
  val id: Long,

  name: String,
  description: String,
  wayToGet: String,
  menNorm: ResultNorm,
  womenNorm: ResultNorm,
  belowNorm: String,
  aboveNorm: String
) : InfoAboutResultDto(name, description, wayToGet, menNorm, womenNorm, belowNorm, aboveNorm)
