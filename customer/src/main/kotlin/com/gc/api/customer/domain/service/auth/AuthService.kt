package com.gc.api.customer.domain.service.auth

import com.gc.api.customer.adapter.out.external.dto.response.social_login.kakao.KakaoProfile
import com.gc.api.customer.adapter.out.external.dto.response.social_login.naver.NaverProfile
import com.gc.api.customer.adapter.out.external.social_login.kakao.KakaoClient
import com.gc.api.customer.adapter.out.external.social_login.naver.NaverClient
import org.springframework.stereotype.Service

@Service
class AuthService(
  private val kakaoClient: KakaoClient,
  private val naverClient: NaverClient,
) {

  fun kakaoLogin(accessCode: String): KakaoProfile {

    val kakaoOAuthToken = kakaoClient.getAccessToken(accessCode)
    val requestProfile = kakaoClient.getProfile(kakaoOAuthToken)

    return requestProfile
  }

  fun naverLogin(accessCode: String): NaverProfile {
    val naverOAuthToken = naverClient.getAccessToken(accessCode)
    val naverProfile = naverClient.getProfile(naverOAuthToken)

    return naverProfile
  }
}