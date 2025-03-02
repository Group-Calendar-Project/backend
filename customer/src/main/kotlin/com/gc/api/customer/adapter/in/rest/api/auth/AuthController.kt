package com.gc.api.customer.adapter.`in`.rest.api.auth

import com.gc.adapter.`in`.response.ResponseData
import com.gc.api.customer.application.service.facade.MemberFacade
import com.gc.api.customer.domain.service.auth.AuthService
import com.gc.api.customer.framework.security.TokenDto
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class AuthController(
  val authService: AuthService,
  val memberFacade: MemberFacade,
) {

  @GetMapping("/login/kakao")
  fun kakaoLogin(@RequestParam("code") accessCode: String) : ResponseData<TokenDto> {
    val kakaoProfile = authService.kakaoLogin(accessCode)
    return ResponseData.success(memberFacade.loginOrJoin(kakaoProfile))
  }

  @GetMapping("/login/naver")
  fun naverLogin(@RequestParam("code") accessCode: String) : ResponseData<TokenDto> {
    val naverProfile = authService.naverLogin(accessCode)
    return ResponseData.success(memberFacade.loginOrJoin(naverProfile))
  }
}