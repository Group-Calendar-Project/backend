package com.gc.api.customer.adapter.out.persistence.member

import com.gc.storage.document.member.MemberDocument
import com.gc.storage.document.member.QMemberDocument
import com.gc.storage.document.member.QMemberDocument.memberDocument
import org.springframework.data.mongodb.core.MongoOperations
import org.springframework.data.mongodb.repository.support.QuerydslRepositorySupport
import org.springframework.stereotype.Repository

@Repository
class GetMemberRepository(
  val operations: MongoOperations,
): QuerydslRepositorySupport(operations) {

  fun getMemberByUserName(): List<MemberDocument> {

    return from(memberDocument)
      .where(memberDocument.nickname.eq("kylo"))
      .fetch()

  }

  fun getMemberByEmailAndOauth(email: String, oauthProvider: String): MemberDocument? {
    return from(memberDocument)
      .where(memberDocument.email.eq(email)
        .and(memberDocument.oauthProvider.eq(oauthProvider)))
      .fetchOne()
  }
}
