package common.exception

import com.gc.exception.CustomException
import org.springframework.http.HttpStatus

class CustomNotFoundException(message: String)
  : CustomException(
    httpStatusCode = HttpStatus.NOT_FOUND.value(),
    code = "COMMON404",
    message
  ){
}