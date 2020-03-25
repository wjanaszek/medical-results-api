package org.wjanaszek.medicalresultsengine.result.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus
import java.lang.RuntimeException

@ResponseStatus(HttpStatus.NOT_FOUND)
class ResultInfoNotFoundException(id: Long) : RuntimeException("Result info with id: \"${id}\" not found.")
