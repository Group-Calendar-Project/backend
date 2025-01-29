package com.gc.api.customer.domain.service.auth

import com.gc.api.customer.domain.service.external.auth.KakaoClient
import com.gc.api.customer.domain.service.external.auth.KakaoProfile
import com.gc.api.customer.domain.service.external.auth.NaverClient
import com.gc.api.customer.domain.service.external.auth.NaverProfile
import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Service

@Service
@RequiredArgsConstructor
class AuthService(
  val kakaoClient: KakaoClient,
  val naverClient: NaverClient,
) {

  fun kakaoLogin(accessCode: String?): KakaoProfile? {

    val kakaoOAuthToken = kakaoClient.requestAuthorizationToken(accessCode)
    val requestProfile = kakaoOAuthToken?.let { kakaoClient.requestProfile(it) }

    return requestProfile
  }

  fun naverLogin(accessCode: String?): NaverProfile? {
    val naverOAuthToken = naverClient.requestAuthorizationToken(accessCode)
    val naverProfile = naverOAuthToken?.let { naverClient.requestProfile(it) }

    return naverProfile
  }
}