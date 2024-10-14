package ar.edu.unsam.algo3.errors

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus


@ResponseStatus(value = HttpStatus.FORBIDDEN)
class BadCredentailsError(errorMessage: String = "") : RuntimeException(errorMessage) {}