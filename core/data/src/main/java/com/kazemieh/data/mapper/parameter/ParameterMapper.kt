package com.kazemieh.data.mapper.parameter

import com.kazemieh.domain.model.parameter.Parameter
import com.kazemieh.network.dto.ads.ParameterAnswerRequest
import com.kazemieh.network.dto.parameter.ParameterResponse

fun ParameterResponse.toDomain(): Parameter {
    return Parameter(
        id = id,
        name = name,
        dataType = dataType.toDomain(),
        acceptedOptions = acceptedOptions,
    )
}

fun Parameter.toAnswerRequest(): ParameterAnswerRequest? {
    if (answer.isNullOrEmpty()) return null
    return ParameterAnswerRequest(
        answer = answer ?: "", parameterId = id
    )
}


