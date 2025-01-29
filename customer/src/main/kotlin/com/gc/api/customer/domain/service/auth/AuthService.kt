package com.gc.api.customer.domain.service.auth

import com.gc.api.customer.domain.service.external.auth.KakaoClient
import com.gc.api.customer.domain.service.external.auth.KakaoProfile
import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Service

@Service
@RequiredArgsConstructor
class AuthService(
  val kakaoClient: KakaoClient,
) {

  fun kakaoLogin(accessCode: String?): KakaoProfile? {

    val kakaoOAuthToken = kakaoClient.requestAuthorizationToken(accessCode)
    val requestProfile = kakaoOAuthToken?.let { kakaoClient.requestProfile(it) }

    return requestProfile
  }
}