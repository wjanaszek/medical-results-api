package org.wjanaszek.medicalresultsengine.result.domain

import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.Repository
import org.springframework.data.repository.query.Param

internal interface ResultRepository : Repository<Result, Long> {
  fun deleteById(id: Long)
  fun findAllByNameContaining(name: String): List<Result>
  fun findById(id: Long): Result?
  fun save(entity: Result): Result

  @Query("SELECT r.name FROM Result r WHERE r.name LIKE %:keyword%")
  fun searchByName(@Param("keyword") keyword: String): List<String>
}
