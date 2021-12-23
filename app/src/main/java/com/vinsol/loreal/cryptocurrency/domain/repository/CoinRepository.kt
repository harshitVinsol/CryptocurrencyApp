package com.vinsol.loreal.cryptocurrency.domain.repository

import com.vinsol.loreal.cryptocurrency.data.remote.dto.CoinDetailDto
import com.vinsol.loreal.cryptocurrency.data.remote.dto.CoinDto

interface CoinRepository {

    suspend fun getCoins(): List<CoinDto>
    suspend fun getCoinById(coinId: String): CoinDetailDto

}