package org.wjanaszek.medicalresultsengine.result.domain

import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.Repository
import org.springframework.data.repository.query.Param

internal interface ResultRepository : Repository<Result, Long> {
  fun deleteById(id: Long)
  fun findById(id: Long): Result?
  fun save(entity: Result): Result

  @Query("""
    SELECT *
    FROM results r
    WHERE position(lower(:keyword) IN lower(r.name)) > 0
  """, nativeQuery = true)
  fun searchAllResultByName(@Param("keyword") keyword: String): List<Result>
  @Query("""
      SELECT r.name
      FROM results r
      WHERE position(lower(:keyword) IN lower(r.name)) > 0
  """, nativeQuery = true)
  fun searchAllNameByName(@Param("keyword") keyword: String): List<String>
}
