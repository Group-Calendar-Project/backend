package com.gc.api.customer.framework.exception

import com.gc.adapter.`in`.response.ResponseData
import com.gc.common.framework.exception.CustomBadRequestException
import com.gc.common.utils.logger
import org.apache.tomcat.websocket.AuthenticationException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.util.*

@RestControllerAdvice
class GlobalExceptionHandler {

  @ExceptionHandler(AuthenticationException::class)
  fun authenticationExceptionHandler(e: AuthenticationException): ResponseEntity<Any> {
    logger.info { "AuthenticationException: $e" }
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
      .body(ResponseData.error<Any>("COMMON401", "로그인이 필요합니다."))
  }

  @ExceptionHandler(AccessDeniedException::class)
  fun accessDeniedExceptionHandler(e: AccessDeniedException): ResponseEntity<Any> {
    logger.info {"AccessDeniedException: $e"}
    return ResponseEntity.status(HttpStatus.FORBIDDEN)
      .body(ResponseData.error<Any>("COMMON403", "해당 요청에 대한 권한이 없습니다."))
  }

  @ExceptionHandler(CustomBadRequestException::class)
  fun badRequestExceptionHandler(e: CustomBadRequestException): ResponseEntity<Any> {
    logger.info { "CustomBadRequestException $e" }
    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
      .body(ResponseData.error<Any>("COMMON400", e.message))
  }

  @ExceptionHandler(MethodArgumentNotValidException::class)
  fun methodArgumentNotValidException(e: MethodArgumentNotValidException): ResponseEntity<Any> {
    logger.info { "MethodArgumentNotValidException $e"}

    val fieldError: FieldError? = Objects.requireNonNull(e.bindingResult.fieldError)
    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
      .body(ResponseData.error<Any>("COMMON400",
        """
          Field ${fieldError?.field} 에서 에러 발생
          입력한 값: ${fieldError?.rejectedValue}
          에러 메시지: ${fieldError?.defaultMessage}
        """.trimIndent()))
  }

}