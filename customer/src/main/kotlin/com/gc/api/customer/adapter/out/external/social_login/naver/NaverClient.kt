package com.gc.api.customer.adapter.out.external.social_login.naver

import com.gc.api.customer.adapter.out.external.dto.response.social_login.OAuthToken
import com.gc.api.customer.adapter.out.external.dto.response.social_login.naver.NaverOAuthToken
import com.gc.api.customer.adapter.out.external.dto.response.social_login.naver.NaverProfile
import com.gc.api.customer.application.port.out.external.social_login.SocialLoginPort
import common.exception.CustomBadRequestException
import lombok.RequiredArgsConstructor
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.util.LinkedMultiValueMap
import org.springframework.web.client.RestTemplate

@Service
@RequiredArgsConstructor
class NaverClient(
  @Value("\${naver.auth.client_id}") private val clientId: String,
  @Value("\${naver.auth.client_secret}") private val clientSecret: String,
  private val restTemplate: RestTemplate,
): SocialLoginPort {

  override fun getAuthorizationToken(accessCode: String?): NaverOAuthToken {
    val headers = HttpHeaders().apply {
      add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    }

    // TODO
    val params = LinkedMultiValueMap<String, String>().apply {
      add("grant_type", "authorization_code")
      add("client_id", clientId)
      add("client_secret", clientSecret)
      add("code", accessCode)
      add("state", "STATE_STRING")
    }

    val naverTokenRequest = HttpEntity(params, headers)

    val response = restTemplate.exchange(
      "https://nid.naver.com/oauth2.0/token",
      HttpMethod.POST,
      naverTokenRequest,
      NaverOAuthToken::class.java
    )

    return response.body ?: throw CustomBadRequestException("잘못된 인가 코드입니다.")
  }

  override fun getProfile(oAuthToken: OAuthToken): NaverProfile {
    val headers = HttpHeaders().apply {
      add("Authorization", "Bearer " + oAuthToken.accessToken)
    }

    val httpEntity = HttpEntity<Void>(headers)
    val response = restTemplate.exchange(
      "https://openapi.naver.com/v1/nid/me",
      HttpMethod.POST,
      httpEntity,
      NaverProfile::class.java
    )

    return response.body ?: throw CustomBadRequestException("잘못된 인증 코드입니다.")
  }

}