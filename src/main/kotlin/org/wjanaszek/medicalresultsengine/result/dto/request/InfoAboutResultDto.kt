package org.wjanaszek.medicalresultsengine.result.dto.request

import org.wjanaszek.medicalresultsengine.result.domain.ResultNorm
import javax.validation.constraints.NotEmpty

internal open class InfoAboutResultDto(
  @field:NotEmpty(message = "Name cannot be empty")
  val name: String,

  @field:NotEmpty(message = "Description cannot be empty")
  val description: String,

  @field:NotEmpty(message = "Men norm cannot be empty")
  val menNorm: ResultNorm,

  @field:NotEmpty(message = "Women norm cannot be empty")
  val womenNorm: ResultNorm,

  @field:NotEmpty(message = "Results below norm cannot be empty")
  val resultsBelowNorm: String,

  @field:NotEmpty(message = "Results above norm cannot be empty")
  val resultsAboveNorm: String
)
