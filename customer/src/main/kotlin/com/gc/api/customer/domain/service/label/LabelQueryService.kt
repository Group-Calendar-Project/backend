package com.gc.api.customer.domain.service.label

import com.gc.api.customer.application.port.out.persistence.label.GetMemberLabelPort
import com.gc.api.customer.domain.model.label.EventLabel
import com.gc.api.customer.domain.model.member.Member
import org.springframework.stereotype.Service

@Service
class LabelQueryService(
  private val getMemberLabelPort: GetMemberLabelPort,
) {

  fun getLabels(member: Member): List<EventLabel> {
    // default label
    val defaultLabels: List<EventLabel> = getMemberLabelPort.getDefaultLabels()

    // custom label
    val customLabels: List<EventLabel> = getMemberLabelPort.getCustomLabels(member)

    val labelMap = defaultLabels.associateBy { it.id }.toMutableMap()

    customLabels.forEach { customLabel ->
      labelMap[customLabel.id] = customLabel
    }

    return labelMap.values.toList()

  }

  fun getLabel(memberId: String, labelId: String): EventLabel {
    return getMemberLabelPort.getLabel(memberId, labelId)
  }

  // Calendar 표시할 라벨 조회
  fun getAllEventLabel(memberId: String, labelIds: Set<String>): List<EventLabel> {

    return labelIds
      .map { getMemberLabelPort.getLabel(memberId, it) }
      .toList()
  }
}