package com.vinsol.loreal.cryptocurrency.presentation.coin_list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vinsol.loreal.cryptocurrency.common.Resource
import com.vinsol.loreal.cryptocurrency.domain.use_case.get_coins.GetCoinsUseCase
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class CoinListViewModel(private val getCoinsUseCase: GetCoinsUseCase) : ViewModel() {
    var coinStateLiveData: MutableLiveData<CoinListState> = MutableLiveData()
        private set

    init {
        getCoins()
    }

    private fun getCoins() {
        getCoinsUseCase().onEach { result ->
            when (result) {
                is Resource.Success ->
                    coinStateLiveData.postValue(CoinListState(coins = result.data ?: emptyList()))

                is Resource.Error -> coinStateLiveData
                    .postValue(CoinListState(error = result.message
                        ?: "An unknown error occurred!"))

                is Resource.Loading -> coinStateLiveData.postValue(CoinListState(isLoading = true))
            }
        }.launchIn(viewModelScope)
    }
}