package com.gc.api.customer.domain.service.external.auth

data class KakaoOAuthToken(
  val access_token: String,
  val token_type: String,
  val refresh_token: String,
  val expires_in: Int,
  val scope: String,
  val refresh_token_expires_in: Int,
)
