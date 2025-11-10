package com.kazemieh.data.mapper.image

import com.kazemieh.domain.model.image.Image
import com.kazemieh.network.dto.image.ImageResponse

fun ImageResponse.toDomain(): Image {
    return Image(path = path)
}