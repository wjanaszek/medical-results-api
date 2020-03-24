package org.wjanaszek.medicalresultsengine.result.domain

import org.wjanaszek.medicalresultsengine.result.dto.request.SearchByNameRequestDto

internal data class ResultQueryFacadeImpl(
  private val resultRepository: ResultRepository
) : ResultQueryFacade {
  override fun searchForResultByName(data: SearchByNameRequestDto): List<Result> {
    return resultRepository.findAllByNameContaining(data.name)
  }
}
