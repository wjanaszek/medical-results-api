package org.wjanaszek.medicalresultsengine.result.domain

import org.wjanaszek.medicalresultsengine.result.dto.request.CreateInfoAboutResultDto

internal interface ResultCommandFacade {
  fun createInfoAboutResult(data: CreateInfoAboutResultDto)
}
