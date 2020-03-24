package org.wjanaszek.medicalresultsengine.result

import org.springframework.http.HttpStatus
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import org.wjanaszek.medicalresultsengine.API_URI
import org.wjanaszek.medicalresultsengine.result.domain.Result
import org.wjanaszek.medicalresultsengine.result.domain.ResultCommandFacade
import org.wjanaszek.medicalresultsengine.result.domain.ResultQueryFacade
import org.wjanaszek.medicalresultsengine.result.dto.request.CreateInfoAboutResultDto
import org.wjanaszek.medicalresultsengine.result.dto.request.SearchByNameRequestDto
import javax.validation.Valid

const val RESULT_URI = "$API_URI/results"

@RestController
@RequestMapping(RESULT_URI)
internal class ResultEndpoint(
  private val resultCommandFacade: ResultCommandFacade,
  private val resultQueryFacade: ResultQueryFacade
) {
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  @PreAuthorize("ROLE_ADMIN")
  fun createInfoAboutResult(@Valid @RequestBody data: CreateInfoAboutResultDto) {
    resultCommandFacade.createInfoAboutResult(data)
  }

  @GetMapping
  fun searchByName(@RequestParam name: String): List<Result> {
    return resultQueryFacade.searchForResultByName(SearchByNameRequestDto(name))
  }
}
