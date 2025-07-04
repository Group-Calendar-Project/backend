package com.gc.api.customer.framework.security

import com.gc.api.customer.domain.model.member.Member
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority

class CustomAuthentication(
    val member: Member
) : Authentication {
    override fun getName(): String {
        return member.nickname
    }

    override fun getAuthorities(): Collection<GrantedAuthority> {
        return if (member != null) listOf(CustomAuthority.MEMBER) else emptyList()
    }

    override fun getCredentials(): Any {
        throw UnsupportedOperationException()
    }

    override fun getDetails(): Any {
        throw UnsupportedOperationException()
    }

    override fun getPrincipal(): Member {
        return member
    }

    override fun isAuthenticated(): Boolean {
        return principal != null
    }

    override fun setAuthenticated(isAuthenticated: Boolean) {
        throw UnsupportedOperationException()
    }
}