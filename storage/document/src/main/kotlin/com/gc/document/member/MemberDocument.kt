package com.gc.document.member

import com.gc.document.common.BaseTimeEntity
import jakarta.persistence.Entity
import jakarta.persistence.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

@Document(value = "members")
@Entity
data class MemberDocument(
  @Id
  val id: String?,
  val userName: String,
  val email: String,
  val isActive: Boolean,
  val isAdmin: Boolean,
  val profile: String,
  val oauthProvider: String,
  override val createdAt: LocalDateTime,
  override val updatedAt: LocalDateTime,
): BaseTimeEntity() {

}