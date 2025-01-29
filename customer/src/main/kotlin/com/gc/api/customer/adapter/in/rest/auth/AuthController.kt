package com.gc.api.customer.adapter.`in`.rest.auth

import com.gc.api.customer.domain.service.auth.AuthService
import com.gc.api.customer.domain.service.external.auth.KakaoProfile
import com.gc.api.customer.domain.service.external.auth.NaverProfile
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class AuthController(
  val authService: AuthService,
) {

  @GetMapping("/login/kakao")
  fun kakaoLogin(@RequestParam("code") accessCode: String?) : KakaoProfile? {

    return authService.kakaoLogin(accessCode)
  }

  @GetMapping("/login/naver")
  fun naverLogin(@RequestParam("code") accessCode: String?) : NaverProfile? {

    println(accessCode)
    return authService.naverLogin(accessCode)
  }
}