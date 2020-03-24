package org.wjanaszek.medicalresultsengine.result.domain

import org.wjanaszek.medicalresultsengine.result.dto.request.CreateInfoAboutResultDto

internal class ResultCommandFacadeImpl(
  private val resultRepository: ResultRepository
) : ResultCommandFacade {
  override fun createInfoAboutResult(data: CreateInfoAboutResultDto) {
    resultRepository.save(ResultFactory().fromDto(data))
  }
}
