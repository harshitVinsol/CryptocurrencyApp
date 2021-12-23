package com.vinsol.loreal.cryptocurrency.presentation.coin_detail

import com.vinsol.loreal.cryptocurrency.domain.model.CoinDetail

data class CoinDetailState(
    val isLoading: Boolean = false,
    val coinDetail: CoinDetail? = null,
    val error: String = ""
)