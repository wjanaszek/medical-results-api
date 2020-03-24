package org.wjanaszek.medicalresultsengine.security.domain

import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.wjanaszek.medicalresultsengine.security.jwt.JwtUser
import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.JoinTable
import javax.persistence.ManyToMany
import javax.persistence.Table
import javax.validation.constraints.Email
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

@Entity
@Table(name = "USERS")
data class User(
  @Id
  @Column(name = "ID")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  val id: Long? = null,

  @Column(name = "USERNAME", length = 50, unique = true)
  @Size(min = 4, max = 50)
  val username: String,

  @Column(name = "EMAIL", length = 50)
  @Email
  private val email: String? = null,

  @Column(name = "PASSWORD", length = 100)
  @Size(min = 4, max = 100)
  @NotNull
  @JsonIgnore
  private val password: String,

  @Column(name = "ENABLED")
  @NotNull
  private val enabled: Boolean? = null,

  @ManyToMany(fetch = FetchType.EAGER, cascade = [CascadeType.ALL])
  @JoinTable(
    name = "USER_AUTHORITIES",
    joinColumns = [JoinColumn(name = "USER_ID", referencedColumnName = "ID")],
    inverseJoinColumns = [JoinColumn(name = "AUTHORITY_ID", referencedColumnName = "ID")]
  )
  private val roles: Set<Role>? = null
) {
  fun jwtUser(): JwtUser {
    return JwtUser(
      id = id,
      username = username,
      password = password,
      authorities = mapToGrantedAuthorities(roles)
    )
  }

  private fun mapToGrantedAuthorities(roles: Set<Role>?): Set<GrantedAuthority> {
    return roles?.map { authority -> SimpleGrantedAuthority(authority.name!!.name) }?.toSet()
      ?: setOf()
  }
}
