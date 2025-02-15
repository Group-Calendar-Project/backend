package com.gc.document.device_token

import com.gc.document.common.BaseTimeEntity
import jakarta.persistence.Entity
import jakarta.persistence.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

@Document("device_tokens")
@Entity
data class DeviceTokenDocument(
  @Id
  val id: String?,
  val memberId: String,
  val deviceToken: String,
  override val createdAt: LocalDateTime,
  override val updatedAt: LocalDateTime,
  ): BaseTimeEntity() {

}
