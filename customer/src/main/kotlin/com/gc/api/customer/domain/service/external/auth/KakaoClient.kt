package com.gc.api.customer.domain.service.external.auth

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
  @Value("\${kakao.auth.client}") client: String,
  @Value("\${kakao.auth.redirect}") redirect: String,
  private val restTemplate: RestTemplate,
) {

  val kakaoClient = client
  val kakaoRedircet = redirect

  // 인가 코드 요청
  fun requestAuthorizationToken(accessCode: String?): KakaoOAuthToken? {
    // TODO
    val headers = HttpHeaders().apply {
      add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    }

    // TODO
    val params = LinkedMultiValueMap<String, String>().apply {
      add("grant_type", "authorization_code")
      add("client_id", kakaoClient)
      add("redirect_uri", kakaoRedircet)
      add("code", accessCode)
    }

    val kakaoTokenRequest = HttpEntity(params, headers)

    val response = restTemplate.exchange(
      "https://kauth.kakao.com/oauth/token",
      HttpMethod.POST,
      kakaoTokenRequest,
      KakaoOAuthToken::class.java
    )

    return response.body

  }

  fun requestProfile(kakaoOAuthToken: KakaoOAuthToken): KakaoProfile? {
    val headers = HttpHeaders().apply {
      add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE)
      add("Authorization", "Bearer " + kakaoOAuthToken.accessToken)
    }

    val httpEntity = HttpEntity<Void>(headers)
    val response = restTemplate.exchange(
      "https://kapi.kakao.com/v2/user/me",
      HttpMethod.GET,
      httpEntity,
      KakaoProfile::class.java
    )

    return response.body
  }

}