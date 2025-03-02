package com.gc.api.customer.domain.service.member

import com.gc.api.customer.adapter.out.persistence.member.GetMemberRepository
import com.gc.storage.document.member.MemberDocument
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class MemberQueryService(
  private val getMemberRepository: GetMemberRepository,
) {

  fun getMemberByEmail(email: String, oauthProvider: String) : MemberDocument? {
    return getMemberRepository.getMemberByEmailAndOauth(email, oauthProvider)
  }
}