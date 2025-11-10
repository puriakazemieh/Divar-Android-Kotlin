package com.kazemieh.data.mapper.paginate

import com.kazemieh.domain.model.paginate.Paging
import com.kazemieh.network.dto.paginate.PagingResponse

fun <T, R> PagingResponse<T>.toDomain(contentMapper: (T) -> R): Paging<R> {
    return Paging(
        content = contentMapper(content),
        totalPage = totalPage,
        totalElements = totalElements,
        isFirst = isFirst,
        isLast = isLast
    )
}
