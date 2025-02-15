package com.gc.document.share_event

import com.gc.document.common.BaseTimeEntity
import jakarta.persistence.Entity
import jakarta.persistence.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

@Document("member_share_events")
@Entity
data class MemberShareEventDocument(
  @Id
  val id: String?,
  val eventId: String,
  val groupPromiseId: String,
  override val createdAt: LocalDateTime,
  override val updatedAt: LocalDateTime,
): BaseTimeEntity()
