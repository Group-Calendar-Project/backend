package com.gc.document.group

import com.gc.document.common.BaseTimeEntity
import jakarta.persistence.Entity
import jakarta.persistence.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

@Document("group_members")
@Entity
data class GroupMemberDocument(
  @Id
  val id: String?,
  val memberId: String,
  val nickname: String,
  val profile: String?,
  val groupId: String,
  override val createdAt: LocalDateTime,
  override val updatedAt: LocalDateTime,
): BaseTimeEntity()
