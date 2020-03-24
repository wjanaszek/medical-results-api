package org.wjanaszek.medicalresultsengine.security.domain

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus
import java.lang.RuntimeException

@ResponseStatus(HttpStatus.NOT_FOUND)
class UserWithIdNotFoundException(id: String) : RuntimeException("User with id $id not found.")
