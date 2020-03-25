package org.wjanaszek.medicalresultsengine.result.domain

import org.wjanaszek.medicalresultsengine.result.dto.request.SearchByNameRequestDto

internal interface ResultQueryFacade {
  fun searchByNameForAutocomplete(name: String): List<String>
  fun searchForResultByName(data: SearchByNameRequestDto): List<Result>
}
