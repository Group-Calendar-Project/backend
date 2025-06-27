package com.gc.api.customer.adapter.`in`.rest.event

import com.gc.api.customer.adapter.`in`.dto.event.response.CalendarResponse
import com.gc.api.customer.adapter.`in`.dto.event.response.EventResponse
import com.gc.api.customer.adapter.`in`.dto.response.ResponseData
import com.gc.api.customer.application.service.dto.event.GetCalendarDto
import com.gc.api.customer.application.service.facade.event.EventFacade
import com.gc.api.customer.framework.annotation.RequestInfo
import com.gc.api.customer.utils.UrlConstant
import org.springframework.web.bind.annotation.*
import java.time.LocalDate

@RestController
@RequestMapping(UrlConstant.EVENT)
class EventController(
    private val eventFacade: EventFacade,
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
}