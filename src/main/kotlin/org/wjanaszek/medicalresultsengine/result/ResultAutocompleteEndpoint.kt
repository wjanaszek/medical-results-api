package org.wjanaszek.medicalresultsengine.result

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.wjanaszek.medicalresultsengine.result.domain.ResultQueryFacade

const val RESULT_AUTOCOMPLETE_URI = "$RESULT_URI/autocomplete"
const val NAME_URI = "name"

@RestController
@RequestMapping(RESULT_AUTOCOMPLETE_URI)
internal class ResultAutocompleteEndpoint(
  private val resultQueryFacade: ResultQueryFacade
) {
  @GetMapping(NAME_URI)
  fun searchByNameForAutocomplete(@RequestParam query: String): List<String> {
    return resultQueryFacade.searchByNameForAutocomplete(query)
  }
}
