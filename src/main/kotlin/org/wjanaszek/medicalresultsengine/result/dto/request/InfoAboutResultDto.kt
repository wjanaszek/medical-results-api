package org.wjanaszek.medicalresultsengine.result.dto.request

import org.wjanaszek.medicalresultsengine.result.domain.ResultNorm
import javax.validation.constraints.NotEmpty

internal open class InfoAboutResultDto(
  @field:NotEmpty(message = "Name cannot be empty")
  val name: String,

  @field:NotEmpty(message = "Description cannot be empty")
  val description: String,

  @field:NotEmpty(message = "Way to get cannot be empty")
  val wayToGet: String,

  @field:NotEmpty(message = "Men norm cannot be empty")
  val menNorm: ResultNorm,

  @field:NotEmpty(message = "Women norm cannot be empty")
  val womenNorm: ResultNorm,

  @field:NotEmpty(message = "Below norm cannot be empty")
  val belowNorm: String,

  @field:NotEmpty(message = "Above norm cannot be empty")
  val aboveNorm: String
)
