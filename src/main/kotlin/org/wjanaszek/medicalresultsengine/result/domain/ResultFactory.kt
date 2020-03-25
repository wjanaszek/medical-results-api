package org.wjanaszek.medicalresultsengine.result.domain

import org.wjanaszek.medicalresultsengine.result.dto.request.InfoAboutResultDto
import org.wjanaszek.medicalresultsengine.result.dto.request.UpdateInfoAboutResultDto

internal class ResultFactory {
  fun fromDto(dto: InfoAboutResultDto): Result {
    return Result(
      name = dto.name,
      description = dto.description,
      norms = mutableSetOf(dto.menNorm, dto.womenNorm),
      resultsAboveNorm = dto.resultsAboveNorm,
      resultsBelowNorm = dto.resultsBelowNorm
    )
  }

  fun fromDto(dto: UpdateInfoAboutResultDto): Result {
    return Result(
      id = dto.id,
      name = dto.name,
      description = dto.description,
      norms = mutableSetOf(dto.menNorm, dto.womenNorm),
      resultsAboveNorm = dto.resultsAboveNorm,
      resultsBelowNorm = dto.resultsBelowNorm
    )
  }
}
