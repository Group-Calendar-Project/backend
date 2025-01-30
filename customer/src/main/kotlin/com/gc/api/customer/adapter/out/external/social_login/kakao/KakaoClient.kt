package com.gc.api.customer.adapter.out.external.social_login.kakao

import com.gc.api.customer.adapter.out.external.dto.response.social_login.OAuthToken
import com.gc.api.customer.adapter.out.external.dto.response.social_login.kakao.KakaoOAuthToken
import com.gc.api.customer.adapter.out.external.dto.response.social_login.kakao.KakaoProfile
import com.gc.api.customer.application.port.out.external.social_login.SocialLoginPort
import common.exception.CustomBadRequestException
import lombok.RequiredArgsConstructor
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.util.LinkedMultiValueMap
import org.springframework.web.client.RestTemplate

@Component
@RequiredArgsConstructor
class KakaoClient(
  @Value("\${kakao.auth.client}") private val client: String,
  @Value("\${kakao.auth.redirect}") private val redirect: String,
  private val restTemplate: RestTemplate,
): SocialLoginPort {

  override fun getAuthorizationToken(accessCode: String?): KakaoOAuthToken {

    // TODO
    val headers = HttpHeaders().apply {
      add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    }

    // TODO
    val params = LinkedMultiValueMap<String, String>().apply {
      add("grant_type", "authorization_code")
      add("client_id", client)
      add("redirect_uri", redirect)
      add("code", accessCode)
    }

    val kakaoTokenRequest = HttpEntity(params, headers)

    val response = restTemplate.exchange(
      "https://kauth.kakao.com/oauth/token",
      HttpMethod.POST,
      kakaoTokenRequest,
      KakaoOAuthToken::class.java
    )

    return response.body ?: throw CustomBadRequestException("잘못된 인가코드 입니다.")
  }

  override fun getProfile(oAuthToken: OAuthToken): KakaoProfile {

    val headers = HttpHeaders().apply {
      add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE)
      add("Authorization", "Bearer " + oAuthToken.accessToken)
    }

    val httpEntity = HttpEntity<Void>(headers)
    val response = restTemplate.exchange(
      "https://kapi.kakao.com/v2/user/me",
      HttpMethod.GET,
      httpEntity,
      KakaoProfile::class.java
    )

    return response.body ?: throw CustomBadRequestException("잘못된 인증코드 입니다.")
  }


}