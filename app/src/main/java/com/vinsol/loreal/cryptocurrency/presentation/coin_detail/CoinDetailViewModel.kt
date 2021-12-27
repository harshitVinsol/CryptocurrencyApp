package com.vinsol.loreal.cryptocurrency.presentation.coin_detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vinsol.loreal.cryptocurrency.common.Resource
import com.vinsol.loreal.cryptocurrency.domain.use_case.get_coin.GetCoinUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CoinDetailViewModel @Inject constructor(
    private val getCoinUseCase: GetCoinUseCase
) : ViewModel() {
    var coinDetailStateLiveData: MutableLiveData<CoinDetailState> = MutableLiveData()
        private set

    fun getCoin(coinId: String) {
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