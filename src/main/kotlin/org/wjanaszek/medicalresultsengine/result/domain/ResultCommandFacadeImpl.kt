package org.wjanaszek.medicalresultsengine.result.domain

import org.wjanaszek.medicalresultsengine.result.dto.request.InfoAboutResultDto
import org.wjanaszek.medicalresultsengine.result.dto.request.UpdateInfoAboutResultDto
import org.wjanaszek.medicalresultsengine.result.exceptions.ResultInfoNotFoundException

internal class ResultCommandFacadeImpl(
  private val resultRepository: ResultRepository
) : ResultCommandFacade {
  override fun createInfoAboutResult(data: InfoAboutResultDto) {
    resultRepository.save(ResultFactory().fromDto(data))
  }

  override fun deleteInfoAboutResult(id: Long) {
    resultRepository.findById(id) ?: throw ResultInfoNotFoundException(id)
    resultRepository.deleteById(id)
  }

  override fun updateInfoAboutResult(data: UpdateInfoAboutResultDto): Result {
    resultRepository.findById(data.id) ?: throw ResultInfoNotFoundException(data.id)
    return resultRepository.save(ResultFactory().fromDto(data))
  }
}
