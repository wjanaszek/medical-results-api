package org.wjanaszek.medicalresultsengine.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.wjanaszek.medicalresultsengine.security.domain.AuthenticationFacade
import org.wjanaszek.medicalresultsengine.security.domain.AuthenticationFacadeImpl
import org.wjanaszek.medicalresultsengine.security.domain.UserRepository
import org.wjanaszek.medicalresultsengine.security.domain.UsersFacade

@Configuration
internal class SecurityConfiguration {
  @Bean
  fun authenticationFacade(
    authenticationManager: AuthenticationManager,
    usersFacade: UsersFacade
  ): AuthenticationFacade {
    return AuthenticationFacadeImpl(authenticationManager, usersFacade)
  }

  @Bean
  fun usersFacade(userRepository: UserRepository, passwordEncoder: PasswordEncoder): UsersFacade {
    return UsersFacade(userRepository, passwordEncoder)
  }

  @Bean
  fun passwordEncoder(): PasswordEncoder {
    return BCryptPasswordEncoder()
  }
}
