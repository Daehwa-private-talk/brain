package com.daehwa.gateway.filter

import com.daehwa.gateway.model.exception.DaehwaException
import com.daehwa.gateway.model.exception.FailureResponse
import com.daehwa.gateway.model.exception.Status
import org.springframework.cloud.gateway.filter.GatewayFilter
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory
import org.springframework.http.server.reactive.ServerHttpResponse
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

@Component
class ExceptionHandlerFilter : AbstractGatewayFilterFactory<ExceptionHandlerFilter.Config>(Config::class.java) {

    data class Config(val name: String = "ExceptionHandlerFilter")

    override fun apply(config: Config?): GatewayFilter {
        return GatewayFilter { exchange, chain ->
            val response = exchange.response

            chain.filter(exchange)
                .onErrorResume { throwable ->
                    handleException(response, throwable)
                }
        }
    }

    private fun handleException(response: ServerHttpResponse, throwable: Throwable): Mono<Void> {
        val error = throwable as DaehwaException

        val status = Status(error.errorCode.responseCode, error.message ?: "")
        val failureResponse = FailureResponse(status)

        response.statusCode = error.errorCode.responseCode.httpStatus
        val buffer = response.bufferFactory().wrap(failureResponse.convertToJson().toByteArray())

        return response.writeWith(Mono.just(buffer))
    }
}
