package com.gc.api.customer.application.service.dto.event

import com.gc.api.customer.domain.model.EventAlarm
import com.gc.api.customer.domain.model.event.EventFrequency
import java.time.LocalDate
import java.time.LocalTime

data class PostEventDto(
  val title: String,
  val date: LocalDate,
  val startTime: LocalTime?,
  val endTime: LocalTime?,
  val isAllDay: Boolean,
  val alarm: EventAlarm,
  val labelId: String,
  val frequency: EventFrequency,
  val memberId: String,
)
