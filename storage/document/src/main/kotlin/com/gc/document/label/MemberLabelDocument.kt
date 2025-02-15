package com.gc.document.label

import jakarta.persistence.Entity
import jakarta.persistence.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document("member_labels")
@Entity
data class MemberLabelDocument(
  @Id
  val id: String?,
  val memberId: String,
  val labelId: String,
  val label: String,
  val color: String,
)
