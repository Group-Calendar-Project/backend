package api.customer.adapter.`in`.rest

import common.adapter.out.persistence.document.MemberDocument
import common.adapter.out.persistence.nosql.MemberMongoRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class MemberController(
  private val memberMongoRepository: MemberMongoRepository,
) {

  @PostMapping
  fun saveMember(): String {
    val newMember = MemberDocument(
      userName = "kylo",
      email = "kylo@kakao.com",
      isActive = true,
      isAdmin = true,
      profile = "kakao",
      oauthProvider = "kakao")
    val saveMember = memberMongoRepository.save(newMember)
    return saveMember.id!!
  }

  @GetMapping
  fun getMembers(): List<MemberDocument> {
    val results = memberMongoRepository.findAll()
    return results
  }
}