package com.example.numberchecker.data.remote

import com.example.numberchecker.data.remote.dto.NumberInfoDto
import retrofit2.http.GET
import retrofit2.http.Query

interface NumberApi {
    @GET("number_verification/validate")
    suspend fun checkNumberInfo(
        @Query("apikey") apiKey: String?  = "J09UWWjLBo9ms9Nz3NNmLUWsUNKBougQ",
        @Query("number") number: String,
    ): NumberInfoDto

    companion object  {
        const val BASE_URL = "https://api.apilayer.com/"
    }

}