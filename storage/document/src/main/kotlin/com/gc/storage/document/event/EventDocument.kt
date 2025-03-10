package com.gc.storage.document.event

import com.gc.storage.document.common.BaseTimeEntity
import jakarta.persistence.Entity
import jakarta.persistence.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

@Document("events")
@Entity
data class EventDocument(
  val title: String,
  val startDateTime: LocalDateTime,
  val endDateTime: LocalDateTime,
  val isAllDay: Boolean,
  val labelId: String,
  val alarm: String,
  val frequency: String,
  val memberId: String,
  @Id
  val id: String? = null,
): BaseTimeEntity() {

}
