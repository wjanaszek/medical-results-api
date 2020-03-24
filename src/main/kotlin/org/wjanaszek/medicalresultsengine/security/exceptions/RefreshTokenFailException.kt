package org.wjanaszek.medicalresultsengine.security.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.BAD_REQUEST)
internal class RefreshTokenFailException : RuntimeException("Failed to refresh token.")
