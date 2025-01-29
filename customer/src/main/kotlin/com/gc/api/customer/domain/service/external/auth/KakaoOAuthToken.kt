package com.gc.api.customer.domain.service.external.auth

import com.fasterxml.jackson.annotation.JsonProperty

data class KakaoOAuthToken(
  @JsonProperty("access_token") val accessToken: String,
  @JsonProperty("token_type") val tokenType: String,
  @JsonProperty("refresh_token") val refreshToken: String,
  @JsonProperty("expires_in") val expiresIn: Int,
  @JsonProperty("scope") val scope: String,
  @JsonProperty("refresh_token_expires_in") val refreshTokenExpiresIn: Int,
)
