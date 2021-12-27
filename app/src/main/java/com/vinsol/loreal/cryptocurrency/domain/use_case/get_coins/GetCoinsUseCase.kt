package com.vinsol.loreal.cryptocurrency.domain.use_case.get_coins

import com.vinsol.loreal.cryptocurrency.common.Resource
import com.vinsol.loreal.cryptocurrency.data.remote.dto.toCoin
import com.vinsol.loreal.cryptocurrency.domain.model.Coin
import com.vinsol.loreal.cryptocurrency.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCoinsUseCase @Inject constructor(
    private val repository: CoinRepository
){
    operator fun invoke(): Flow<Resource<List<Coin>>> = flow {
        try {
            emit(Resource.Loading())
            val coins = repository.getCoins().map { it.toCoin() }
            emit(Resource.Success(coins))
        } catch (e: HttpException) {
            emit(Resource.Error(message = e.localizedMessage ?: "An unexpected error!"))
        } catch (e: IOException) {
            emit(Resource.Error(message = "Couldn't reach server. Please try again!"))
        }
    }
}