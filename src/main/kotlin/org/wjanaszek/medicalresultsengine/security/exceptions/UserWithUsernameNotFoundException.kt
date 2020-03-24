package org.wjanaszek.medicalresultsengine.security.domain

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.NOT_FOUND)
class UserWithUsernameNotFoundException(username: String) : RuntimeException("User with username $username not found.")
