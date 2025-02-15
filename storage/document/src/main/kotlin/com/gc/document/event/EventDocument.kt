package com.gc.document.event

import com.gc.document.common.BaseTimeEntity
import jakarta.persistence.Entity
import jakarta.persistence.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

@Document("events")
@Entity
data class EventDocument(
  @Id
  val id: String?,
  val title: String,
  val date: LocalDate,
  val startTime: LocalTime?,
  val endTime: LocalTime?,
  val isAllDay: Boolean,
  val labelId: String,
  val alarm: String,
  val memberId: String,
  override val createdAt: LocalDateTime,
  override val updatedAt: LocalDateTime,
): BaseTimeEntity() {

}
