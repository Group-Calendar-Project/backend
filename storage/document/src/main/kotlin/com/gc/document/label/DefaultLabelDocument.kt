package com.gc.document.label

import com.gc.document.common.BaseTimeEntity
import jakarta.persistence.Entity
import jakarta.persistence.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

@Document("default_labels")
@Entity
data class DefaultLabelDocument(
  @Id
  val id: String?,
  val label: String,
  val color: String,
  override val createdAt: LocalDateTime,
  override val updatedAt: LocalDateTime,
): BaseTimeEntity() {

}
