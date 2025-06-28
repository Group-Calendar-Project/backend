package com.gc.api.customer.adapter.`in`.dto.request.label

import com.gc.api.customer.application.service.dto.label.ChangeMemberLabelDto

data class ChangeLabelRequest(
    val labelName: String?,
    val labelColor: String?,
) {
    fun toServiceRequest(request: ChangeLabelRequest, labelId: String, memberId: String): ChangeMemberLabelDto {
        return ChangeMemberLabelDto(
            memberId,
            labelId,
            request.labelName,
            request.labelColor)
    }
}
