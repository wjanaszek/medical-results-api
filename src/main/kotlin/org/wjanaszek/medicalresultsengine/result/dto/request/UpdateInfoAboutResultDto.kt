package org.wjanaszek.medicalresultsengine.result.dto.request

import org.wjanaszek.medicalresultsengine.result.domain.ResultNorm
import javax.validation.constraints.NotEmpty

internal class UpdateInfoAboutResultDto(
  @field:NotEmpty(message = "Id cannot be empty")
  val id: Long,

  name: String,
  description: String,
  menNorm: ResultNorm,
  womenNorm: ResultNorm,
  resultsBelowNorm: String,
  resultsAboveNorm: String
) : InfoAboutResultDto(name, description, menNorm, womenNorm, resultsBelowNorm, resultsAboveNorm)
