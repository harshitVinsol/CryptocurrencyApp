package com.vinsol.loreal.cryptocurrency.common

import com.vinsol.loreal.cryptocurrency.common.Constants.BASE_URL
import com.vinsol.loreal.cryptocurrency.data.remote.CoinPaprikaApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object AppModule {
    fun providePaprikaApi(): CoinPaprikaApi {
        return Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
            .build().create(CoinPaprikaApi::class.java)
    }
}