package com.kazemieh.domain.model.location

import kotlinx.serialization.Serializable

@Serializable
enum class LocationScreenType {
    FromLogin,
    FromCreateAds
}