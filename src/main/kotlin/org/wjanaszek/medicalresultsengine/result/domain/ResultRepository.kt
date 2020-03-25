package org.wjanaszek.medicalresultsengine.result.domain

import org.springframework.data.repository.Repository

internal interface ResultRepository : Repository<Result, Long> {
  fun deleteById(id: Long)
  fun findAllByNameContaining(name: String): List<Result>
  fun findById(id: Long): Result?
  fun save(entity: Result): Result
}
