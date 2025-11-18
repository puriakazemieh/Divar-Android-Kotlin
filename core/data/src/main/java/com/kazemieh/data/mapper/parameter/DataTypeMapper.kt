package com.kazemieh.data.mapper.parameter

import com.kazemieh.domain.model.parameter.DataType
import com.kazemieh.network.dto.parameter.DataTypeResponse

fun DataTypeResponse.toDomain(): DataType {
    return when (this) {
        DataTypeResponse.StringInput -> DataType.StringInput
        DataTypeResponse.NumberInput -> DataType.NumberInput
        DataTypeResponse.FloatInput -> DataType.FloatInput
        DataTypeResponse.CheckBoxInput -> DataType.CheckBoxInput
        DataTypeResponse.FixedOption -> DataType.FixedOption
    }
}