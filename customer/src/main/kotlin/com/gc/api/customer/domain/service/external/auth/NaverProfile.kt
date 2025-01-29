package com.gc.api.customer.domain.service.external.auth

import com.fasterxml.jackson.annotation.JsonProperty

data class NaverProfile(
  val response: NaverProfileResponse,
)

data class NaverProfileResponse(
  @JsonProperty("id") val id: String,
  @JsonProperty("email")val email: String,
  @JsonProperty("nickname") val nickname: String,
  @JsonProperty("profile_image") val profileImage: String,
)
