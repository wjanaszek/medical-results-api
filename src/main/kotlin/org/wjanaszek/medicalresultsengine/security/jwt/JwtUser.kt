package org.wjanaszek.medicalresultsengine.security.jwt

import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.*

/**
 * Class used to send whole user data.
 */
data class JwtUser(
  val id: Long? = null,
  private val username: String,
  private val password: String?,
  private val authorities: Collection<GrantedAuthority>? = null,
  val lastPasswordResetDate: Date? = null
) : UserDetails {

  override fun getAuthorities(): Collection<GrantedAuthority>? {
    return authorities
  }

  override fun getUsername(): String {
    return username
  }

  @JsonIgnore
  override fun getPassword(): String? {
    return password
  }

  @JsonIgnore
  override fun isAccountNonExpired(): Boolean {
    return true
  }

  @JsonIgnore
  override fun isAccountNonLocked(): Boolean {
    return true
  }

  @JsonIgnore
  override fun isCredentialsNonExpired(): Boolean {
    return true
  }

  override fun isEnabled(): Boolean {
    return true
  }

  fun getRoles(): List<String> {
    return authorities?.map { it.authority } ?: emptyList()
  }
}
