package org.wjanaszek.medicalresultsengine.security.domain

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.BAD_REQUEST)
class WrongCredentialsException : RuntimeException("Wrong credentials.")
