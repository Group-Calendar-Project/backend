package com.gc.api.customer.framework.security

import org.springframework.security.core.GrantedAuthority

enum class CustomAuthority: GrantedAuthority {
  MEMBER,
  ADMIN,
  ;

  override fun getAuthority(): String {
    return name
  }
}