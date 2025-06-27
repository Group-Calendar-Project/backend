package com.gc.api.customer.adapter.`in`.rest.event

import com.gc.api.customer.adapter.`in`.dto.event.request.PostEventRequest
import com.gc.api.customer.adapter.`in`.dto.event.request.UpdateEventRequest
import com.gc.api.customer.adapter.`in`.dto.event.response.CalendarResponse
import com.gc.api.customer.adapter.`in`.dto.event.response.EventResponse
import com.gc.api.customer.adapter.`in`.dto.response.ResponseData
import com.gc.api.customer.application.service.dto.event.GetCalendarDto
import com.gc.api.customer.application.service.facade.event.EventFacade
import com.gc.api.customer.domain.service.event.EventCommandService
import com.gc.api.customer.framework.annotation.RequestInfo
import com.gc.api.customer.framework.annotation.RequireAuth
import com.gc.api.customer.utils.UrlConstant
import org.springframework.web.bind.annotation.*
import java.time.LocalDate

@RestController
@RequestMapping(UrlConstant.EVENT)
class EventController(
    private val eventFacade: EventFacade,
    private val eventCommandService: EventCommandService,
    private val requestInfo: RequestInfo,
) {

    @GetMapping("/{eventId}")
    fun getEvent(@PathVariable eventId: String): ResponseData<EventResponse> {
        return ResponseData.success(eventFacade.getEvent(eventId))
    }

    @GetMapping
    fun getEvents(
        @RequestParam startDate: LocalDate,
        @RequestParam endDate: LocalDate?,
    ): ResponseData<List<CalendarResponse>> {
        val getCalenderDto = GetCalendarDto.of(requestInfo.member.id, startDate, endDate)
        val calendarEvents = eventFacade.getCalendar(getCalenderDto)
        return ResponseData.success(CalendarResponse.toCalendarResponse(calendarEvents))
    }

    @PostMapping
    @RequireAuth
    fun saveEvent(@RequestBody postEventRequest: PostEventRequest): ResponseData<String> {
        val eventServiceRequest =
            postEventRequest.toEventServiceRequest(postEventRequest, requestInfo.member)
        return ResponseData.success(eventCommandService.addEvent(eventServiceRequest))
    }

    @PatchMapping("/{eventId}")
    @RequireAuth
    fun updateEvent(
        @PathVariable eventId: String,
        @RequestBody updateEventRequest: UpdateEventRequest,
    ): ResponseData<String> {
        eventCommandService.updateEvent(updateEventRequest.toServiceRequest(eventId))
        return ResponseData.success(eventId)
    }

    @DeleteMapping("/{eventId}")
    @RequireAuth
    fun deleteEvent(@PathVariable eventId: String): ResponseData<String> {
        eventCommandService.deleteEvent(eventId)
        return ResponseData.success(eventId)
    }
}