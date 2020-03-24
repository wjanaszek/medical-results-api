package org.wjanaszek.medicalresultsengine.result.domain

import org.wjanaszek.medicalresultsengine.result.dto.request.SearchByNameRequestDto

internal interface ResultQueryFacade {
  fun searchForResultByName(data: SearchByNameRequestDto): List<Result>
}
