package com.kazemieh.domain.model.ads

import com.kazemieh.domain.model.image.Image
import com.kazemieh.domain.model.neighborhood.Neighborhood

data class AdsSummary(
    val id: Long,
    val title: String,
    val price: String,
    val neighborhood: Neighborhood,
    val previewImage: Image?,
    val createAt: String? = null,
)