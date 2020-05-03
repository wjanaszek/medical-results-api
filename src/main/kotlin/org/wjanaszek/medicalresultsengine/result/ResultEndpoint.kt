package org.wjanaszek.medicalresultsengine.result

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.wjanaszek.medicalresultsengine.API_URI
import org.wjanaszek.medicalresultsengine.result.domain.Result
import org.wjanaszek.medicalresultsengine.result.domain.ResultQueryFacade
import org.wjanaszek.medicalresultsengine.result.dto.request.SearchByNameRequestDto

const val RESULT_URI = "$API_URI/results"

@RestController
@RequestMapping(RESULT_URI)
internal class ResultEndpoint(
  private val resultQueryFacade: ResultQueryFacade
) {
  @GetMapping
  fun searchByName(@RequestParam query: String): List<Result> {
    return resultQueryFacade.searchForResultByName(SearchByNameRequestDto(query))
  }
}
