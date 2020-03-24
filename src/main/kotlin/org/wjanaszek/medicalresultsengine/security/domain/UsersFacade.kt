package org.wjanaszek.medicalresultsengine.security.domain

import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder
import org.wjanaszek.medicalresultsengine.security.dto.request.SignUpRequest
import org.wjanaszek.medicalresultsengine.security.dto.request.UserCreatedResponse
import org.wjanaszek.medicalresultsengine.security.jwt.JwtUser
import org.wjanaszek.medicalresultsengine.security.value.AuthorityName

class UsersFacade internal constructor(
  private val userRepository: UserRepository,
  private val passwordEncoder: PasswordEncoder
) : UserDetailsService {

  /**
   * Find user if is registered in system.
   *
   * @param username used to identify the user
   * @return [JwtUser] with user's data
   * @throws UserWithUsernameNotFoundException when no user with this username exists
   */
  override fun loadUserByUsername(username: String): JwtUser {
    val user = userRepository.findByUsername(username)
    val foundUser = user ?: throw UserWithUsernameNotFoundException(username)
    return foundUser.jwtUser()
  }

  /**
   * Retrieve all users registered in the system.
   */
  fun findAll(): List<JwtUser> {
    return userRepository.findAll()
      .map { it.jwtUser() }
  }

  /**
   * Register a new user.
   *
   * @param signUpRequest Payload with user details, containing username and password.
   * @return [UserCreatedResponse] after successful user sign-up
   * @throws UserAlreadyExistsException when there is already a user with this username.
   */
  fun registerNewUser(signUpRequest: SignUpRequest): UserCreatedResponse {
    val userWithTheSameUsername = userRepository.findAll()
      .find { it.username == signUpRequest.username }
    if (userWithTheSameUsername != null) {
      throw UserAlreadyExistsException()
    } else {
      val encryptedPassword = passwordEncoder.encode(signUpRequest.password)
      val user = UserFactory().createUser(signUpRequest, encryptedPassword, setOf(AuthorityName.ROLE_USER))
      val savedUser = userRepository.save(user)
      return UserCreatedResponse(savedUser.username)
    }
  }

  /**
   * Delete user if exists.
   *
   * @param username used for identify a user
   */
  fun deleteByUsername(username: String) {
    val userToDelete = userRepository.findByUsername(username)
    if (userToDelete != null) {
      val id = userToDelete.id
        ?: throw IllegalStateException("Unable to delete user ${userToDelete.username} because of missing id.")
      userRepository.deleteById(id)
    }
  }
}
