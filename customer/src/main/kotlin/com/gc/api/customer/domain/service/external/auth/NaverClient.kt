package com.gc.api.customer.domain.service.external.auth

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
  @Value("\${naver.auth.client_id}") clientId: String,
  @Value("\${naver.auth.client_secret}") clientSecret: String,
  private val restTemplate: RestTemplate,
) {

  val naverClientId = clientId
  val naverClientSecret = clientSecret


  fun requestAuthorizationToken(accessToken: String?): NaverOAuthToken? {

    val headers = HttpHeaders().apply {
      add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    }

    // TODO
    val params = LinkedMultiValueMap<String, String>().apply {
      add("grant_type", "authorization_code")
      add("client_id", naverClientId)
      add("client_secret", naverClientSecret)
      add("code", accessToken)
      add("state", "STATE_STRING")
    }

    val naverTokenRequest = HttpEntity(params, headers)

    val response = restTemplate.exchange(
      "https://nid.naver.com/oauth2.0/token",
      HttpMethod.POST,
      naverTokenRequest,
      NaverOAuthToken::class.java
    )

    return response.body
  }

  fun requestProfile(naverOAuthToken: NaverOAuthToken): NaverProfile? {
    val headers = HttpHeaders().apply {
      add("Authorization", "Bearer " + naverOAuthToken.accessToken)
    }

    val httpEntity = HttpEntity<Void>(headers)
    val response = restTemplate.exchange(
      "https://openapi.naver.com/v1/nid/me",
      HttpMethod.POST,
      httpEntity,
      NaverProfile::class.java
    )

    return response.body
  }
}