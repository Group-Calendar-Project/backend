package com.gc.api.customer.domain.service.member

import com.gc.api.customer.adapter.out.external.dto.response.social_login.OAuthProfile
import com.gc.api.customer.adapter.out.persistence.member.MemberMapper
import com.gc.storage.document.member.MemberDocument
import com.gc.storage.document.member.MemberMongoRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class MemberCommandService(
  val memberMongoRepository: MemberMongoRepository,
) {

  fun singUp(oAuthProfile: OAuthProfile): MemberDocument {
    val memberDocument = MemberMapper.from(oAuthProfile)
    return memberMongoRepository.save(memberDocument)
  }
}