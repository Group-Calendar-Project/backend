package com.gc.document.event

import com.gc.document.common.BaseTimeEntity
import jakarta.persistence.Entity
import jakarta.persistence.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

@Document("recurrence_events")
@Entity
data class RecurrenceEventDocument(
  @Id
  val id: String?,
  val eventId: String,
  val frequency: String,
  override val createdAt: LocalDateTime,
  override val updatedAt: LocalDateTime,
  ): BaseTimeEntity() {

}
