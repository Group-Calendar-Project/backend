package com.gc.api.customer.adapter.`in`.rest.auth

import com.gc.api.customer.domain.service.auth.AuthService
import com.gc.api.customer.domain.service.external.auth.KakaoProfile
import jakarta.servlet.http.HttpServletResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class AuthController(
  val authService: AuthService,
) {

  @GetMapping("/login/kakao")
  fun kakaoLogin(@RequestParam("code") accessCode: String?,
                 httpServletResponse: HttpServletResponse) : KakaoProfile? {

    return authService.kakaoLogin(accessCode)
  }
}