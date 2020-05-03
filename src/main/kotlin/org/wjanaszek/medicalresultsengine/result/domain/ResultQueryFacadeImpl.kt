package org.wjanaszek.medicalresultsengine.result.domain

import org.wjanaszek.medicalresultsengine.result.dto.request.SearchByNameRequestDto

internal data class ResultQueryFacadeImpl(
  private val resultRepository: ResultRepository
) : ResultQueryFacade {
  override fun searchByNameForAutocomplete(name: String): List<String> {
    return resultRepository.searchAllNameByName(name)
  }

  override fun searchForResultsByName(data: SearchByNameRequestDto): List<Result> {
    return resultRepository.searchAllResultByName(data.name)
  }
}
