package com.vinsol.loreal.cryptocurrency.presentation.coin_list

import com.vinsol.loreal.cryptocurrency.domain.model.Coin

data class CoinListState(val isLoading : Boolean = false, val coins: List<Coin> = emptyList(), val error: String = "")