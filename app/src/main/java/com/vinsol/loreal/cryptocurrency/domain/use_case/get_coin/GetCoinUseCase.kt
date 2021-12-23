package com.vinsol.loreal.cryptocurrency.domain.use_case.get_coin

import com.vinsol.loreal.cryptocurrency.common.Resource
import com.vinsol.loreal.cryptocurrency.data.remote.dto.toCoinDetail
import com.vinsol.loreal.cryptocurrency.domain.model.CoinDetail
import com.vinsol.loreal.cryptocurrency.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class GetCoinUseCase(private val repository: CoinRepository) {
    operator fun invoke(coinId: String): Flow<Resource<CoinDetail>> = flow {
        try {
            emit(Resource.Loading())
            val coin = repository.getCoinById(coinId).toCoinDetail()
            emit(Resource.Success(coin))
        } catch (e: HttpException) {
            emit(Resource.Error(message = e.localizedMessage ?: "An unexpected error!"))
        } catch (e: IOException) {
            emit(Resource.Error(message = "Couldn't reach server. Please try again!"))
        }
    }
}