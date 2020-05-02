package org.wjanaszek.medicalresultsengine.result

import org.springframework.http.HttpStatus
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import org.wjanaszek.medicalresultsengine.API_URI
import org.wjanaszek.medicalresultsengine.result.domain.Result
import org.wjanaszek.medicalresultsengine.result.domain.ResultCommandFacade
import org.wjanaszek.medicalresultsengine.result.dto.request.InfoAboutResultDto
import org.wjanaszek.medicalresultsengine.result.dto.request.UpdateInfoAboutResultDto
import javax.validation.Valid

const val RESULT_ADMIN_URI = "$API_URI/admin/results"

@RestController
@RequestMapping(RESULT_ADMIN_URI)
@PreAuthorize("ROLE_ADMIN")
internal class ResultAdminEndpoint(
  private val resultCommandFacade: ResultCommandFacade
) {
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  fun createInfoAboutResult(@Valid @RequestBody data: InfoAboutResultDto) {
    resultCommandFacade.createInfoAboutResult(data)
  }

  @DeleteMapping("/{id}")
  fun deleteResultInfo(@PathVariable id: Long) {
    resultCommandFacade.deleteInfoAboutResult(id)
  }

  @PutMapping("/{id}")
  fun updateResultInfo(
    @PathVariable id: Long,
    @Valid @RequestBody data: InfoAboutResultDto
  ): Result {
    return resultCommandFacade.updateInfoAboutResult(
      UpdateInfoAboutResultDto(
        id = id,
        name = data.name,
        description = data.description,
        menNorm = data.menNorm,
        womenNorm = data.womenNorm,
        resultsAboveNorm = data.resultsAboveNorm,
        resultsBelowNorm = data.resultsBelowNorm
      )
    )
  }
}