package org.wjanaszek.medicalresultsengine.security.domain

import org.springframework.data.repository.Repository
import org.springframework.data.rest.core.annotation.RepositoryRestResource

@RepositoryRestResource(exported = false)
internal interface UserRepository : Repository<User, Long> {
  fun findByUsername(username: String): User?
  fun findById(id: Long): User?
  fun findAll(): List<User>
  fun save(entity: User): User
  fun deleteById(id: Long)
}
