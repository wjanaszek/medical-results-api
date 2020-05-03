package org.wjanaszek.medicalresultsengine.result.domain

import org.wjanaszek.medicalresultsengine.result.dto.request.SearchByNameRequestDto

internal data class ResultQueryFacadeImpl(
  private val resultRepository: ResultRepository
) : ResultQueryFacade {
  override fun searchByNameForAutocomplete(name: String): List<String> {
    return resultRepository.searchByName(name)
  }

  override fun searchForResultByName(data: SearchByNameRequestDto): List<Result> {
    val result = resultRepository.searchAllByNameContaining(data.name)
    return result
  }
}
