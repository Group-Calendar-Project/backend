package com.gc.document.common

import jakarta.persistence.EntityListeners
import jakarta.persistence.MappedSuperclass
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class BaseTimeEntity {

  @get:CreatedDate
  open val createdAt: LocalDateTime = LocalDateTime.now()

  @LastModifiedDate
  open var updatedAt: LocalDateTime = LocalDateTime.now()
}