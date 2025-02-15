package com.gc.document.group

import com.gc.document.common.BaseTimeEntity
import jakarta.persistence.Entity
import jakarta.persistence.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

@Document("groups")
@Entity
data class GroupDocument(
  @Id
  val id: String?,
  val name: String,
  val profile: String,
  val description: String,
  val link: String,
  override val createdAt: LocalDateTime,
  override val updatedAt: LocalDateTime,
): BaseTimeEntity()
