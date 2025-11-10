package com.kazemieh.domain.model.category

data class Category(
    val name: String,
    val id: Long,
    val icon: String,
    val children: List<Category>
)