package org.wjanaszek.medicalresultsengine.result.domain

import org.springframework.data.repository.Repository

internal interface ResultRepository : Repository<Result, Long> {
  fun findAllByNameContaining(name: String): List<Result>
  fun save(entity: Result)
}
