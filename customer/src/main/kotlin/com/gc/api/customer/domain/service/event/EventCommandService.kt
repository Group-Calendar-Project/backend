package com.gc.api.customer.domain.service.event

import com.gc.api.customer.application.port.out.persistence.event.PostEventPort
import com.gc.api.customer.application.port.out.persistence.event.UpdateEventPort
import com.gc.api.customer.application.service.dto.event.EventServiceRequest
import com.gc.api.customer.application.service.dto.event.UpdateEventDto
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class EventCommandService(
  private val postEventPort: PostEventPort,
  private val updateEventPort: UpdateEventPort,
) {

  fun addEvent(eventServiceRequest: EventServiceRequest): String {
    return postEventPort.saveEvent(eventServiceRequest)
  }

  fun updateEvent(updateEventDto: UpdateEventDto) {
    updateEventPort.updateEvent(updateEventDto)
  }
}