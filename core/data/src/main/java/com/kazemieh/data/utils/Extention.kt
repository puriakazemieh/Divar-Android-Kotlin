package com.kazemieh.data.utils

import kotlinx.serialization.json.Json


val json = Json { ignoreUnknownKeys = true }

inline fun <reified T> T?.toJson(): String? {
    return if (this == null) return null
    else json.encodeToString(this)
}

inline fun <reified T> String?.fromJson(): T? {
    return if (this.isNullOrEmpty()) return null
    else json.decodeFromString(this)
}
