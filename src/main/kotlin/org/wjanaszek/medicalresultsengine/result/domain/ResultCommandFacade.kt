package org.wjanaszek.medicalresultsengine.result.domain

import org.wjanaszek.medicalresultsengine.result.dto.request.InfoAboutResultDto
import org.wjanaszek.medicalresultsengine.result.dto.request.UpdateInfoAboutResultDto

internal interface ResultCommandFacade {
  fun createInfoAboutResult(data: InfoAboutResultDto)
  fun deleteInfoAboutResult(id: Long)
  fun updateInfoAboutResult(data: UpdateInfoAboutResultDto): Result
}
