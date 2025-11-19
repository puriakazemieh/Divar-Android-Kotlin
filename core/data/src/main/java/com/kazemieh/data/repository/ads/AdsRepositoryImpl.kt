package com.kazemieh.data.repository.ads

import com.kazemieh.data.mapper.ads.toDomain
import com.kazemieh.data.mapper.ads.toRequest
import com.kazemieh.data.utils.safeCall
import com.kazemieh.data.utils.toJson
import com.kazemieh.domain.model.DataResult
import com.kazemieh.domain.model.NullRequestBody
import com.kazemieh.domain.model.ads.Ads
import com.kazemieh.domain.model.ads.CreateAdsParam
import com.kazemieh.domain.model.onFailure
import com.kazemieh.domain.model.onSuccess
import com.kazemieh.domain.repository.ads.AdsRepository
import com.kazemieh.network.api.ads.AdsApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File
import javax.inject.Inject

class AdsRepositoryImpl @Inject constructor(
    private val apiService: AdsApiService
) : AdsRepository {
    override suspend fun getAdsDetail(id: Long): Flow<DataResult<Ads>> = flow {
        safeCall {
            apiService.getAdsDetail(id)
        }.onSuccess { data ->
            emit(DataResult.Success(data.toDomain()))
        }.onFailure {
            emit(DataResult.Failure(it))
        }
    }
    override suspend fun createAds(createAdsParam: CreateAdsParam): Flow<DataResult<Unit>> = flow {
        val images: MutableList<MultipartBody.Part> = mutableListOf()
        createAdsParam.images.filter { it.isNotEmpty() }.forEachIndexed { index, path ->
            images.add(
                MultipartBody.Part.createFormData(
                    name = "images",
                    filename = path.substring(path.lastIndexOf("/") + 1),
                    body = File(path).asRequestBody("image/*".toMediaTypeOrNull())
                )
            )
        }

        val adsRequest = createAdsParam.toRequest().toJson()?.toRequestBody("text/plain".toMediaTypeOrNull())

        if (adsRequest == null) {
            emit(DataResult.Failure(NullRequestBody()))
        } else {
            safeCall {
                apiService.createAds(
                    images = images,
                    adsRequest = adsRequest
                )
            }.onSuccess {
                emit(DataResult.Success(Unit))
            }.onFailure {
                emit(DataResult.Failure(it))
            }
        }

    }

}