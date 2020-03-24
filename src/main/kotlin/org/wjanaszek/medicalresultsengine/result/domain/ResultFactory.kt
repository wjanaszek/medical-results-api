package org.wjanaszek.medicalresultsengine.result.domain

import org.wjanaszek.medicalresultsengine.result.dto.request.CreateInfoAboutResultDto

internal class ResultFactory {
  fun fromDto(dto: CreateInfoAboutResultDto): Result {
    return Result(name = dto.name)
  }
}
