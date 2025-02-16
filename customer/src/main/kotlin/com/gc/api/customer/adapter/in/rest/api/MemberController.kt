package com.gc.api.customer.adapter.`in`.rest.api

import com.gc.api.customer.adapter.`in`.rest.dto.MemberRequest
import com.gc.api.customer.adapter.out.persistence.GetMemberRepository
import com.gc.document.member.MemberDocument
import com.gc.document.member.MemberMongoRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class MemberController(
  private val memberMongoRepository: MemberMongoRepository,
  private val getMemberRepository: GetMemberRepository,
) {

  @PostMapping
  fun saveMember(
    @RequestBody memberRequest: MemberRequest
  ): String {
    val newMember = MemberDocument(
      nickname = "kylo",
      email = "kylo@kakao.com",
      isActive = true,
      isAdmin = true,
      profile = "kakao",
      oauthProvider = "kakao",
      isNotificationEnabled = true,
      isMondayStart = false)
    val saveMember = memberMongoRepository.save(newMember)
    return saveMember.id!!
  }

  @GetMapping
  fun getMembers(): List<MemberDocument> {
    val results = memberMongoRepository.findAll()
    return results
  }

  @GetMapping("/test")
  fun getUserNames():List<MemberDocument> {
    return getMemberRepository.getMemberByUserName()
  }
}