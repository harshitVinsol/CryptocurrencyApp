package com.vinsol.loreal.cryptocurrency.presentation.coin_detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vinsol.loreal.cryptocurrency.common.Constants.PARAM_COIN_ID
import com.vinsol.loreal.cryptocurrency.common.Resource
import com.vinsol.loreal.cryptocurrency.domain.use_case.get_coin.GetCoinUseCase
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class CoinDetailViewModel(
    private val getCoinUseCase: GetCoinUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    var coinDetailStateLiveData: MutableLiveData<CoinDetailState> = MutableLiveData()
        private set

    init {
        savedStateHandle.get<String>(PARAM_COIN_ID)?.let { coinId ->
            getCoin(coinId)
        }
    }

    private fun getCoin(coinId: String) {
        getCoinUseCase(coinId).onEach { result ->
            when (result) {
                is Resource.Success ->
                    coinDetailStateLiveData.postValue(CoinDetailState(coinDetail = result.data))

                is Resource.Error -> coinDetailStateLiveData
                    .postValue(
                        CoinDetailState(
                            error = result.message
                                ?: "An unknown error occurred!"
                        )
                    )

                is Resource.Loading -> coinDetailStateLiveData.postValue(CoinDetailState(isLoading = true))
            }
        }.launchIn(viewModelScope)
    }
}