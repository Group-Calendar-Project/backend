package com.gc.document.group_event

import com.gc.document.common.BaseTimeEntity
import jakarta.persistence.Entity
import jakarta.persistence.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

@Document("group_events")
@Entity
data class GroupEventDocument(
  @Id
  val id: String?,
  val groupPromiseId: String,
  val isAllDay: Boolean,
  val date: LocalDate,
  val startTime: LocalTime?,
  val endTime: LocalTime?,
  override val createdAt: LocalDateTime,
  override val updatedAt: LocalDateTime
): BaseTimeEntity()
