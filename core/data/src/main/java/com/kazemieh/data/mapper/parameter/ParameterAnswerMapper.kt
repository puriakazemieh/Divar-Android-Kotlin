package com.kazemieh.data.mapper.parameter

import com.kazemieh.domain.model.parameter.ParameterAnswer
import com.kazemieh.network.dto.parameter.ParameterAnswerResponse

fun ParameterAnswerResponse.toDomain(): ParameterAnswer {
    return ParameterAnswer(
        answer = answer,
        parameter = parameter.toDomain()
    )
}