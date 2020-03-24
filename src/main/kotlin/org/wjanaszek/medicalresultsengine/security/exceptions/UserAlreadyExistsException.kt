package org.wjanaszek.medicalresultsengine.security.domain

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.CONFLICT)
class UserAlreadyExistsException : RuntimeException("There is already a user with such username.")
