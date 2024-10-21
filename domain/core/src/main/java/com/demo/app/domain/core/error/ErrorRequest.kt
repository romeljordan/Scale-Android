package com.demo.app.domain.core.error

sealed interface ErrorRequest {
    data object InvalidRequest: ErrorRequest

    data object InvalidBody: ErrorRequest

    data class ServerErrorRequest(val message: String): ErrorRequest
}


class MissingResponseBodyException: Exception("Response has no body")
class ServerErrorException(message: String): Exception(message)
class FailedRequestException(message: String): Exception(message)
